package com.github.arthurscarpin.NinjaRegistration.Missions;

import com.github.arthurscarpin.NinjaRegistration.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_mission")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rank")
    private MissionRank rank;

    // @OneToMany - A mission can have many ninjas
    @OneToMany(mappedBy = "missions")
    private List<NinjaModel> ninja;
}
