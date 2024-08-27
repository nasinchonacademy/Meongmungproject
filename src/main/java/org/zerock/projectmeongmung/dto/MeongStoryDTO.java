package org.zerock.projectmeongmung.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class MeongStoryDTO {
    private Long seq; // Sequence ID
    private Long idstory; // Story ID
    private String title; // Story title
    private int likecount; // Like count
    private String content; // Story content
    private LocalDateTime regdate; // Registration date, replacing 'timestamp'
    private LocalDateTime modified; // Modification date, replacing 'timestamp'
    private String picture; // Binary data for picture, replacing 'MIDEUMBLOB'
    private int commentcount; // Comment count
    private int viewcount; // View count
    private LocalDateTime deleted; // Deletion date, replacing 'timestamp'
    private String category; // Category, replacing 'varchar2'
    private String uid; // Foreign key to user ID

    // 추가 생성자
    public MeongStoryDTO(Long seq, Long id_story, String title, int likecount, String content,
                         LocalDateTime regdate, LocalDateTime modified, String picture,
                         int commentcount, int viewcount, LocalDateTime deleted,
                         String category, String uid) {
        this.seq = seq;
        this.idstory = id_story;
        this.title = title;
        this.likecount = likecount;
        this.content = content;
        this.regdate = regdate;
        this.modified = modified;
        this.picture = picture;
        this.commentcount = commentcount;
        this.viewcount = viewcount;
        this.deleted = deleted;
        this.category = category;
        this.uid = uid; // Foreign key
    }

    // toString() 메서드 (디버깅용)
    @Override
    public String toString() {
        return "MeongStoryDTO{" +
                "seq=" + seq +
                ", idstory=" + idstory +
                ", title='" + title + '\'' +
                ", likecount=" + likecount +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                ", modified=" + modified +
                ", picture=" + picture +
                ", commentcount=" + commentcount +
                ", viewcount=" + viewcount +
                ", deleted=" + deleted +
                ", category='" + category + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}