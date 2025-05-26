package board.dto;


import board.entity.Post;
import lombok.Builder;

@Builder
public record PostResponse(Long id, String title, String content, String author, String createdAt) {
    public static PostResponse from(Post p) {
        return PostResponse.builder()
                .id(p.getId())
                .title(p.getTitle())
                .content(p.getContent())
                .author(p.getAuthor().getUsername())
                .createdAt(p.getCreatedAt().toString())
                .build();
    }
}
