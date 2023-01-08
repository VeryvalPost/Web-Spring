package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    ConcurrentHashMap<Long, Post> repositoryPost = new ConcurrentHashMap<>();
    AtomicLong mapIndex;

    public ConcurrentHashMap<Long, Post> all() {
        return repositoryPost;
    }

    public Optional<Post> getById(long id) {
        return Optional.of(repositoryPost.get(id));
    }

    public Post save(Post post) {

        if (repositoryPost.containsKey(post.getId())) {
            repositoryPost.put(post.getId(), post);
        } else if (post.getId() == (mapIndex.get() + 1)) {
            repositoryPost.put(post.getId(), post);
            mapIndex.getAndIncrement();
        } else if (post.getId() > mapIndex.get() + 1) {
            repositoryPost.put(mapIndex.incrementAndGet(), post);
            post.setId(mapIndex.get() + 1);
            throw new NotFoundException("ID not found, Post#"+ post.getId() + " saved sucsessfully");
        }  else if (post.getId() == 0 ){
           Long id = mapIndex.incrementAndGet();
            repositoryPost.put(id, post);
        }
        return post;
    }

    public void removeById(long id) {
        repositoryPost.remove(id);
    }
}
