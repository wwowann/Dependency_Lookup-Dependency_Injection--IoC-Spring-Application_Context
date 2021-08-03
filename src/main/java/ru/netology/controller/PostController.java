package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
// прием запросов и подготовка ответов
public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);// записывается в JSON формате ответ
    final var data = service.all();//получение всех записей репозитория через слой Service
    final var gson = new Gson();//создание Jsona
    response.getWriter().print(gson.toJson(data));// отправка обратно ответа в виде Jsona
  }

  public void getById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
  }

  public void save(Reader body, HttpServletResponse response) throws Exception {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
  }
}
