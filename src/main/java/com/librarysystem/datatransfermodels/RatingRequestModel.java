package com.librarysystem.datatransfermodels;

public class RatingRequestModel {
    private long publicationWorkId;

    private byte value;

    public long getPublicationWorkId() {
        return publicationWorkId;
    }

    public void setPublicationWorkId(long publicationWorkId) {
        this.publicationWorkId = publicationWorkId;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
