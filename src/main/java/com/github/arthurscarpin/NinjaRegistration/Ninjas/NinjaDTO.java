package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import com.github.arthurscarpin.NinjaRegistration.Missions.MissionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NinjaDTO {

    private Long id;

    private String name;

    private String email;

    private String imageUrl;

    private int age;

    private MissionModel missions;

    private NinjaRank rank;
}
