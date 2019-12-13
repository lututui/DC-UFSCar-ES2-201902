import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.field.StandardField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BibTeXBookEntryTest {
    private BibDatabase db;
    private BibEntry book1;
    private BibEntry book2;
    private BibEntry book3;
    private BibEntry book4;
    private BibEntry book5;
    private BibEntry book6;
    private BibEntry book7;
    private BibEntry book8;
    private BibEntry book9;
    private BibEntry book10;
    private BibEntry book11;
    private BibEntry book12;
    private BibEntry book13;
    private BibEntry book14;
    private BibEntry book15;

    @Before
    public void setUp() throws Exception {
        db = new BibDatabase();
    }


    @Test
    public void bookInsert1() {
        book1 = new BibEntry(StandardEntryType.Book);
        book1.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book1.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book1.setField(StandardField.PUBLISHER, "AMGH");
        book1.setField(StandardField.YEAR, "2016");
        book1.setField(StandardField.EDITION, "81");
        book1.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book1);
        Assert.assertTrue(db.hasEntries()); // Realmente entrou
        Assert.assertEquals(db.getEntryCount(), 1); // Tem 1 só
        Assert.assertEquals(db.getEntries().get(0), book1); // Pega a entry e testa se é a mesma
    }

    @Test
    public void bookInsert2() {
        book2 = new BibEntry(StandardEntryType.Book);
        book2.setField(StandardField.AUTHOR, null);
        book2.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book2.setField(StandardField.PUBLISHER, "AMGH");
        book2.setField(StandardField.YEAR, "2016");
        book2.setField(StandardField.EDITION, "8");
        book2.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book2);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book2);
    }

    @Test
    public void bookInsert3() {
        book3 = new BibEntry(StandardEntryType.Book);
        book3.setField(StandardField.AUTHOR, "A");
        book3.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book3.setField(StandardField.PUBLISHER, "AMGH");
        book3.setField(StandardField.YEAR, "2016");
        book3.setField(StandardField.EDITION, "8");
        book3.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book3);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book3);
    }

    @Test
    public void bookInsert4() {
        book4 = new BibEntry(StandardEntryType.Book);
        book4.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book4.setField(StandardField.TITLE, null);
        book4.setField(StandardField.PUBLISHER, "AMGH");
        book4.setField(StandardField.YEAR, "2016");
        book4.setField(StandardField.EDITION, "8");
        book4.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book4);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book4);
    }

    @Test
    public void bookInsert5() {
        book5 = new BibEntry(StandardEntryType.Book);
        book5.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book5.setField(StandardField.TITLE, "a");
        book5.setField(StandardField.PUBLISHER, "AMGH");
        book5.setField(StandardField.YEAR, "2016");
        book5.setField(StandardField.EDITION, "8");
        book5.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book5);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book5);
    }

    @Test
    public void bookInsert6() {
        book6 = new BibEntry(StandardEntryType.Book);
        book6.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book6.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book6.setField(StandardField.PUBLISHER, null);
        book6.setField(StandardField.YEAR, "2016");
        book6.setField(StandardField.EDITION, "9");
        book6.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book6);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book6);
    }

    @Test
    public void bookInsert7() {
        book7 = new BibEntry(StandardEntryType.Book);
        book7.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book7.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book7.setField(StandardField.PUBLISHER, "a");
        book7.setField(StandardField.YEAR, "2016");
        book7.setField(StandardField.EDITION, "8");
        book7.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book7);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book7);
    }

    @Test
    public void bookInsert8() {
        book8 = new BibEntry(StandardEntryType.Book);
        book8.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book8.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book8.setField(StandardField.PUBLISHER, "AMGH");
        book8.setField(StandardField.YEAR, null);
        book8.setField(StandardField.EDITION, "8");
        book8.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book8);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book8);
    }

    @Test
    public void bookInsert9() {
        book9 = new BibEntry(StandardEntryType.Book);
        book9.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book9.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book9.setField(StandardField.PUBLISHER, "AMGH");
        book9.setField(StandardField.YEAR, "2");
        book9.setField(StandardField.EDITION, "8");
        book9.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book9);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book9);
    }

    @Test
    public void bookInsert10() {
        book10 = new BibEntry(StandardEntryType.Book);
        book10.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book10.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book10.setField(StandardField.PUBLISHER, "AMGH");
        book10.setField(StandardField.YEAR, "Nao deveria ser valido");
        book10.setField(StandardField.EDITION, "8");
        book10.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book10);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book10);
    }

    @Test
    public void bookInsert11() {
        book11 = new BibEntry(StandardEntryType.Book);
        book11.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book11.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book11.setField(StandardField.PUBLISHER, "AMGH");
        book11.setField(StandardField.YEAR, "2016");
        book11.setField(StandardField.EDITION, null);
        book11.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book11);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book11);
    }

    @Test
    public void bookInsert12() {
        book12 = new BibEntry(StandardEntryType.Book);
        book12.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book12.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book12.setField(StandardField.PUBLISHER, "AMGH");
        book12.setField(StandardField.YEAR, "2016");
        book12.setField(StandardField.EDITION, "8");
        book12.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book12);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book12);
    }

    @Test
    public void bookInsert13() {
        book13 = new BibEntry(StandardEntryType.Book);
        book13.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book13.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book13.setField(StandardField.PUBLISHER, "AMGH");
        book13.setField(StandardField.YEAR, "2016");
        book13.setField(StandardField.EDITION, "8");
        book13.setField(StandardField.ISBN, null);

        db.insertEntry(book13);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book13);
    }

    @Test
    public void bookInsert14() {
        book14 = new BibEntry(StandardEntryType.Book);
        book14.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book14.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book14.setField(StandardField.PUBLISHER, "AMGH");
        book14.setField(StandardField.YEAR, "2016");
        book14.setField(StandardField.EDITION, "8");
        book14.setField(StandardField.ISBN, "9");

        db.insertEntry(book14);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book14);
    }

    @Test
    public void bookInsert15() {
        book15 = new BibEntry(StandardEntryType.Book);
        book15.setField(StandardField.AUTHOR, "Roger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaxiRoger Pressman and Bruce MaximmRoger Pressman and Bruce Maxim");
        book15.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem Profissional");
        book15.setField(StandardField.PUBLISHER, "AMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGAMGHAMGHAMGHAMGHHAMGHAMGAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHH");
        book15.setField(StandardField.YEAR, "201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016");
        book15.setField(StandardField.EDITION, "88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
        book15.setField(StandardField.ISBN, "99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");

        db.insertEntry(book15);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(db.getEntryCount(), 1);
        Assert.assertEquals(db.getEntries().get(0), book15);
    }


}
