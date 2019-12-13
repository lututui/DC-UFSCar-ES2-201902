import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.field.StandardField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BibTeXBookEntryTest {
    private BibDatabase db;

    @Before
    public void setUp() throws Exception {
        db = new BibDatabase();
    }

    @Test
    public void bookInsert1() {
        BibEntry book1 = new BibEntry(StandardEntryType.Book);
        book1.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book1.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book1.setField(StandardField.PUBLISHER, "AMGH");
        book1.setField(StandardField.YEAR, "2016");
        book1.setField(StandardField.EDITION, "81");
        book1.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book1);
        Assert.assertTrue(db.hasEntries()); // Realmente entrou
        Assert.assertEquals(1, db.getEntryCount()); // Tem 1 só
        Assert.assertEquals(book1, db.getEntries().get(0)); // Pega a entry e testa se é a mesma
    }

    @Test
    public void bookInsert2() {
        try {
            BibEntry book2 = new BibEntry(StandardEntryType.Book);
            book2.setField(StandardField.AUTHOR, null);
            book2.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
            book2.setField(StandardField.PUBLISHER, "AMGH");
            book2.setField(StandardField.YEAR, "2016");
            book2.setField(StandardField.EDITION, "8");
            book2.setField(StandardField.ISBN, "9788580555332");

            db.insertEntry(book2);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertFalse(db.hasEntries());
            Assert.assertEquals(0, db.getEntryCount());
        }
    }

    @Test
    public void bookInsert3() {
        BibEntry book3 = new BibEntry(StandardEntryType.Book);
        book3.setField(StandardField.AUTHOR, "A");
        book3.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book3.setField(StandardField.PUBLISHER, "AMGH");
        book3.setField(StandardField.YEAR, "2016");
        book3.setField(StandardField.EDITION, "8");
        book3.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book3);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book3, db.getEntries().get(0));
    }

    @Test
    public void bookInsert4() {
        try {
            BibEntry book4 = new BibEntry(StandardEntryType.Book);
            book4.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
            book4.setField(StandardField.TITLE, null);
            book4.setField(StandardField.PUBLISHER, "AMGH");
            book4.setField(StandardField.YEAR, "2016");
            book4.setField(StandardField.EDITION, "8");
            book4.setField(StandardField.ISBN, "9788580555332");

            db.insertEntry(book4);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertFalse(db.hasEntries());
            Assert.assertEquals(0, db.getEntryCount());
        }
    }

    @Test
    public void bookInsert5() {
        BibEntry book5 = new BibEntry(StandardEntryType.Book);
        book5.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book5.setField(StandardField.TITLE, "a");
        book5.setField(StandardField.PUBLISHER, "AMGH");
        book5.setField(StandardField.YEAR, "2016");
        book5.setField(StandardField.EDITION, "8");
        book5.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book5);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book5, db.getEntries().get(0));
    }

    @Test
    public void bookInsert6() {
        try {
            BibEntry book6 = new BibEntry(StandardEntryType.Book);
            book6.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
            book6.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
            book6.setField(StandardField.PUBLISHER, null);
            book6.setField(StandardField.YEAR, "2016");
            book6.setField(StandardField.EDITION, "9");
            book6.setField(StandardField.ISBN, "9788580555332");

            db.insertEntry(book6);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertFalse(db.hasEntries());
            Assert.assertEquals(0, db.getEntryCount());
        }
    }

    @Test
    public void bookInsert7() {
        BibEntry book7 = new BibEntry(StandardEntryType.Book);
        book7.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book7.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book7.setField(StandardField.PUBLISHER, "a");
        book7.setField(StandardField.YEAR, "2016");
        book7.setField(StandardField.EDITION, "8");
        book7.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book7);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book7, db.getEntries().get(0));
    }

    @Test
    public void bookInsert8() {
        try {
            BibEntry book8 = new BibEntry(StandardEntryType.Book);
            book8.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
            book8.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
            book8.setField(StandardField.PUBLISHER, "AMGH");
            book8.setField(StandardField.YEAR, null);
            book8.setField(StandardField.EDITION, "8");
            book8.setField(StandardField.ISBN, "9788580555332");

            db.insertEntry(book8);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertFalse(db.hasEntries());
            Assert.assertEquals(0, db.getEntryCount());
        }
    }

    @Test
    public void bookInsert9() {
        BibEntry book9 = new BibEntry(StandardEntryType.Book);
        book9.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book9.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book9.setField(StandardField.PUBLISHER, "AMGH");
        book9.setField(StandardField.YEAR, "2");
        book9.setField(StandardField.EDITION, "8");
        book9.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book9);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book9, db.getEntries().get(0));
    }

    @Test
    public void bookInsert10() {
        BibEntry book10 = new BibEntry(StandardEntryType.Book);
        book10.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book10.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book10.setField(StandardField.PUBLISHER, "AMGH");
        book10.setField(StandardField.YEAR, "Nao deveria ser valido");
        book10.setField(StandardField.EDITION, "8");
        book10.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book10);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book10, db.getEntries().get(0));
    }

    @Test
    public void bookInsert11() {
        try {
            BibEntry book11 = new BibEntry(StandardEntryType.Book);
            book11.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
            book11.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
            book11.setField(StandardField.PUBLISHER, "AMGH");
            book11.setField(StandardField.YEAR, "2016");
            book11.setField(StandardField.EDITION, null);
            book11.setField(StandardField.ISBN, "9788580555332");

            db.insertEntry(book11);

            // Não deve ser alcançado
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertFalse(db.hasEntries());
            Assert.assertEquals(0, db.getEntryCount());
        }
    }

    @Test
    public void bookInsert12() {
        BibEntry book12 = new BibEntry(StandardEntryType.Book);
        book12.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book12.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book12.setField(StandardField.PUBLISHER, "AMGH");
        book12.setField(StandardField.YEAR, "2016");
        book12.setField(StandardField.EDITION, "8");
        book12.setField(StandardField.ISBN, "9788580555332");

        db.insertEntry(book12);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book12, db.getEntries().get(0));
    }

    @Test
    public void bookInsert13() {
        try {
            BibEntry book13 = new BibEntry(StandardEntryType.Book);
            book13.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
            book13.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
            book13.setField(StandardField.PUBLISHER, "AMGH");
            book13.setField(StandardField.YEAR, "2016");
            book13.setField(StandardField.EDITION, "8");
            book13.setField(StandardField.ISBN, null);

            db.insertEntry(book13);

            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertFalse(db.hasEntries());
            Assert.assertEquals(0, db.getEntryCount());
        }
    }

    @Test
    public void bookInsert14() {
        BibEntry book14 = new BibEntry(StandardEntryType.Book);
        book14.setField(StandardField.AUTHOR, "Roger Pressman and Bruce Maxim");
        book14.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem Profissional");
        book14.setField(StandardField.PUBLISHER, "AMGH");
        book14.setField(StandardField.YEAR, "2016");
        book14.setField(StandardField.EDITION, "8");
        book14.setField(StandardField.ISBN, "9");

        db.insertEntry(book14);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book14, db.getEntries().get(0));
    }

    @Test
    public void bookInsert15() {
        BibEntry book15 = new BibEntry(StandardEntryType.Book);
        book15.setField(StandardField.AUTHOR, "Roger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaximRoger Pressman and Bruce MaxiRoger Pressman and Bruce MaximmRoger Pressman and Bruce Maxim");
        book15.setField(StandardField.TITLE, "Engenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem ProfissionalEngenharia de Software: Uma Abordagem Profissional");
        book15.setField(StandardField.PUBLISHER, "AMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGAMGHAMGHAMGHAMGHHAMGHAMGAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHAMGHH");
        book15.setField(StandardField.YEAR, "201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016201620162016");
        book15.setField(StandardField.EDITION, "88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
        book15.setField(StandardField.ISBN, "99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");

        db.insertEntry(book15);
        Assert.assertTrue(db.hasEntries());
        Assert.assertEquals(1, db.getEntryCount());
        Assert.assertEquals(book15, db.getEntries().get(0));
    }


}
