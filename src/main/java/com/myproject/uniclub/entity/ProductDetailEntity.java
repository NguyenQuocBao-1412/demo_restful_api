package com.myproject.uniclub.entity;

import com.myproject.uniclub.entity.key.ProductDetailID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "productdetail")
public class ProductDetailEntity {
    @EmbeddedId
    private ProductDetailID id;

    @Column(name = "Soluong")
    private int soLuong;

    public ProductDetailID getId() {
        return id;
    }

    public void setId(ProductDetailID id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
