package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/books")
@Scope("singleton")
public class BookShelfController {
    private final Logger logger = Logger.getLogger(BookShelfController.class);
    private final BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info(this.toString());
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        if (!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || !(book.getSize() == null)) {
            bookService.saveBook(book);
            logger.info("current repository size : " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeById")
    public String removeBookById(@RequestParam(value = "id") String id) {
        Optional<Book> deletedBook = bookService.getAllBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        if (bookService.removeBookById(id)) {
            logger.info("remove book :" +
                    deletedBook.get() +
                    "current repository size : " +
                    bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@RequestParam(value = "author") String author) {
        List<Book> deletedBooks = bookService.getAllBooks()
                .stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
        if (bookService.removeBookByAuthor(author)) {
            logger.info("remove book :" +
                    deletedBooks +
                    "current repository size : " +
                    bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByTitle")
    public String removeBookByTitle(@RequestParam(value = "title") String title) {
        List<Book> deletedBooks = bookService.getAllBooks()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
        if (bookService.removeBookByTitle(title)) {
            logger.info("remove book :" +
                    deletedBooks + "" +
                    "current repository size : " +
                    bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeBySize")
    public String removeBookBySize(@RequestParam(value = "size") Integer size) {
        List<Book> deletedBooks = bookService.getAllBooks().
                stream()
                .filter(book -> book.getSize().equals(size))
                .collect(Collectors.toList());
        if (bookService.removeBookBySize(size)) {
            logger.info("remove book :" +
                    deletedBooks + "current repository size : " +
                    bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
        return "redirect:/books/shelf";
    }
}