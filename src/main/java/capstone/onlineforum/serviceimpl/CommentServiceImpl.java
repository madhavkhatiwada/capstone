package capstone.onlineforum.serviceimpl;

import capstone.onlineforum.models.Comment;
import capstone.onlineforum.models.Post;
import capstone.onlineforum.repository.CommentRepo;
import capstone.onlineforum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Override
    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepo.deleteById( commentId);
    }

    @Override
    public List<Comment> getAllCommentsByPost(Post post) {

        return commentRepo.findByPost(post);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        Optional<Comment> optionalComment = commentRepo.findById(commentId);
        return optionalComment.orElse(null);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }
}
