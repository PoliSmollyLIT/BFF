package com.tinqin.academy.persistence.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID user;
    @Column(nullable = false)
    private Double price;
    @OneToMany(mappedBy="cart")
    private List<CartItem> items;


}
