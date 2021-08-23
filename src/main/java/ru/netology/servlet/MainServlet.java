package ru.netology.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.netology.config.JavaConfig;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MainServlet extends HttpServlet {
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    private PostController controller;
    private static final String ENDPOINT = "/api/posts/";

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            // primitive routing
            if (method.equals("GET")) {
                doGet(req, resp);
                return;
            }
            if (method.equals("POST")) {
                doPost(req, resp);
                return;
            }
            if (method.equals("DELETE")) {
                doDelete(req, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();
        if (path.equals(ENDPOINT)) {
            controller.all(resp);
            return;
        }
        if (path.matches(ENDPOINT + "\\d+")) {
            // easy way
            try {
                controller.getById(getId(path), resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();
        if (path.equals(ENDPOINT)) {
            try {
                controller.save(req.getReader(), resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var path = req.getRequestURI();
        if (path.matches(ENDPOINT + "\\d+")) {
            // easy way
            try {
                controller.removeById(getId(path), resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long getId(String path) {
        return Long.parseLong(path.substring(path.lastIndexOf("/")));
    }
}


