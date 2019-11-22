import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.field.StandardField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BibTeXArticleEntryTest {
    private BibDatabase db = new BibDatabase();
    private BibEntry article1;

    @Before
    public void setUp() throws Exception {
        article1 = new BibEntry(StandardEntryType.Article);
        article1.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim Artigo");
        article1.setField(StandardField.TITLE, "Artigo teste Engenharia de Software: Uma Abordagem Profissional");
        article1.setField(StandardField.JOURNAL, "Diario da regiao");
    }

    @Test
    public void articleInsert() {
        db.insertEntry(article1);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), article1);
    }
}
