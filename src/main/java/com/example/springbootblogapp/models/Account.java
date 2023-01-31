package com.example.springbootblogapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;

    private String username;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "account")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {
                    @JoinColumn(name = "account_id" , referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_name",referencedColumnName = "name")
            }
    )
    private Set<Authority> authorities = new HashSet<>();

    public void addPost(Post post){
        posts.add(post);
    }


    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authorities=" + authorities +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
