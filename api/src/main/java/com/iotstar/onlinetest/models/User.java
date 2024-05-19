package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "LONGTEXT")
    private String avatar;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int status;

    @Column
    private String gender;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreate;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Account account;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<History> histories;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    @JsonIgnore
    private Subject subject;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Question> questions;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private WishList wishList;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Review review;
}
