package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long basketId;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_basket",
            joinColumns = {@JoinColumn(name = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> productsList;

    @Column(name = "is_ordered")
    private boolean isOrdered;

    public Basket() {
    }

    public Basket(User user) {
        this.user = user;
        this.productsList = new ArrayList<>();
    }

    public Basket(Long basketId, boolean isOrdered, User user) {
        this.basketId = basketId;
        this.user = user;
        this.productsList = new ArrayList<>();
        this.isOrdered = isOrdered;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void addProduct(Product product) {
        productsList.add(product);
    }

    public void removeProduct(Integer index) {
        Product product = productsList.get(index);
        productsList.remove(product);
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return isOrdered == basket.isOrdered &&
                Objects.equals(basketId, basket.basketId) &&
                Objects.equals(user, basket.user) &&
                Objects.equals(productsList, basket.productsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, user, productsList, isOrdered);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "basketId=" + basketId +
                ", user=" + user +
                ", productsList=" + productsList +
                ", isOrdered=" + isOrdered +
                '}';
    }
}
