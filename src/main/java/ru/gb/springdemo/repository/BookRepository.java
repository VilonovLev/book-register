package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
