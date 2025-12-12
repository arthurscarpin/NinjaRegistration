package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import com.github.arthurscarpin.NinjaRegistration.Missions.MissionModel;
import jakarta.persistence.*;
import lombok.*;

// Entity - Transform the class to a Database Entity
// JPA - Java Persistence API
@Entity
@Table(name = "tb_register")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "missions")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "age")
    private int age;

    @Column(name = "rank")
    @Enumerated(EnumType.STRING)
    private NinjaRank rank;

    // @ManyToOne - A ninja can have one mission
    @ManyToOne
    @JoinColumn(name = "mission_id") // Foreign Key
    private MissionModel missions;
}
