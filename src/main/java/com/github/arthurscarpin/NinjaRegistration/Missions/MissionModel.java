package com.github.arthurscarpin.NinjaRegistration.Missions;

import com.github.arthurscarpin.NinjaRegistration.Ninjas.NinjaModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_mission")
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private MissionRank rank;

    private NinjaModel ninja;

    public MissionModel() {
    }

    public MissionModel(String name, MissionRank rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MissionRank getRank() {
        return rank;
    }

    public void setRank(MissionRank rank) {
        this.rank = rank;
    }
}
