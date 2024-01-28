package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    public List<Issue> findByReturned(LocalDateTime dateTime);
}
