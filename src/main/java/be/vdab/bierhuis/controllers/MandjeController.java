package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.forms.AantalbakkenForm;
import be.vdab.bierhuis.forms.MandjeForm;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BierService bierService;

    public MandjeController(Mandje mandje, BierService bierService) {
        this.mandje = mandje;
        this.bierService = bierService;
    }

    @GetMapping("{id}")
    public String toevoegenInMandje(@PathVariable int id, @Valid AantalbakkenForm form,
                                    Errors errors){
        if (errors.hasErrors()){
            return "redirect:/bieren/" + id + "/form";
        }
        mandje.voegToe(id, form.getAantalBakken());
        return "redirect:/mandje";
    }

    @GetMapping
    public ModelAndView toonMandje(){
        ModelAndView modelAndView = new ModelAndView("mandje");
        modelAndView.addObject("mandjeForm", bierService.composeBestelLijstForm(mandje));
//        modelAndView.addObject(new MandjeForm());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView bestellen(@Valid MandjeForm form, Errors errors){
        int bestelbonid = bierService.insertBestelling(form);
        ModelAndView modelAndView = new ModelAndView("bestelling");
        modelAndView.addObject("bestelbonid", bestelbonid);
        return modelAndView;

    }


}
