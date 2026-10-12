package com.bloodlink.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blood_types")
public class BloodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", nullable = false, unique = true, length = 5)
    private String type;

    // Default constructor
    public BloodType() {
    }

    // Constructor
    public BloodType(String type) {
        this.type = type;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    // Setter
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter
    public String getType() {
        return type;
    }

    // Setter
    public void setType(String type) {
        this.type = type;
    }
}