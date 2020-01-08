package com.cepardov.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "debe tener un título")
    private String title;

    private String description;

    @NotNull(message = "debe tener un cuerpo")
    private String body;

    private String defaultImage;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties(value={"notice", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Audio> audios;

    @JsonIgnoreProperties(value={"notice", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Image> images;

    @JsonIgnoreProperties(value={"notice", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Video> videos;

    @JsonIgnoreProperties(value={"notice", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}
