package com.manikanta.books.controllers;

import com.manikanta.books.domain.Book;
import com.manikanta.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path = "books/{isbn}")
    public ResponseEntity<Book> createBook(@PathVariable final String isbn,
                                           @RequestBody final Book book) {
        book.setIsbn(isbn);
        final Book savedBook = bookService.create(book);
        final ResponseEntity<Book> response = new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
        return response;
    }

    @GetMapping(path="books/{isbn}")
    public ResponseEntity<Book> retrieveBook(@PathVariable final String isbn) {
        final Optional<Book> foundBook = bookService.findById(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
               .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }
}
