package com.cepardov.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 9, max = 12, message = "El tamaño debe estar entre 9 y 12")
    @Column(nullable = false, unique = true)
    private String socialId;

    @NotEmpty(message = "no purede estar vacío")
    private String firstName;

    @NotEmpty(message = "no puede estar vacío")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties(value={"user", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Notice> noticias;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}
