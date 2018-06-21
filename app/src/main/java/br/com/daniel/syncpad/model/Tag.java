package br.com.daniel.syncpad.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Tag implements Serializable {
    @Exclude
    private String id;
    private String name;
    private String content;
    private String dateTime;

    public Tag(String id, String name, String content, String dateTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Tag() {
    }

    public String getDate() {
        return dateTime;
    }

    public void setDate(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.name + " " + this.content + " " + this.dateTime;
    }
}
