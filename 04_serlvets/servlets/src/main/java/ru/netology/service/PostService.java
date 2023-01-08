package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public ConcurrentHashMap<Long, Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }
    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        if (repository.all().containsKey(id)) {
            repository.removeById(id);
            System.out.println("Deleted post #" + id);
        } else System.out.println("Nothing to delete");
    }
}

