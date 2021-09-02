package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;
import ru.netology.servlet.MainServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration

public class JavaConfig {

    @Bean
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }

    @Bean
    public MainServlet mainServlet() {
        return new MainServlet(postController(postService(postRepository())));
    }
}
/* второй вариант создания бинов
@Configuration
public class ru.netology.config.Config_Class {

    @Bean
    public PostController postController(){
        return new PostController(postService());
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepository();
    }

}
 */

