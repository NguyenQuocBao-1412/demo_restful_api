package com.myproject.uniclub.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;

@Embeddable
public class ProductDetailID implements Serializable {

    @Column(name = "ID_product")
    private int idProduct;

    @Column(name = "ID_tag")
    private int idTag;

    @Column(name = "ID_category")
    private int idCategory;

    @Column(name = "ID_color")
    private int idColor;

    @Column(name = "ID_size")
    private int idSize;

    public ProductDetailID() {}

    public ProductDetailID(int idProduct, int idTag, int idCategory, int idColor, int idSize) {
        this.idProduct = idProduct;
        this.idTag = idTag;
        this.idCategory = idCategory;
        this.idColor = idColor;
        this.idSize = idSize;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }
}
