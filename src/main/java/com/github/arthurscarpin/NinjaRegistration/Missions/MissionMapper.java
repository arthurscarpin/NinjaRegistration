package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

    public MissionModel map(MissionDTO missionDTO) {
        MissionModel missionModel = new MissionModel();
        missionModel.setId(missionDTO.getId());
        missionModel.setName(missionDTO.getName());
        missionModel.setRank(missionDTO.getRank());
        missionModel.setNinja(missionDTO.getNinja());
        return missionModel;
    }

    public MissionDTO map(MissionModel missionModel) {
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setId(missionModel.getId());
        missionDTO.setName(missionModel.getName());
        missionDTO.setRank(missionModel.getRank());
        missionDTO.setNinja(missionModel.getNinja());
        return missionDTO;
    }
}
