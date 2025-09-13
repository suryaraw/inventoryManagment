package com.inventory.rootPackage.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "items")
public class Wholesaler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactPerson;
    private Long phone;
    private String email;
    private String address;
    
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Item> items;
}
