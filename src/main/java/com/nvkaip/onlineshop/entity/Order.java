package com.nvkaip.onlineshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "code_id")
    private Code validationCode;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Column(name = "is_valid_code")
    private boolean isValidCode;

    public Order() {
    }

    public Order(Long orderId, String address, boolean isValidCode) {
        this.orderId = orderId;
        this.address = address;
        this.isValidCode = isValidCode;
    }

    public Order(Code validationCode) {
        this.validationCode = validationCode;
        this.isValidCode = false;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Code getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(Code validationCode) {
        this.validationCode = validationCode;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public boolean isValidCode() {
        return isValidCode;
    }

    public void setValidCode(boolean validCode) {
        isValidCode = validCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return isValidCode == order.isValidCode &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(address, order.address) &&
                Objects.equals(validationCode, order.validationCode) &&
                Objects.equals(basket, order.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, address, validationCode, basket, isValidCode);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", address='" + address + '\'' +
                ", validationCode=" + validationCode +
                ", basket=" + basket +
                ", isValidCode=" + isValidCode +
                '}';
    }
}
