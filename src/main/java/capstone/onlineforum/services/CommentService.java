package capstone.onlineforum.services;

import capstone.onlineforum.models.Comment;
import capstone.onlineforum.models.Post;

import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment);
    void deleteComment(Long commentId);
    List<Comment> getAllCommentsByPost(Post post);
    Comment getCommentById(Long commentId);
    List<Comment> getAllComments();
}
