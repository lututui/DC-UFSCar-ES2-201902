import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jabref.model.database.BibDatabase;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.StandardEntryType;
import org.jabref.model.entry.field.Field;
import org.jabref.model.entry.field.StandardField;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SortedInsertTest {
    private static final List<Field> fields = Arrays.asList(StandardField.AUTHOR, StandardField.EDITOR);
    private BibEntry article, book, booklet, conference, inBook, inCollection, inProceedings, manual, masterThesis,
            misc, phdThesis, proceedings, techReport, unpublished, ieeeTranBSTCTL;
    private final List<String> authorsOrEditors = Arrays.asList(
            "zlDQk2buHS", "AJAC3", "PsvTmeMmt2", "K", "huwtk", "AimQP0B", "VXQpz", "x6GYHUyIw", "KeCj0u"
    );

    @Before
    public void setUp() {
        article = new BibEntry(StandardEntryType.Article);
        book = new BibEntry(StandardEntryType.Book);
        booklet = new BibEntry(StandardEntryType.Booklet);
        conference = new BibEntry(StandardEntryType.Conference);
        inBook = new BibEntry(StandardEntryType.InBook);
        inCollection = new BibEntry(StandardEntryType.InCollection);
        inProceedings = new BibEntry(StandardEntryType.InProceedings);
        manual = new BibEntry(StandardEntryType.Manual);
        masterThesis = new BibEntry(StandardEntryType.MastersThesis);
        misc = new BibEntry(StandardEntryType.Misc);
        phdThesis = new BibEntry(StandardEntryType.PhdThesis);
        proceedings = new BibEntry(StandardEntryType.Proceedings);
        techReport = new BibEntry(StandardEntryType.TechReport);
        unpublished = new BibEntry(StandardEntryType.Unpublished);
        ieeeTranBSTCTL = new BibEntry(StandardEntryType.IEEEtranBSTCTL);
    }

    @Test
    public void testSortedInsertTypelessEntries() {
        final ArrayList<BibEntry> entries = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            entries.add(new BibEntry());
        }

        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(15, db.getEntryCount());
    }

    @Test
    public void testSortedEmpty() {
        final ArrayList<BibEntry> entries = new ArrayList<>();
        final BibDatabase db = new BibDatabase(entries);

        assertFalse(db.hasEntries());
        assertEquals(0, db.getEntryCount());
    }

    @Test
    public void testSortedOneEntry() {
        final List<BibEntry> entries = Collections.singletonList(book);
        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(1, db.getEntryCount());
    }

    @Test
    public void testSortedTwoEntries() {
        final List<BibEntry> entries = Arrays.asList(techReport, book);
        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(2, db.getEntryCount());
        assertEquals(StandardEntryType.Book, db.getEntries().get(0).getType());
        assertEquals(StandardEntryType.TechReport, db.getEntries().get(1).getType());
    }

    @Test
    public void testSortedInsertAllBibtexTypesRandomized() {
        final List<BibEntry> randomizedEntries = Arrays.asList(
                article, book, booklet, conference, inBook, inCollection, inProceedings, manual, masterThesis,
                misc, phdThesis, proceedings, techReport, unpublished, ieeeTranBSTCTL
        );
        Collections.shuffle(randomizedEntries);

        final BibDatabase db = new BibDatabase(randomizedEntries);

        assertTrue(db.hasEntries());
        assertEquals(randomizedEntries.size(), db.getEntryCount());

        assertEquals(StandardEntryType.Article, db.getEntries().get(0).getType());
        assertEquals(StandardEntryType.Book, db.getEntries().get(1).getType());
        assertEquals(StandardEntryType.Booklet, db.getEntries().get(2).getType());
        assertEquals(StandardEntryType.Conference, db.getEntries().get(3).getType());
        assertEquals(StandardEntryType.IEEEtranBSTCTL, db.getEntries().get(4).getType());
        assertEquals(StandardEntryType.InBook, db.getEntries().get(5).getType());
        assertEquals(StandardEntryType.InCollection, db.getEntries().get(6).getType());
        assertEquals(StandardEntryType.InProceedings, db.getEntries().get(7).getType());
        assertEquals(StandardEntryType.Manual, db.getEntries().get(8).getType());
        assertEquals(StandardEntryType.MastersThesis, db.getEntries().get(9).getType());
        assertEquals(StandardEntryType.Misc, db.getEntries().get(10).getType());
        assertEquals(StandardEntryType.PhdThesis, db.getEntries().get(11).getType());
        assertEquals(StandardEntryType.Proceedings, db.getEntries().get(12).getType());
        assertEquals(StandardEntryType.TechReport, db.getEntries().get(13).getType());
        assertEquals(StandardEntryType.Unpublished, db.getEntries().get(14).getType());
    }

    @Test
    public void testSortedByAuthor() {
        final ArrayList<BibEntry> entries = new ArrayList<>();

        for (String author : authorsOrEditors) {
            final BibEntry bibEntry = new BibEntry();
            bibEntry.setField(StandardField.AUTHOR, author);
            entries.add(bibEntry);
        }

        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(entries.size(), db.getEntryCount());

        assertEquals("AimQP0B", getResolvedFields(db.getEntries().get(0), StandardField.AUTHOR));
        assertEquals("AJAC3", getResolvedFields(db.getEntries().get(1), StandardField.AUTHOR));
        assertEquals("huwtk", getResolvedFields(db.getEntries().get(2), StandardField.AUTHOR));
        assertEquals("K", getResolvedFields(db.getEntries().get(3), StandardField.AUTHOR));
        assertEquals("KeCj0u", getResolvedFields(db.getEntries().get(4), StandardField.AUTHOR));
        assertEquals("PsvTmeMmt2", getResolvedFields(db.getEntries().get(5), StandardField.AUTHOR));
        assertEquals("VXQpz", getResolvedFields(db.getEntries().get(6), StandardField.AUTHOR));
        assertEquals("x6GYHUyIw", getResolvedFields(db.getEntries().get(7), StandardField.AUTHOR));
        assertEquals("zlDQk2buHS", getResolvedFields(db.getEntries().get(8), StandardField.AUTHOR));
    }

    @Test
    public void testSortedByEditor() {
        final ArrayList<BibEntry> entries = new ArrayList<>();

        for (String editor : authorsOrEditors) {
            final BibEntry bibEntry = new BibEntry();
            bibEntry.setField(StandardField.EDITOR, editor);
            entries.add(bibEntry);
        }

        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(entries.size(), db.getEntryCount());

        assertEquals("AimQP0B", getResolvedFields(db.getEntries().get(0), StandardField.EDITOR));
        assertEquals("AJAC3", getResolvedFields(db.getEntries().get(1), StandardField.EDITOR));
        assertEquals("huwtk", getResolvedFields(db.getEntries().get(2), StandardField.EDITOR));
        assertEquals("K", getResolvedFields(db.getEntries().get(3), StandardField.EDITOR));
        assertEquals("KeCj0u", getResolvedFields(db.getEntries().get(4), StandardField.EDITOR));
        assertEquals("PsvTmeMmt2", getResolvedFields(db.getEntries().get(5), StandardField.EDITOR));
        assertEquals("VXQpz", getResolvedFields(db.getEntries().get(6), StandardField.EDITOR));
        assertEquals("x6GYHUyIw", getResolvedFields(db.getEntries().get(7), StandardField.EDITOR));
        assertEquals("zlDQk2buHS", getResolvedFields(db.getEntries().get(8), StandardField.EDITOR));
    }

    @Test
    public void testSortedByAuthorAndEditor() {
        final ArrayList<BibEntry> entries = new ArrayList<>();

        for (int i = 0; i <= authorsOrEditors.size() / 2; i++) {
            String authorOrEditor = authorsOrEditors.get(i);
            final BibEntry bibEntry = new BibEntry();

            bibEntry.setField(StandardField.EDITOR, authorOrEditor);
            entries.add(bibEntry);
        }

        for (int i = 1 + authorsOrEditors.size() / 2; i < authorsOrEditors.size(); i++) {
            String authorOrEditor = authorsOrEditors.get(i);
            final BibEntry bibEntry = new BibEntry();

            bibEntry.setField(StandardField.AUTHOR, authorOrEditor);
            entries.add(bibEntry);
        }

        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(entries.size(), db.getEntryCount());

        assertEquals("AimQP0B", getResolvedFields(db.getEntries().get(0), fields));
        assertEquals("AJAC3", getResolvedFields(db.getEntries().get(1), fields));
        assertEquals("huwtk", getResolvedFields(db.getEntries().get(2), fields));
        assertEquals("K", getResolvedFields(db.getEntries().get(3), fields));
        assertEquals("KeCj0u", getResolvedFields(db.getEntries().get(4), fields));
        assertEquals("PsvTmeMmt2", getResolvedFields(db.getEntries().get(5), fields));
        assertEquals("VXQpz", getResolvedFields(db.getEntries().get(6), fields));
        assertEquals("x6GYHUyIw", getResolvedFields(db.getEntries().get(7), fields));
        assertEquals("zlDQk2buHS", getResolvedFields(db.getEntries().get(8), fields));
    }

    private static String getResolvedFields(BibEntry entry, Field fields) {
        return entry.getField(fields).orElse("");
    }

    private static String getResolvedFields(BibEntry entry, List<Field> fields) {
        for (Field f : fields) {
            if (entry.hasField(f)) {
                return entry.getField(f).get();
            }
        }

        return "";
    }

    @Test
    public void testSortedByAuthorOrEditorWithType() {
        final ArrayList<BibEntry> entries = new ArrayList<>();

        for (int i = 0; i <= authorsOrEditors.size() / 2; i++) {
            String authorOrEditor = authorsOrEditors.get(i);
            final BibEntry bibEntry = new BibEntry(StandardEntryType.Conference);

            bibEntry.setField(StandardField.EDITOR, authorOrEditor);
            entries.add(bibEntry);
        }

        for (int i = 1 + authorsOrEditors.size() / 2; i < authorsOrEditors.size(); i++) {
            String authorOrEditor = authorsOrEditors.get(i);
            final BibEntry bibEntry = new BibEntry(StandardEntryType.Book);

            bibEntry.setField(StandardField.AUTHOR, authorOrEditor);
            entries.add(bibEntry);
        }

        assertEquals(authorsOrEditors.size(), entries.size());

        final BibDatabase db = new BibDatabase(entries);

        assertTrue(db.hasEntries());
        assertEquals(entries.size(), db.getEntryCount());

        assertEquals(StandardEntryType.Book, db.getEntries().get(0).getType());
        assertEquals(StandardEntryType.Book, db.getEntries().get(1).getType());
        assertEquals(StandardEntryType.Book, db.getEntries().get(2).getType());
        assertEquals(StandardEntryType.Book, db.getEntries().get(3).getType());
        assertEquals(StandardEntryType.Conference, db.getEntries().get(4).getType());
        assertEquals(StandardEntryType.Conference, db.getEntries().get(5).getType());
        assertEquals(StandardEntryType.Conference, db.getEntries().get(6).getType());
        assertEquals(StandardEntryType.Conference, db.getEntries().get(7).getType());
        assertEquals(StandardEntryType.Conference, db.getEntries().get(8).getType());

        assertEquals("AimQP0B", getResolvedFields(db.getEntries().get(0), StandardField.AUTHOR));
        assertEquals("KeCj0u", getResolvedFields(db.getEntries().get(1), StandardField.AUTHOR));
        assertEquals("VXQpz", getResolvedFields(db.getEntries().get(2), StandardField.AUTHOR));
        assertEquals("x6GYHUyIw", getResolvedFields(db.getEntries().get(3), StandardField.AUTHOR));

        assertEquals("AJAC3", getResolvedFields(db.getEntries().get(4), StandardField.EDITOR));
        assertEquals("huwtk", getResolvedFields(db.getEntries().get(5), StandardField.EDITOR));
        assertEquals("K", getResolvedFields(db.getEntries().get(6), StandardField.EDITOR));
        assertEquals("PsvTmeMmt2", getResolvedFields(db.getEntries().get(7), StandardField.EDITOR));
        assertEquals("zlDQk2buHS", getResolvedFields(db.getEntries().get(8), StandardField.EDITOR));
    }
}
