package board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import board.dto.PostRequest;
import board.dto.PostResponse;
import board.service.PostService;
import board.service.UserService;

import java.util.List;

@RestController @RequestMapping("/api/posts") @RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public PostResponse create(@RequestParam Long userId,
                               @RequestBody @Valid PostRequest req) {
        return PostResponse.from(
                postService.create(req, userService.findById(userId))
        );
    }

    @GetMapping
    public List<PostResponse> list() {
        return postService.findAll()
                .stream().map(PostResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PostResponse detail(@PathVariable Long id) {
        return PostResponse.from(postService.findById(id));
    }
}
