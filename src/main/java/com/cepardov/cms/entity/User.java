package com.cepardov.cms.entity;

import com.cepardov.cms.util.DateUtil;
import com.cepardov.cms.validator.Rut;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Rut
    @Size(min = 9, max = 12, message = "El tamaño debe estar entre 9 y 12")
    @Column(nullable = false, unique = true)
    private String socialId;

    @NotEmpty(message = "no purede estar vacío")
    private String firstName;

    @NotEmpty(message = "no puede estar vacío")
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    private Boolean enabled;

    private Timestamp createAt;

    @JsonIgnoreProperties(value={"user", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Post> posts;

    @PrePersist
    public void prePersist() {
        this.createAt = new DateUtil().getDateTimeFromNtpServer();
    }

    @PreRemove
    private void preRemove() {
        for (Post post : posts) {
            post.setUser(null);
        }
    }
}
