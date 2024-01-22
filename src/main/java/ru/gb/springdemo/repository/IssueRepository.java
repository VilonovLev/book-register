package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {

  private final List<Issue> issues;
  private long maxCountBooks;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  @Autowired
  public void setMaxCountBooks(@Value("${application.max-allowed-books:1}") long maxCountBooks) {
    this.maxCountBooks = maxCountBooks;
  }

  public void save(Issue issue) {
    issues.add(issue);
  }

  public Issue getIssueById(long issueId) {
    return issues.stream().filter(it -> Objects.equals(it.getId(), issueId))
            .findFirst()
            .orElse(null);
  }

  public boolean bookIsAcceptably(long bookId) {

    List<Issue> issueList = getAllOpenIssue();
    if (issueList.size() == 0) {
      return true;}
    return issueList.stream().noneMatch(x -> x.getBookId() == bookId);
  }

  public boolean readerCanTakeBook(long readerId) {
    List<Issue> issueList = getAllOpenIssue();
    if (issueList.size() == 0) {return true;}
    System.out.println(issueList.stream().filter(x -> x.getReaderId() == readerId).count());
    return issueList.stream().filter(x -> x.getReaderId() == readerId).count() < maxCountBooks;
  }

  public List<Issue> getAllOpenIssue() {
    return issues.stream()
            .filter(issue -> issue.getReturned_at() == null)
            .collect(Collectors.toList());
  }

}
