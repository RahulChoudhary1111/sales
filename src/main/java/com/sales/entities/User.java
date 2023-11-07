package com.sales.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import static com.sales.utils.Utils.getCurrentMillis;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
@Where(clause = "is_deleted != 'Y' ")
public class User {
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
    String isDeleted;
    @Column(name = "created_at")
    Long createdAt;
    @Column(name = "updated_at")
    Long updatedAt;
    @Column(name = "created_by")
    Integer createdBy;
    @Column(name = "updated_by")
    Integer updatedBy;


    public User (User loggedUser) {
        this.createdAt = getCurrentMillis();
        this.createdBy = loggedUser.getId();
        this.updatedAt = getCurrentMillis();
        this.updatedBy = loggedUser.getId();
        this.status = "A";
        this.isDeleted = "N";
    }

}
