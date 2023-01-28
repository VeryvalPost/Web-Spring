package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {

    ConcurrentHashMap<Long, Post> repositoryPost = new ConcurrentHashMap<>();
    AtomicLong mapIndex = new AtomicLong();


    public List<Post> all() {
       List<Post> rep = new ArrayList<>(repositoryPost.size());
       for (Post post: repositoryPost.values()
            ) {
           rep.add(post);
       }
        return rep;
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(repositoryPost.get(id));
    }

    public Post save(Post post) {
        if (mapIndex.get() == 0) {
            Long id = mapIndex.incrementAndGet();
            repositoryPost.put(id, post);
        }

        if (mapIndex.get() != 0) {
            if (repositoryPost.containsKey(post.getId())) {
                repositoryPost.put(post.getId(), post);
            }

            if (post.getId() == (mapIndex.get() + 1)) {
                repositoryPost.put(post.getId(), post);
                mapIndex.getAndIncrement();
            }

            if (post.getId() > mapIndex.get() + 1) {
                post.setId(mapIndex.incrementAndGet());
                repositoryPost.put(mapIndex.get(), post);
                throw new NotFoundException("ID not found, BUT your Post#" + post.getId() + " saved sucsessfully");
            }
        }


        return post;
    }

    public void removeById(long id) {
        repositoryPost.remove(id);
    }
}
