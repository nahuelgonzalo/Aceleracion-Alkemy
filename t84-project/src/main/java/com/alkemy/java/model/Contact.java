package com.alkemy.java.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String phone;
    private String email;
    private String message;
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;
    private Long deletedAt;
}
