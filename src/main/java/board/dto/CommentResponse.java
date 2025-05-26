package board.dto;

import board.entity.Comment;
import lombok.Builder;

@Builder
public record CommentResponse(Long id, String content, String author, String createdAt) {
    public static CommentResponse from(Comment c) {
        return CommentResponse.builder()
                .id(c.getId())
                .content(c.getContent())
                .author(c.getAuthor().getUsername())
                .createdAt(c.getCreatedAt().toString())
                .build();
    }
}
