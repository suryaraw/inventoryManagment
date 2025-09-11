package com.inventory.rootPackage.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wholesaler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    
    @ManyToMany(mappedBy = "suppliers")
    private List<Item> items;
}
