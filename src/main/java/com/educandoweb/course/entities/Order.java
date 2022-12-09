package com.educandoweb.course.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", timezone = "GMT")
  private Instant momoment;

  private Integer orderStatus;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private User client;

  @JsonIgnore
  @OneToMany(mappedBy = "id.order")
  private Set<OrderItem> items = new HashSet<>();

  @JsonIgnore
  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Payment payment;

  public Order() {
  }

  public Order(Long id, Instant momoment, OrderStatus orderStatus, User client) {
    this.id = id;
    this.momoment = momoment;
    setOrderStatus(orderStatus);
    this.client = client;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getMomoment() {
    return momoment;
  }

  public void setMomoment(Instant momoment) {
    this.momoment = momoment;
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  public OrderStatus getOrderStatus() {
    return OrderStatus.valueOf(orderStatus);
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    if (orderStatus != null) {
      this.orderStatus = orderStatus.getCode();
    }
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
