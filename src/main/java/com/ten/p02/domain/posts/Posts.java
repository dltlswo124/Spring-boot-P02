package com.ten.p02.domain.posts;

import com.ten.p02.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  //롬복 @
@NoArgsConstructor //롬복 @
@Entity //JPA @
public class Posts extends BaseTimeEntity {

    @Id // posts 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙 , GenerationType.IDENTITY = autoincrement
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
