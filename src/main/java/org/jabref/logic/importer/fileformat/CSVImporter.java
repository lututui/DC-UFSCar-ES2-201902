package org.jabref.logic.importer.fileformat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.jabref.logic.exporter.TemplateExporter;
import org.jabref.logic.importer.Importer;
import org.jabref.logic.importer.ParserResult;
import org.jabref.logic.util.FileType;
import org.jabref.logic.util.StandardFileType;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.EntryType;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.UnknownEntryType;
import org.jabref.model.entry.field.IEEEField;
import org.jabref.model.entry.field.InternalField;
import org.jabref.model.entry.field.StandardField;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVImporter extends Importer {
    @Override
    public boolean isRecognizedFormat(BufferedReader input) {
        String pattern, str;
        String layoutFile = TemplateExporter.LAYOUT_PREFIX + "openoffice/openoffice-csv" + TemplateExporter.BEGIN_INFIX + TemplateExporter.LAYOUT_EXTENSION;
        InputStream rsc = CSVImporter.class.getResourceAsStream(layoutFile);

        try {
            FileReader fr;
            BufferedReader bf;

            if (rsc != null) {
                bf = new BufferedReader(new InputStreamReader(rsc));
            } else {
                fr = new FileReader(layoutFile);
                bf = new BufferedReader(fr);
            }
            pattern = bf.readLine();

            if (pattern == null) {
                return false;
            }

            while ((str = input.readLine()) != null) {
                if (str.equals(pattern)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    @Override
    public ParserResult importDatabase(BufferedReader input) throws IOException {
        Objects.requireNonNull(input);

        List<BibEntry> entries = new ArrayList<>();
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);

        for (CSVRecord r : csvParser) {
            BibEntry entry = new BibEntry();

            String type = r.get("entrytype");
            Optional<StandardEntryType> tp = StandardEntryType.fromName(type);
            EntryType realType = tp.isPresent() ? tp.get() : new UnknownEntryType(type);

            entry.setType(realType);
            entry.setField(StandardField.ABSTRACT, r.get("abstract"));
            entry.setField(StandardField.ADDRESS, r.get("address"));
            entry.setField(StandardField.AUTHOR, r.get("author"));
            entry.setField(InternalField.KEY_FIELD, r.get("bibtexkey"));
            entry.setField(StandardField.BOOKTITLE, r.get("booktitle"));
            entry.setField(StandardField.CHAPTER, r.get("chapter"));
            entry.setField(StandardField.COMMENT, r.get("comment"));
            entry.setField(StandardField.CROSSREF, r.get("crossref"));
            entry.setField(IEEEField.CTLALT_STRETCH_FACTOR, r.get("ctlalt_stretch_factor"));
            entry.setField(IEEEField.CTLDASH_REPEATED_NAMES, r.get("ctldash_repeated_names"));
            entry.setField(IEEEField.CTLMAX_NAMES_FORCED_ETAL, r.get("ctlmax_names_forced_etal"));
            entry.setField(IEEEField.CTLNAME_FORMAT_STRING, r.get("ctlname_format_string"));
            entry.setField(IEEEField.CTLNAME_LATEX_CMD, r.get("ctlname_latex_cmd"));
            entry.setField(IEEEField.CTLNAME_URL_PREFIX, r.get("ctlname_url_prefix"));
            entry.setField(IEEEField.CTLNAMES_SHOW_ETAL, r.get("ctlnames_show_etal"));
            entry.setField(IEEEField.CTLUSE_ALT_SPACING, r.get("ctluse_alt_spacing"));
            entry.setField(IEEEField.CTLUSE_ARTICLE_NUMBER, r.get("ctluse_article_number"));
            entry.setField(IEEEField.CTLUSE_FORCED_ETAL, r.get("ctluse_forced_etal"));
            entry.setField(IEEEField.CTLUSE_PAPER, r.get("ctluse_paper"));
            entry.setField(IEEEField.CTLUSE_URL, r.get("ctluse_url"));
            entry.setField(StandardField.EDITION, r.get("edition"));
            entry.setField(StandardField.EDITOR, r.get("editor"));
            entry.setField(StandardField.FILE, r.get("file"));
            entry.setField(InternalField.GROUPS, r.get("groups"));
            entry.setField(StandardField.HOWPUBLISHED, r.get("howpublished"));
            entry.setField(StandardField.INSTITUTION, r.get("institution"));
            entry.setField(StandardField.ISBN, r.get("isbn"));
            entry.setField(StandardField.ISSN, r.get("issn"));
            entry.setField(StandardField.JOURNAL, r.get("journal"));
            entry.setField(StandardField.KEYWORDS, r.get("keywords"));
            entry.setField(StandardField.MONTH, r.get("month"));
            entry.setField(StandardField.NOTE, r.get("note"));
            entry.setField(StandardField.NUMBER, r.get("number"));
            entry.setField(StandardField.ORGANIZATION, r.get("organization"));
            entry.setField(StandardField.PAGES, r.get("pages"));
            entry.setField(StandardField.PUBLISHER, r.get("publisher"));
            entry.setField(StandardField.SCHOOL, r.get("school"));
            entry.setField(StandardField.SERIES, r.get("series"));
            entry.setField(StandardField.TITLE, r.get("title"));
            entry.setField(StandardField.TYPE, r.get("type"));
            entry.setField(StandardField.VOLUME, r.get("volume"));
            entry.setField(StandardField.YEAR, r.get("year"));
            entry.setField(StandardField.DOI, r.get("doi"));

            entries.add(entry);
        }

        return new ParserResult(entries);
    }

    @Override
    public String getName() {
        return "CSV";
    }

    @Override
    public FileType getFileType() {
        return StandardFileType.CSV;
    }

    @Override
    public String getDescription() {
        return "Importer for the CSV format";
    }
}
