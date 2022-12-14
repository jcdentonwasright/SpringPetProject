package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }
    public boolean removeBookById(String id) {
        return bookRepo.removeById(id);
    }
    public boolean removeBookByAuthor(String author) {
        return bookRepo.removeByAuthor(author);
    }
    public boolean removeBookByTitle(String title) {
        return bookRepo.removeByTitle(title);
    }
    public boolean removeBookBySize(Integer size) {
        return bookRepo.removeBySize(size);
    }

}
