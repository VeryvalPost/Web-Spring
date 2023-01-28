package ru.netology.controller;

import com.google.gson.Gson;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    //public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<Post> all() {
        //response.setContentType(APPLICATION_JSON);
        //final var data = service.all();
        //final var gson = new Gson();
        //response.getWriter().print(gson.toJson(data));
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) throws IOException {
        //response.setContentType(APPLICATION_JSON);
        //final var data = service.getById(id);
        //final var gson = new Gson();
        //response.getWriter().print(gson.toJson(data));
        return service.getById(id);
    }

    @PostMapping
    public Post save(Post post) throws IOException {
        //response.setContentType(APPLICATION_JSON);
        //final var gson = new Gson();
        //final var post = gson.fromJson(body, Post.class);
        //final var data = service.save(post);
        //response.getWriter().print(gson.toJson(data));
        return service.save(post);
    }
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        //response.setContentType(APPLICATION_JSON);
        service.removeById(id);
    }

  }
