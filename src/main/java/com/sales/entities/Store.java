package com.sales.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "store")
public class Store implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "user_id")
    int user_id;
    @Column(name = "slug")
    String slug;
    @Column(name = "name")
    String name;
    @Column(name = "address")
    String address;
    @Column(name = "altitude")
    Float altitude;
    @Column(name = "latitude")
    Float latitude;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "description")
    String description;
    @Column(name = "rating")
    Float rating;
    @Column(name = "status")
    String status;
    @Column(name = "is_deleted")
    String is_deleted;
    @Column(name = "created_at")
    Long created_at;
    @Column(name = "created_by")
    int created_by;
    @Column(name = "updated_at")
    Long updated_at;
}
