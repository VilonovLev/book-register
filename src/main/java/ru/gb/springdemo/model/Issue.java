package ru.gb.springdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Issue {

  @Id
  private long id;
  private long bookId;
  private long readerId;
  private LocalDateTime issued;
  private LocalDateTime returned;

  public Issue(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued = LocalDateTime.now();
  }
}
