package com.cepardov.cms.entity;

import com.cepardov.cms.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class Audio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String location;

    @NotNull
    private String name;

    private String description;

    private Timestamp createAt;

    @JsonIgnoreProperties(value={"audio", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @PrePersist
    public void prePersist() {
        this.createAt = new DateUtil().getDateTimeFromNtpServer();
    }
}
