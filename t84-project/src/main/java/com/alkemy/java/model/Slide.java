package com.alkemy.java.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(
        name = "UniqueOrderForSlidesInOrganization",
        columnNames = { "_order", "organization_id" }) })
public class Slide{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "image_url", length = 800)
    private String imageUrl;

    @Column (name = "text")
    private String text;

    @Column (name = "_order", nullable = false)
    private Integer order;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Organization organization;

}