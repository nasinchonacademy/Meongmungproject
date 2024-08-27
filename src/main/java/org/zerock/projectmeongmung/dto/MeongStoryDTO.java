package org.zerock.projectmeongmung.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class MeongStoryDTO {
    private long seq; // Sequence ID
    private Long id_story; // Story ID
    private String title; // Story title
    private int likecount; // Like count
    private String content; // Story content
    private LocalDateTime regdate; // Registration date, replacing 'timestamp'
    private LocalDateTime modified; // Modification date, replacing 'timestamp'
    private byte[] picture; // Binary data for picture, replacing 'MIDEUMBLOB'
    private int comment_count; // Comment count
    private int view_count; // View count
    private LocalDateTime deleted; // Deletion date, replacing 'timestamp'
    private String category; // Category, replacing 'varchar2'
    private String uid; // Foreign key to user ID

    // 추가 생성자
    public MeongStoryDTO(long seq, Long id_story, String title, int likecount, String content,
                         LocalDateTime regdate, LocalDateTime modified, byte[] picture,
                         int comment_count, int view_count, LocalDateTime deleted,
                         String category, String uid) {
        this.seq = seq;
        this.id_story = id_story;
        this.title = title;
        this.likecount = likecount;
        this.content = content;
        this.regdate = regdate;
        this.modified = modified;
        this.picture = picture;
        this.comment_count = comment_count;
        this.view_count = view_count;
        this.deleted = deleted;
        this.category = category;
        this.uid = uid; // Foreign key
    }

    // toString() 메서드 (디버깅용)
    @Override
    public String toString() {
        return "MeongStoryDTO{" +
                "seq=" + seq +
                ", id_story=" + id_story +
                ", title='" + title + '\'' +
                ", likecount=" + likecount +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                ", modified=" + modified +
                ", picture=" + picture +
                ", comment_count=" + comment_count +
                ", view_count=" + view_count +
                ", deleted=" + deleted +
                ", category='" + category + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}