package com.sprintbootmongodb.sprintbootmongo.rest;

import com.sprintbootmongodb.sprintbootmongo.Book;
import com.sprintbootmongodb.sprintbootmongo.BookRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class BookController {
    String[] HEADERS = { "isbn", "title", "author", "year"};

    @Autowired
    private BookRepository repository;

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/book/{isbn}")
    Book getBookByISBN(@PathVariable String isbn) {
        return repository.findByISBN(isbn);
    }

    @GetMapping("/book-author/{author}")
    List<Book> getBookByAuthor(@PathVariable String author) {
        return repository.findByAuthor(author);
    }

    @GetMapping("/book-year/{year}")
    List<Book> getBookByYear(@PathVariable int year) {
        return repository.findByYear(year);
    }

    @GetMapping("/seedbooks")
    String seedBookDataBase() throws IOException {
        // Empty the database
        repository.deleteAll();

        String fileName = "data/books.csv";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
            String isbn = record.get("isbn");
            String title = record.get("title");
            String author = record.get("author");
            String year = record.get("year");

            repository.save(new Book(isbn, title, Integer.parseInt(year), author));
        }

        return "Database Seeded";
    }

}
