package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mission/ui")
public class MissionControllerUI {

    private final MissionService missionService;

    public MissionControllerUI(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping("/create")
    public String createMissionForm(Model model) {
        model.addAttribute("mission", new MissionDTO());
        return "createMission";
    }

    @PostMapping("/save")
    public String saveCreateMissionForm(@ModelAttribute MissionDTO missionDTO,
                                        RedirectAttributes redirectAttributes) {
        missionService.create(missionDTO);
        redirectAttributes.addFlashAttribute("message", "Mission created successfully!");
        return "redirect:/mission/ui/list";
    }

    @GetMapping("/list")
    public String listAllMissions(Model model) {
        List<MissionDTO> missions = missionService.listAll();
        model.addAttribute("missions", missions);
        return "listMissions";
    }

    @GetMapping("/list/{id}")
    public String listMissionById(@PathVariable Long id, Model model) {
        MissionDTO missionDTO = missionService.listById(id);
        if (missionDTO != null) {
            model.addAttribute("mission", missionDTO);
            return "detailsMission";
        } else {
            model.addAttribute("message", "Mission not found.");
            return "listMissions";
        }
    }

    @GetMapping("/alter/{id}")
    public String updateMissionForm(@PathVariable Long id,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        MissionDTO missionDTO = missionService.listById(id);
        if (missionDTO != null) {
            model.addAttribute("mission", missionDTO);
            return "alterMission";
        } else {
            redirectAttributes.addFlashAttribute("error", "Mission not found!");
            return "redirect:/mission/ui/list";
        }
    }

    @PostMapping("/alter/{id}")
    public String saveUpdateMissionForm(@PathVariable Long id,
                                      @ModelAttribute MissionDTO missionDTO,
                                      RedirectAttributes redirectAttributes) {
        missionDTO.setId(id);
        missionService.updateById(id, missionDTO);
        redirectAttributes.addFlashAttribute("message", "Mission updated successfully!");
        return "redirect:/mission/ui/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteMissionById(@PathVariable Long id) {
        missionService.deleteById(id);
        return "redirect:/mission/ui/list";
    }
}
