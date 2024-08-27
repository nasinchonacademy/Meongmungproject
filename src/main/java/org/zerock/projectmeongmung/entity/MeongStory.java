package org.zerock.projectmeongmung.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "meongstory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeongStory extends BaseEntity1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", updatable = false)
    private Long seq;

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "likecount", nullable = false)
    private int likecount;

    @Lob
    @Column(name = "picture")
    private String picture;

    @Column(name = "viewcount", nullable = false)
    private int viewcount;

    @Column(name = "category", nullable = false, length = 40)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberid", nullable = false)
    private User user;

    // StoryLike 엔티티와의 관계 설정
    @OneToMany(mappedBy = "storySeq")
    private Set<StoryLike> likes;

    @Builder
    public MeongStory(String title, String content, int likecount, String picture,
                      int viewcount, String category, User user) {
        this.title = title;
        this.content = content;
        this.likecount = likecount;
        this.picture = picture;
        this.viewcount = viewcount;
        this.category = category;
        this.user = user;
    }

    @Override
    public String toString() {
        return "MeongStory{" +
                "seq=" + seq +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likecount=" + likecount +
                ", count=" + viewcount +
                ", category='" + category + '\'' +
                ", regdate=" + getRegdate() +
                ", modified=" + getModified() +
                ", deleted=" + getDeleted() +
                '}';
    }
}
