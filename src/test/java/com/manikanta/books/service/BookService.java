package com.manikanta.books.service;

import com.manikanta.books.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book create(Book book);

    Optional<Book> findById(String isbn);

    List<Book> listBooks();

    boolean isBookExists(Book book);

    void deleteBookById(String isbn);
}
