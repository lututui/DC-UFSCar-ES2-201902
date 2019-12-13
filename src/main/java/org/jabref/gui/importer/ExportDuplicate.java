package org.jabref.gui.importer;

import javafx.stage.FileChooser;
import org.jabref.Globals;
import org.jabref.gui.DialogService;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.gui.exporter.ExportCommand;
import org.jabref.gui.util.BackgroundTask;
import org.jabref.gui.util.FileDialogConfiguration;
import org.jabref.gui.util.FileFilterConverter;
import org.jabref.logic.exporter.Exporter;
import org.jabref.logic.exporter.ExporterFactory;
import org.jabref.logic.exporter.SavePreferences;
import org.jabref.logic.exporter.TemplateExporter;
import org.jabref.logic.l10n.Localization;
import org.jabref.logic.layout.LayoutFormatterPreferences;
import org.jabref.logic.util.io.FileUtil;
import org.jabref.logic.xmp.XmpPreferences;
import org.jabref.model.entry.BibEntry;
import org.jabref.preferences.JabRefPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExportDuplicate extends SimpleCommand {

    private final JabRefPreferences preferences;
    private final DialogService dialogService;
    private final List<BibEntry> entriesToImport;
    private final JabRefFrame frame;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportCommand.class);

    public ExportDuplicate(JabRefPreferences preferences, DialogService dialogService, List<BibEntry> entriesToImport, JabRefFrame frame) {
        this.preferences = preferences;
        this.dialogService = dialogService;
        this.entriesToImport = entriesToImport;
        this.frame = frame;
    }

    @Override
    public void execute() {
        List<TemplateExporter> customExporters = preferences.getCustomExportFormats(Globals.journalAbbreviationLoader);
        LayoutFormatterPreferences layoutPreferences = preferences.getLayoutFormatterPreferences(Globals.journalAbbreviationLoader);
        SavePreferences savePreferences = preferences.loadForExportFromPreferences();
        XmpPreferences xmpPreferences = preferences.getXMPPreferences();

        List<Exporter> exporters = Globals.exportFactory.getExporters().stream()
                .sorted(Comparator.comparing(Exporter::getName))
                .collect(Collectors.toList());

        Globals.exportFactory = ExporterFactory.create(customExporters, layoutPreferences, savePreferences, xmpPreferences);
        FileDialogConfiguration fileDialogConfiguration = new FileDialogConfiguration.Builder()
                .addExtensionFilter(FileFilterConverter.exporterToExtensionFilter(exporters))
                .withDefaultExtension(Globals.prefs.get(JabRefPreferences.LAST_USED_EXPORT))
                .withInitialDirectory(Globals.prefs.get(JabRefPreferences.EXPORT_WORKING_DIRECTORY))
                .build();
        dialogService.showFileSaveDialog(fileDialogConfiguration)
                .ifPresent(path -> export(path, fileDialogConfiguration.getSelectedExtensionFilter(), exporters));
    }

    private void export(Path file, FileChooser.ExtensionFilter selectedExtensionFilter, List<Exporter> exporters) {

        String selectedExtension = selectedExtensionFilter.getExtensions().get(0).replace("*", "");
        if (!file.endsWith(selectedExtension)) {
            FileUtil.addExtension(file, selectedExtension);
        }

        final Exporter format = FileFilterConverter.getExporter(selectedExtensionFilter, exporters)
                .orElseThrow(() -> new IllegalStateException("User didn't selected a file type for the extension"));

        // Set the global variable for this database's file directory before exporting,
        // so formatters can resolve linked files correctly.
        // (This is an ugly hack!)
        Globals.prefs.fileDirForDatabase = frame.getCurrentBasePanel()
                .getBibDatabaseContext()
                .getFileDirectories(Globals.prefs.getFilePreferences());

        // Make sure we remember which filter was used, to set
        // the default for next time:
        Globals.prefs.put(JabRefPreferences.LAST_USED_EXPORT, format.getName());
        Globals.prefs.put(JabRefPreferences.EXPORT_WORKING_DIRECTORY, file.getParent().toString());

        final List<BibEntry> finEntries = entriesToImport;

        BackgroundTask
                .wrap(() -> {
                    format.export(frame.getCurrentBasePanel().getBibDatabaseContext(),
                            file,
                            frame.getCurrentBasePanel()
                                    .getBibDatabaseContext()
                                    .getMetaData()
                                    .getEncoding()
                                    .orElse(Globals.prefs.getDefaultEncoding()),
                            finEntries);
                    return null; // can not use BackgroundTask.wrap(Runnable) because Runnable.run() can't throw Exceptions
                })
                .onSuccess(x -> frame.getDialogService().notify(Localization.lang("%0 export successful", format.getName())))
                .onFailure(this::handleError)
                .executeWith(Globals.TASK_EXECUTOR);
    }

    private void handleError(Exception ex) {
        LOGGER.warn("Problem exporting", ex);
        frame.getDialogService().notify(Localization.lang("Could not save file."));
        // Need to warn the user that saving failed!
        frame.getDialogService().showErrorDialogAndWait(Localization.lang("Save library"), Localization.lang("Could not save file."), ex);
    }
}
