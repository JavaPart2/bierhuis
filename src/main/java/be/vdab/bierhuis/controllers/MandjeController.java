package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.forms.AantalbakkenForm;
import be.vdab.bierhuis.forms.MandjeForm;
import be.vdab.bierhuis.services.BierService;
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
//    private final Mandje mandje;
    private final BierService bierService;
    private final MyControllerAdvice myControllerAdvice;

    public MandjeController(BierService bierService, MyControllerAdvice myControllerAdvice) {
//        this.mandje = mandje;
        this.bierService = bierService;
        this.myControllerAdvice = myControllerAdvice;
    }

    @GetMapping("{id}")
    public String toevoegenInMandje(@PathVariable int id, @Valid AantalbakkenForm form,
                                    Errors errors){
        if (errors.hasErrors()){
            return "redirect:/bieren/" + id + "/form";
        }
        myControllerAdvice.getMandje().voegToe(id, form.getAantalBakken());
        return "redirect:/mandje";
    }

    @GetMapping
    public ModelAndView toonMandje(){
        ModelAndView modelAndView = new ModelAndView("mandje");
        modelAndView.addObject("mandjeForm",
                bierService.composeBestelLijstForm(myControllerAdvice.getMandje()));
        return modelAndView;
    }

    @PostMapping
    public ModelAndView bestellen(@Valid MandjeForm form, Errors errors){
        int bestelbonid = bierService.insertBestelling(form, myControllerAdvice.getMandje());
        myControllerAdvice.getMandje().leegMaken();
        ModelAndView modelAndView = new ModelAndView("bestelling");
        modelAndView.addObject("bestelbonid", bestelbonid);
        return modelAndView;

    }


}
