package com.myproject.uniclub.entity;

import jakarta.persistence.*;

/**
 *
 * B1 : khóa chính 2 bảng tự động tăng thì là OneToMany
 * B2 :
 *
 * (*) : OneToMany : entity nào có khóa ngoại thì sẽ có 2 anotation (@ManyToOne và @JoinColumn)
 */
@Entity(name = "user")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullname;

//    @Column(name = "avatar")
//    private String avatar;

    @ManyToOne
    @JoinColumn(name = "id_roles")
    private RolesEntity role_id;

//    @Column(name = "phone_number")
//    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

    public RolesEntity getRole_id() {
        return role_id;
    }

    public void setRole_id(RolesEntity role_id) {
        this.role_id = role_id;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
