package board.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import board.dto.PostRequest;
import board.entity.Post;
import board.entity.User;
import board.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepo;

    @Transactional
    public Post create(PostRequest req, User author) {
        Post post = Post.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();
        return postRepo.save(post);
    }

    public List<Post> findAll() { return postRepo.findAll(); }

    public Post findById(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }
}
