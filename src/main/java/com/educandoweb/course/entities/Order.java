package com.educandoweb.course.entities;

import java.time.Instant;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  public Order() {
  }

  public Order(Long id, Instant momoment, OrderStatus orderStatus, User client) {
    this.id = id;
    this.momoment = momoment;
    this.orderStatus = orderStatus.getCode();
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

}
