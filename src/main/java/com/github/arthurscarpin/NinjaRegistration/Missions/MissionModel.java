package com.github.arthurscarpin.NinjaRegistration.Missions;

import com.github.arthurscarpin.NinjaRegistration.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_mission")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private MissionRank rank;

    // @OneToMany - A mission can have many ninjas
    @OneToMany(mappedBy = "missions")
    private NinjaModel ninja;
}
