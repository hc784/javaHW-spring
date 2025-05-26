package board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import board.dto.CommentRequest;
import board.dto.CommentResponse;
import board.service.CommentService;
import board.service.PostService;
import board.service.UserService;

import java.util.List;

@RestController @RequestMapping("/api/posts/{postId}/comments") @RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public CommentResponse write(@PathVariable Long postId,
                                 @RequestParam Long userId,
                                 @RequestBody @Valid CommentRequest req) {
        return CommentResponse.from(
                commentService.create(
                        req,
                        postService.findById(postId),
                        userService.findById(userId)
                )
        );
    }

    @GetMapping
    public List<CommentResponse> list(@PathVariable Long postId) {
        return commentService.findByPost(postService.findById(postId))
                .stream().map(CommentResponse::from).toList();
    }
}
