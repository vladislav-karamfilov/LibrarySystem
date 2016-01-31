package com.librarysystem.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Length(max = 50)
    private String ip;

    @NotNull
    @Max(10)
    private byte value;

    @ManyToOne
    @JoinColumn(updatable = false, nullable = false)
    private PublicationWork publicationWork;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public byte getValue() {
        return this.value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public PublicationWork getPublicationWork() {
        return this.publicationWork;
    }

    public void setPublicationWork(PublicationWork publicationWork) {
        this.publicationWork = publicationWork;
    }
}
