package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.forms.AantalbakkenForm;
import be.vdab.bierhuis.services.BierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("bieren")
public class BierController {
    private final BierService bierService;

    public BierController(BierService bierService) {
        this.bierService = bierService;
    }

    @GetMapping("{id}/form")
    public ModelAndView bierForm(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("bier");
        modelAndView.addObject(new AantalbakkenForm(2));
        bierService.findById(id).ifPresent(bier -> {
            modelAndView.addObject("bier", bier);
        });
        return modelAndView;
    }

}
