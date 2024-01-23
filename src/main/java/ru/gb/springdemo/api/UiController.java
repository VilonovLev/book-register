package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class UiController {

    private final BookService bookService;
    private final ReaderService readerService;
    private final IssuerService issuerService;

    @GetMapping("/books")
    public String available(Model model) {
        model.addAttribute("books",bookService.getAllAccessibleBooks());
        return "available";
    }

    @GetMapping("/readers")
    public String readers(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "readers";
    }

    @GetMapping("/issues")
    public String issues  (Model model) {

        for (Issue issue :issuerService.getIssues()) {
            model.addAllAttributes(List.of(
            model.addAttribute("book",bookService.getBookById(issue.getBookId())),
            model.addAttribute("reader",readerService.getReaderById(issue.getReaderId())),
            model.addAttribute("issue",issue)));
        }
        return "table";
    }
}
