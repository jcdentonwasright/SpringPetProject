package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book" + book);
        repo.add(book);
    }

    @Override
    public boolean removeById(String id) {
        logger.info("remove book with id " + id);
        return repo.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public boolean removeByAuthor(String author) {
        logger.info("remove book with author " + author);
        return repo.removeIf(book -> book.getAuthor().equals(author));
    }

    @Override
    public boolean removeByTitle(String title) {
        logger.info("remove book with title " + title);
        return repo.removeIf(book -> book.getTitle().equals(title));
    }

    @Override
    public boolean removeBySize(Integer size) {
        logger.info("remove book with size " + size);
        return repo.removeIf(book -> book.getSize().equals(size));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit() {
        logger.info("repo INIT");
    }

    private void defaultDestroy() {
        logger.info("repo DESTROY");
    }
}
