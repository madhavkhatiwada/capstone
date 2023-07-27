package capstone.onlineforum.repository;

import capstone.onlineforum.models.Comment;
import capstone.onlineforum.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
