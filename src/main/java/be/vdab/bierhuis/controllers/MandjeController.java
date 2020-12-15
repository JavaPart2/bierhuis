package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.forms.AantalbakkenForm;
import be.vdab.bierhuis.forms.MandjeForm;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BierService bierService;

    public MandjeController(Mandje mandje, BierService bierService) {
        this.mandje = mandje;
        this.bierService = bierService;
    }

    @GetMapping("{id}/{bakken}")
    public String toevoegenInMandje(@PathVariable int id, int bakken){
        mandje.voegToe(id, bakken);
        return "redirect:/mandje";
    }

    @GetMapping
    public ModelAndView toonMandje(){
        ModelAndView modelAndView = new ModelAndView("mandje");
        modelAndView.addObject("bestellijst", bierService.composeBestelLijstForm(mandje));
        modelAndView.addObject(new MandjeForm());
        return modelAndView;
    }


}
