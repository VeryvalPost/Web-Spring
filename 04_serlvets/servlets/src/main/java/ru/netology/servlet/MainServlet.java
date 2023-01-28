package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;
    private static final String PATH = "/api/posts";
    private static final String PATH_ID = "/api/posts\\d+";
    @Override
    public void init() {
        //final var repository = new PostRepository();
        //final var service = new PostService(repository);
        //controller = new PostController(service);
        final var context = new AnnotationConfigApplicationContext("ru.netology");
        controller = (PostController) context.getBean("postController");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();

            if (method.equals("GET") && path.equals(PATH)) {
                controller.all(resp);
                return;
            }
            if (method.equals("GET") && path.matches(PATH_ID)) {
                // easy way
                final var id = idStrToLong(PATH);
                controller.getById(id, resp);

                return;
            }
            if (method.equals("POST") && path.equals(PATH)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals("DELETE") && path.matches(PATH_ID)) {
                // easy way
                final var id = idStrToLong(PATH);
                controller.removeById(id, resp);

                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    public long idStrToLong(String path) {
        Long id = Long.parseLong(path.substring(path.lastIndexOf("/")));
        return id;
    }

}

