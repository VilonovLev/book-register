package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    public Book getBookById(long bookId) {
        return bookRepository.getBookById(bookId);
    }

    public Boolean removeBookById(long bookId) {
        return bookRepository.removeBookById(bookId);
    }

    public Boolean addBook(Book book) {
        return bookRepository.addBook(book);
    }
}
