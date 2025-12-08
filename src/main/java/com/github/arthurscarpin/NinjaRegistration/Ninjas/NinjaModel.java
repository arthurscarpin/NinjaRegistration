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
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    // @ManyToOne - A ninja can have one mission
    @ManyToOne
    @JoinColumn(name = "mission_id") // Foreign Key
    private MissionModel missions;
}
