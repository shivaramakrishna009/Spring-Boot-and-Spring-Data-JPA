package com.krishnaallu009.springBoot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor

public class Author {
    @Id
    /*@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_generator"
    )
    @SequenceGenerator(
            name = "author_generator",
            sequenceName = "author_seq",
            allocationSize = 1
    )*/

    /*@GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "author_generator"
    )
    @TableGenerator(
            name = "author_generator",
            table = "id_gen",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "author_id",
            allocationSize = 1
    )*/

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "f_name",
            length = 35
    )
    private String firstName;

    private String lastName;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    private int age;

    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            insertable = false
    )
    private LocalDateTime updatedAt;

}
