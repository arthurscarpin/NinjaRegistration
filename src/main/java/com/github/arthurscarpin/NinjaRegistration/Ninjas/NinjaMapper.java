package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO ninjaDTO) {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setName(ninjaDTO.getName());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setImageUrl(ninjaDTO.getImageUrl());
        ninjaModel.setAge(ninjaDTO.getAge());
        ninjaModel.setMissions(ninjaDTO.getMissions());
        ninjaModel.setRank(ninjaDTO.getRank());
        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setName(ninjaModel.getName());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setImageUrl(ninjaModel.getImageUrl());
        ninjaDTO.setAge(ninjaModel.getAge());
        ninjaDTO.setMissions(ninjaModel.getMissions());
        ninjaDTO.setRank(ninjaModel.getRank());
        return ninjaDTO;
    }
}
