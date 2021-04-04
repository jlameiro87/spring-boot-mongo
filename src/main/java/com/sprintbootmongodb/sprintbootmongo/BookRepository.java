package com.sprintbootmongodb.sprintbootmongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    public Book findByISBN(String isbn);
    public List<Book> findByAuthor(String author);
    public List<Book> findByYear(int year);

}
