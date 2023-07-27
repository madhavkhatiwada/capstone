package capstone.onlineforum.controller;

import capstone.onlineforum.models.Comment;
import capstone.onlineforum.models.Post;
import capstone.onlineforum.services.CommentService;
import capstone.onlineforum.services.PostService;
import capstone.onlineforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;



    // Endpoint to create a new comment for a specific post
    @PostMapping("/posts/{postId}/comments")
    public String createComment(@PathVariable Long postId, @ModelAttribute Comment comment) {
        // Fetch the Post object for the given postId
        Post post = postService.getPostById(postId);

        if (post == null) {
            // If the post does not exist, return a custom error view or redirect
            return "error"; // For example, you can have an "error" view in your templates folder
        }

        // Associate the comment with the post
        comment.setPost(post);

        // Save the comment using the CommentService
        Comment savedComment = commentService.saveComment(comment);

        // Redirect to the post's detail page with the newly created comment
        return "redirect:/posts/" + postId;
    }

    // Endpoint to delete a comment by its ID
    @DeleteMapping("/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        // Check if the comment exists
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            // If the comment does not exist, return a custom error view or redirect
            return "error"; // For example, you can have an "error" view in your templates folder
        }

        // Delete the comment using the CommentService
        commentService.deleteComment(commentId);

        // Redirect to the post's detail page after successful deletion
        return "redirect:/posts/" + comment.getPost().getId();
    }

    // Endpoint to get all comments for a specific post
    @GetMapping("/posts/{postId}/comments")
    public String getCommentsForPost(@PathVariable Long postId, Model model) {
        // Fetch the Post object for the given postId
        Post post = postService.getPostById(postId);

        if (post == null) {
            // If the post does not exist, return a custom error view or redirect
            return "error"; // For example, you can have an "error" view in your templates folder
        }

        // Fetch all comments for the given post using the CommentService
        List<Comment> comments = commentService.getAllCommentsByPost(post);

        // Add the comments to the model to be displayed on the post's detail page
        // Assuming you have a view named "postDetail.html" to display the post and its comments
        // You can pass the "comments" list along with other attributes needed for the view.
        // For example:
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        // Return the view name (post.html) to render the page
        return "posts";
    }

    // Add more controller methods as needed for other comment-related operations

//    @GetMapping("/{id}")
//    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
//        Comment comment = commentService.getCommentById(id);
//        if (comment != null) {
//            return new ResponseEntity<>(comment, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/post/{postId}")
//    public ResponseEntity<List<Comment>> getAllCommentsByPost(@PathVariable Long postId) {
//        Optional<Post> optionalPost = postService.getById(postId);
//        if (optionalPost.isPresent()) {
//            List<Comment> comments = commentService.getAllCommentsByPost(optionalPost.get());
//            return new ResponseEntity<>(comments, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
//        Comment savedComment = commentService.saveComment(comment);
//        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
//        Comment comment = commentService.getCommentById(id);
//        if (comment != null) {
//            commentService.deleteComment(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
