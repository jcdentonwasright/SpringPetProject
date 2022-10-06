package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retrieveAll();

    void store(T book);

    boolean removeById(String id);

    boolean removeByAuthor(String author);

    boolean removeByTitle(String title);

    boolean removeBySize(Integer size);
}
