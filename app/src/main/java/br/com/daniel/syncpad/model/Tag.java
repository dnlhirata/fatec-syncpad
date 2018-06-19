package br.com.daniel.syncpad.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Tag implements Serializable {
    @Exclude
    private String id;
    private String name;
    private String content;

    public Tag(String tagName) {
        this.name = tagName;
    }

    public Tag(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Tag(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public Tag() {
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
        return this.name;
    }
}
