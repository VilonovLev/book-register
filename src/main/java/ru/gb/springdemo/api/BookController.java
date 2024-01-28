package ru.gb.springdemo.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    /**
     * Book service.
     */
    private final BookService bookService;

    /**
     * @param bookId - id book
     * @return ResponseEntity
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookInfo(@PathVariable final long bookId) {
        log.info("Запрос на информацию о книге: bookId = {}", bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getBookById(bookId));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable final long bookId) {
        log.info("Запрос на удаления книги: bookId = {}", bookId);
        bookService.removeBookById(bookId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody final Book book) {
        log.info("Запрос на добавления книги: bookId = {}, bookName = {}", book.getId(), book.getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.addBook(book));
    }
}
