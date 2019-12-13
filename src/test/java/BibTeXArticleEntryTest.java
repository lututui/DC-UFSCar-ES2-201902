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
    private BibEntry article2;
    private BibEntry article3;
    private BibEntry article4;
    private BibEntry article5;
    private BibEntry article6;
    private BibEntry article7;
    private BibEntry article8;
    private BibEntry article9;
    private BibEntry article10;
    private BibEntryWriter bew;

    @Before
    public void setUp() throws Exception {
        db = new BibDatabase();
    }

    @Test
    public void articleCompare() throws IOException {
        bew = new BibEntryWriter(new LatexFieldFormatter(JabRefPreferences.getInstance().getLatexFieldFormatterPreferences()), new BibEntryTypesManager());

        StringWriter sw = new StringWriter();
        article1 = new BibEntry(StandardEntryType.Article);
        article1.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article1.setField(StandardField.TITLE, "Processes & Materials");
        article1.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article1.setField(StandardField.YEAR, "2019");

        db.insertEntry(article1);

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

    @Test
    public void _articleInsert() {
        article1 = new BibEntry(StandardEntryType.Article);
        article1.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article1.setField(StandardField.TITLE, "Processes & Materials");
        article1.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article1.setField(StandardField.YEAR, "2019");

        db.insertEntry(article1);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article1);
    }

    // testes de limites inferiores (nulo)
    @Test
    public void _articleInsert2() {
        article2 = new BibEntry(StandardEntryType.Article);
        article2.setField(StandardField.AUTHOR, null);
        article2.setField(StandardField.TITLE, null);
        article2.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article2.setField(StandardField.YEAR, "2019");

        db.insertEntry(article2);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article2);
    }

    @Test
    public void _articleInsert3() {
        article3 = new BibEntry(StandardEntryType.Article);
        article3.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article3.setField(StandardField.TITLE, null);
        article3.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article3.setField(StandardField.YEAR, "2019");

        db.insertEntry(article3);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article3);
    }

    @Test
    public void _articleInsert4() {
        article4 = new BibEntry(StandardEntryType.Article);
        article4.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article4.setField(StandardField.TITLE, "Processes & Materials");
        article4.setField(StandardField.JOURNAL, null);
        article4.setField(StandardField.YEAR, "2019");

        db.insertEntry(article4);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article4);
    }

    @Test
    public void _articleInsert5() {
        article5= new BibEntry(StandardEntryType.Article);
        article5.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article5.setField(StandardField.TITLE, "Processes & Materials");
        article5.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article5.setField(StandardField.YEAR, null);

        db.insertEntry(article5);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article5);
    }

    // testes limites inferiores (um caractere nos campos author, title, journal)
    @Test
    public void articleInsert6() throws IOException {
        bew = new BibEntryWriter(new LatexFieldFormatter(JabRefPreferences.getInstance().getLatexFieldFormatterPreferences()), new BibEntryTypesManager());

        StringWriter sw = new StringWriter();
        article6 = new BibEntry(StandardEntryType.Article);
        article6.setField(StandardField.AUTHOR, "M");
        article6.setField(StandardField.TITLE, "P");
        article6.setField(StandardField.JOURNAL, "J");
        article6.setField(StandardField.YEAR, "2019");

        db.insertEntry(article6);

        bew.write(article6, sw, BibDatabaseMode.BIBTEX);
        String actual = sw.toString().trim();
        String expected = "@Article{," + OS.NEWLINE +
                "  author  = {M}," + OS.NEWLINE +
                "  journal = {J}," + OS.NEWLINE +
                "  title   = {P}," + OS.NEWLINE +
                "  year    = {2019}," + OS.NEWLINE +
                "}";
        Assert.assertEquals(expected, actual);
    }

    //testes para ano
    @Test
    public void _articleInsert7() {
        article7= new BibEntry(StandardEntryType.Article);
        article7.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article7.setField(StandardField.TITLE, "Processes & Materials");
        article7.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article7.setField(StandardField.YEAR, "2");

        db.insertEntry(article7);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article7);
    }

    @Test
    public void _articleInsert8() {
        article8= new BibEntry(StandardEntryType.Article);
        article8.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article8.setField(StandardField.TITLE, "Processes & Materials");
        article8.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article8.setField(StandardField.YEAR, "20199");

        db.insertEntry(article8);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article8);
    }

    @Test
    public void _articleInsert10() {
        article10= new BibEntry(StandardEntryType.Article);
        article10.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article10.setField(StandardField.TITLE, "Processes & Materials");
        article10.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)");
        article10.setField(StandardField.YEAR, "aaaaaaabbbbbcccccddddd");

        db.insertEntry(article10);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article10);
    }

    // teste para grandes insercoes
    @Test
    public void _articleInsert9() {
        article9= new BibEntry(StandardEntryType.Article);
        article9.setField(StandardField.AUTHOR, "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI) " +
                "Marcelo Lubaszewski (UFRGS) and Marcelo Antonio Pavanello (FEI)");
        article9.setField(StandardField.TITLE, "Processes & Materials Processes & Materials Processes & Materials " +
                "Processes & Materials Processes & Materials Processes & Materials Processes & Materials " +
                "Processes & Materials Processes & Materials Processes & Materials Processes & Materials " +
                "Processes & Materials Processes & Materials Processes & Materials Processes & Materials " +
                "Processes & Materials Processes & Materials Processes & Materials Processes & Materials " +
                "Processes & Materials Processes & Materials Processes & Materials Processes & Materials " +
                "Processes & Materials Processes & Materials");
        article9.setField(StandardField.JOURNAL, "Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)" +
                "Journal of Integrated Circuits and Systems (JICS)Journal of Integrated Circuits and Systems (JICS)");
        article9.setField(StandardField.YEAR, "201999999999999999999999999999999999999999999999999999999999999" +
                "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" +
                "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");

        db.insertEntry(article9);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article9);
    }

}
