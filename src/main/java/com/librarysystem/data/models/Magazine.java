package com.librarysystem.data.models;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Magazine extends PublicationWork {
    @NotNull
    @Min(1)
    private int issue;

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }
}
