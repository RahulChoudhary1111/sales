package com.sales.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import static com.sales.utils.Utils.getCurrentMillis;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "store")
@Where(clause = "is_deleted != 'Y'")
public class Store implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "slug")
    String slug;
    @Column(name = "name")
    String storeName;
    @Column(name = "address")
    String address;
    @Column(name = "longitude")
    Float longitude;
    @Column(name = "latitude")
    Float latitude;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "discription")
    String description;
    @Column(name = "rating")
    Float rating;
    @Column(name = "status")
    String status;
    @Column(name = "is_deleted")
    String isDeleted="N";
    @Column(name = "created_at")
    Long createdAt;
    @Column(name = "created_by")
    int createdBy;
    @Column(name = "updated_at")
    Long updatedAt;
    @Column(name = "updated_by")
    Integer updatedBy;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;



    public Store (User loggedUser) {
        this.createdAt = getCurrentMillis();
        this.createdBy = loggedUser.getId();
        this.updatedAt = getCurrentMillis();
        this.updatedBy = loggedUser.getId();
        this.status = "A";
        this.isDeleted = "N";
        this.slug = UUID.randomUUID().toString();
    }

}
