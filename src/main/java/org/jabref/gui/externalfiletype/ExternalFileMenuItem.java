package org.jabref.gui.externalfiletype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.Icon;
import javax.swing.JMenuItem;

import org.jabref.Globals;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.desktop.JabRefDesktop;
import org.jabref.gui.util.DefaultTaskExecutor;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.util.FileHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The menu item used in the popup menu for opening external resources associated
 * with an entry. Shows the resource name and icon given, and adds an action listener
 * to process the request if the user clicks this menu item.
 */
public class ExternalFileMenuItem extends JMenuItem implements ActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalFileMenuItem.class);

    private final String link;
    private final BibDatabaseContext databaseContext;
    private Optional<ExternalFileType> fileType;
    private final JabRefFrame frame;

    public ExternalFileMenuItem(JabRefFrame frame, String name, String link, Icon icon,
                                BibDatabaseContext databaseContext, Optional<ExternalFileType> fileType) {
        super(name, icon);
        this.frame = frame;
        this.link = link;
        this.databaseContext = databaseContext;
        this.fileType = fileType;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean success = openLink();
        if (!success) {
            List<Path> searchedDirs = databaseContext.getFileDirectoriesAsPaths(Globals.prefs.getFilePreferences());
            frame.getDialogService().notify(Localization.lang("Unable to open %0", link) + " " + Arrays.toString(searchedDirs.toArray()));
        }
    }

    private boolean openLink() {
        frame.getDialogService().notify(Localization.lang("External viewer called") + ".");
        try {
            Optional<ExternalFileType> type = fileType;
            if (!this.fileType.isPresent()) {
                // We don't already know the file type, so we try to deduce it from the extension:
                Optional<String> extension = FileHelper.getFileExtension(link);
                // Now we know the extension, check if it is one we know about:
                type = ExternalFileTypes.getInstance().getExternalFileTypeByExt(extension.orElse(null));
                fileType = type;
            }

            return JabRefDesktop.openExternalFileAnyFormat(databaseContext, link, type);

        } catch (IOException ex) {
            // See if we should show an error message concerning the application to open the
            // link with. We check if the file type is set, and if the file type has a non-empty
            // application link. If that link is referred by the error message, we can assume
            // that the problem is in the open-with-application setting:
            if ((fileType.isPresent()) && (!fileType.get().getOpenWithApplication().isEmpty())
            && ex.getMessage().contains(fileType.get().getOpenWithApplication())) {

                DefaultTaskExecutor.runInJavaFXThread(() -> frame.getDialogService().showErrorDialogAndWait(Localization.lang("Could not open link"),
                Localization.lang("Unable to open link. " + "The application '%0' associated with the file type '%1' could not be called.",
                fileType.get().getOpenWithApplication(),
                fileType.get().getName())));

                return false;
            }

            LOGGER.warn("Unable to open link", ex);
        }
        return false;
    }
}
