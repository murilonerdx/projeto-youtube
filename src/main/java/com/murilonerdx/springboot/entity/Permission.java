package com.murilonerdx.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name="tb_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public Permission() {
    }

    public Permission(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
