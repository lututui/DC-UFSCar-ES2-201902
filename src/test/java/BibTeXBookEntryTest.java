import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.field.StandardField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BibTeXBookEntryTest {
    static final private BibDatabase db = new BibDatabase();
    private BibEntry book1;

    @Before
    public void setUp() throws Exception {
        book1 = new BibEntry(StandardEntryType.Book);
        book1.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book1.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book1.setField(StandardField.PUBLISHER, "AMGH");
        book1.setField(StandardField.YEAR, "2016");
        book1.setField(StandardField.EDITION, "8");
        book1.setField(StandardField.ISBN, "9788580555332");
    }

    @Test
    public void bookInsert() {
        db.insertEntry(book1);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book1);
    }
}
