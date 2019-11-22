import java.io.IOException;
import java.io.StringWriter;

import org.jabref.logic.bibtex.BibEntryWriter;
import org.jabref.logic.bibtex.LatexFieldFormatter;
import org.jabref.logic.util.OS;
import org.jabref.model.database.BibDatabase;
import org.jabref.model.database.BibDatabaseMode;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.BibEntryTypesManager;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.field.StandardField;
import org.jabref.preferences.JabRefPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BibTeXArticleEntryTest {
    private BibDatabase db = new BibDatabase();
    private BibEntry article1;
    private BibEntryWriter bew;

    @Before
    public void setUp() throws Exception {
        bew = new BibEntryWriter(new LatexFieldFormatter(JabRefPreferences.getInstance().getLatexFieldFormatterPreferences()), new BibEntryTypesManager());

        article1 = new BibEntry(StandardEntryType.Article);
        article1.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article1.setField(StandardField.TITLE, "Processes & Materials");
        article1.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article1.setField(StandardField.YEAR, "2019");
    }

    @Test
    public void _articleInsert() {
        db.insertEntry(article1);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article1);
    }

    @Test
    public void articleCompare() throws IOException {
        StringWriter sw = new StringWriter();

        bew.write(article1, sw, BibDatabaseMode.BIBTEX);
        String actual = sw.toString().trim();
        String expected = "@Article{," + OS.NEWLINE +
                "  author  = {Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)}," + OS.NEWLINE +
                "  journal = {Journal of Integrated Circuits and Systems (JICS)}," + OS.NEWLINE +
                "  title   = {Processes \\& Materials}," + OS.NEWLINE +
                "  year    = {2019}," + OS.NEWLINE +
                "}";

        Assert.assertEquals(expected, actual);
    }
}
