package com.example.springbootblogapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;

    @Column(name = "TEXT",length = 1200)
    private String body;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id",
                referencedColumnName = "id",
                nullable = false)
    private Account account;

}
