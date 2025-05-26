package board.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import board.dto.CommentRequest;
import board.entity.Comment;
import board.entity.Post;
import board.entity.User;
import board.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepo;

    @Transactional
    public Comment create(CommentRequest req, Post post, User author) {
        Comment comment = Comment.builder()
                .content(req.getContent())
                .post(post)
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();
        return commentRepo.save(comment);
    }

    public List<Comment> findByPost(Post post) {
        return commentRepo.findByPostOrderByCreatedAt(post);
    }
}
