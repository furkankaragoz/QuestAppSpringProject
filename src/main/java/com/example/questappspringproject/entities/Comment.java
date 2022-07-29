package com.example.questappspringproject.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "comment")
@Data
public class Comment {


    @Id
    Long id;

    Long userId;
    Long postId;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}


