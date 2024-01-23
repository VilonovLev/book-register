package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.util.IssueRejectedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг.
 *     Если есть - не выдавать книгу (статус ответа - 409 Conflict)
 */
@Service
@RequiredArgsConstructor
public class IssuerService {

  private final BookRepository bookRepository;

  private final ReaderRepository readerRepository;

  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) throws IssueRejectedException {
    validateRequest(request);
    if (!issueRepository.bookIsAcceptably(request.getBookId())) {
      throw new IssueRejectedException("Нет свободной книги");
    }
    if (!issueRepository.readerCanTakeBook(request.getReaderId())) {
      throw new IssueRejectedException("Читателю отказанно в получение книги");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  public Issue getIssueById(long issueId) {
    return issueRepository.getIssueById(issueId);
  }

  private void validateRequest(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
  }

  public Boolean returnedBook(long issueId) {
    issueRepository.getIssueById(issueId).setReturned_at(LocalDateTime.now());
    return true;
  }

  public List<Issue> getIssues() {
    return issueRepository.getAllIssue();
  }
}
