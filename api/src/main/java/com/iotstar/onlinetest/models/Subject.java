package com.iotstar.onlinetest.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column(nullable = false)
    private int status;


    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<User> users;



    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Topic> topics;



}
