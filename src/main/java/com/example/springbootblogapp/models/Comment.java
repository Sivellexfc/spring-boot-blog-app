package com.example.springbootblogapp.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
=======
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
>>>>>>> origin/master
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long postIdTemp;

    @OneToOne
    private Account account;

    @ManyToOne
<<<<<<< HEAD
    @JsonIgnore
=======
>>>>>>> origin/master
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 450)
    private String content;
    private LocalDateTime publishedDate;

    private int likeCount;

    public Comment(){
        this.likeCount = 0;
        this.publishedDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
