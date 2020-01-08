package com.cepardov.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String location;

    @NotNull
    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties(value={"video", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}
