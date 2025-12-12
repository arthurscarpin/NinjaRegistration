package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import com.github.arthurscarpin.NinjaRegistration.Missions.MissionDTO;
import com.github.arthurscarpin.NinjaRegistration.Missions.MissionMapper;
import com.github.arthurscarpin.NinjaRegistration.Missions.MissionModel;
import com.github.arthurscarpin.NinjaRegistration.Missions.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninja/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    private final MissionService missionService;

    private final MissionMapper missionMapper;

    public NinjaControllerUI(NinjaService ninjaService, MissionService missionService, MissionMapper missionMapper) {
        this.ninjaService = ninjaService;
        this.missionService = missionService;
        this.missionMapper = missionMapper;
    }

    @GetMapping("/create")
    public String createNinjaForm(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missions", missionService.listAll());
        return "createNinja";
    }

    @PostMapping("/save")
    public String saveCreateNinjaForm(@ModelAttribute NinjaDTO ninjaDTO, RedirectAttributes redirectAttributes) {
        ninjaService.create(ninjaDTO);
        redirectAttributes.addFlashAttribute("message", "Ninja created successfully!");
        return "redirect:/ninja/ui/list";
    }

    @GetMapping("/list")
    public String listAllNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listAll();
        model.addAttribute("ninjas", ninjas);
        return "listNinjas";
    }

    @GetMapping("/list/{id}")
    public String listNinjaById(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listById(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detailsNinja";
        } else {
            model.addAttribute("message", "Ninja not found.");
            return "listNinjas";
        }
    }

    @GetMapping("/alter/{id}")
    public String updateNinjaForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        NinjaDTO ninja = ninjaService.listById(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            model.addAttribute("missions", missionService.listAll());
            return "alterNinja";
        } else {
            redirectAttributes.addFlashAttribute("error", "Ninja not found!");
            return "redirect:/ninja/ui/list";
        }
    }

    @PostMapping("/alter/{id}")
    public String saveUpdateNinjaForm(@PathVariable Long id,
                                      @ModelAttribute NinjaDTO ninjaDTO,
                                      RedirectAttributes redirectAttributes) {
        NinjaDTO existingNinjaDTO = ninjaService.listById(id);
        if (existingNinjaDTO == null) {
            redirectAttributes.addFlashAttribute("error", "Ninja not found!");
            return "redirect:/ninja/ui/list";
        }
        Long missionId = ninjaDTO.getMissions().getId();
        MissionDTO missionDTO = missionService.listById(missionId);
        ninjaDTO.setMissions(missionMapper.map(missionDTO));
        ninjaDTO.setId(id);
        ninjaService.updateById(id, ninjaDTO);
        redirectAttributes.addFlashAttribute("message", "Ninja updated successfully!");
        return "redirect:/ninja/ui/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteNinjaById(@PathVariable Long id) {
        ninjaService.deleteById(id);
        return "redirect:/ninja/ui/list";
    }
}
