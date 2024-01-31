package com.manikanta.books.service.impl;

import com.manikanta.books.domain.Book;
import com.manikanta.books.domain.BookEntity;
import com.manikanta.books.repositories.BookRepository;
import com.manikanta.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    public final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookEntityToBook(savedBookEntity);
    }

    @Override
    public Optional<Book> findById(String isbn) {
        final Optional<BookEntity> foundBook = bookRepository.findById(isbn);
        return foundBook.map(book -> bookEntityToBook(book));
    }

    @Override
    public List<Book> listBooks() {
        final List<BookEntity> bookList = bookRepository.findAll();
        return bookList.stream().map(book -> bookEntityToBook(book)).collect(Collectors.toList());
    }

    @Override
    public boolean isBookExists(Book book) {
        return false;
    }

    @Override
    public void deleteBookById(String isbn) {

    }

    final BookEntity bookToBookEntity(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .name(book.getName())
                .author(book.getAuthor())
                .build();
    }

    final Book bookEntityToBook(BookEntity bookEntity) {
        return Book
                .builder()
                .isbn(bookEntity.getIsbn())
                .name(bookEntity.getName())
                .author(bookEntity.getAuthor())
                .build();
    }
}
