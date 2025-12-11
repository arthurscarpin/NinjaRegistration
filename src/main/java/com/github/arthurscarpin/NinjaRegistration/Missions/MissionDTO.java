package com.github.arthurscarpin.NinjaRegistration.Missions;

import com.github.arthurscarpin.NinjaRegistration.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MissionDTO {

    private Long id;

    private String name;

    private MissionRank rank;

    private List<NinjaModel> ninja;
}
