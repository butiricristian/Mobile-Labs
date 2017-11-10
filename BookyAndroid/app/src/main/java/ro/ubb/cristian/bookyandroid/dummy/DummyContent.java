package ro.ubb.cristian.bookyandroid.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Book> ITEMS = new ArrayList<>(Arrays.asList(
            new Book("1", "Harry Potter", "J.K. Rowling", 346, "123456789"),
            new Book("2", "Harry Potter 2", "J.K. Rowling", 349, "123456790"),
            new Book("3", "Harry Potter 3", "J.K. Rowling", 456, "123456791"),
            new Book("4", "The Pragmatic Programmer", "Andrew Hunt", 320, "123444789"),
            new Book("5", "Clean Code", "Andrew Hunt", 376, "123456999")
    ));

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Book> ITEM_MAP = new HashMap<>();


    /**
     * A dummy item representing a piece of content.
     */
    public static class Book {
        public final String id;
        public final String title;
        public final String author;
        public final Integer nrPages;
        public final String isbn;

        public Book(String id, String title, String author, Integer nrPages, String isbn) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.nrPages = nrPages;
            this.isbn = isbn;
        }

        @Override
        public String toString() {
            return (title + String.valueOf(nrPages));
        }
    }
}
