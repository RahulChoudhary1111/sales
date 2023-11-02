package com.sales.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
@Where(clause = "is_deleted != 'Y' ")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int id;
    @Column(name = "slug")
    String slug;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email")
    String email;
    @Column(name = "contact",length = 12 , nullable = true)
    String contact;
    @Column(name = "user_type")
    String userType;
    @Column(name = "status")
    String status;
    @Column(name = "is_deleted")
    String isDeleted="N";
    @Column(name = "created_at")
    Long createdAt;
    @Column(name = "updated_at")
    Long updatedAt;
    @Column(name = "created_by")
    Integer createdBy;
    @Column(name = "updated_by")
    Integer updatedBy;
}
