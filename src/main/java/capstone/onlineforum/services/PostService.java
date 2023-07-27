package capstone.onlineforum.services;

import capstone.onlineforum.models.Post;
import capstone.onlineforum.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



public interface PostService {

    Optional<Post> getById(Long id);
    List<Post> getAll();
    Post save(Post post);
    void delete(Post post);
    Post getPostById(Long PostId);

}
