package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Stub
public class PostRepository {
    ConcurrentHashMap<Long, String> repositoryMap = new ConcurrentHashMap<>();
    static long counter = 0;

    public List<Post> all() {
        return Collections.emptyList();
    }

    public Optional<Post> getById(long id) {
        return Optional.empty();
    }

    public Post save(Post post) throws Exception {
        if (post.getId() == 0) {
            counter++;
            repositoryMap.put(counter, post.getContent());
        }

        if (post.getId() != 0) {
            if (repositoryMap.containsKey(post.getId())) {
                repositoryMap.put(post.getId(), post.getContent());
            } else
                throw new Exception("Такой \"id\" не существует");
        }

        return post;
    }

    public void removeById(long id) {
        if (repositoryMap.containsKey(id)) {
            repositoryMap.remove(id, repositoryMap.get(id));
        }
    }
}
