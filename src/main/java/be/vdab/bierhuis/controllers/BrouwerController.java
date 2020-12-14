package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("brouwers")
public class BrouwerController {
    private final BrouwerService brouwerService;

    public BrouwerController(BrouwerService brouwerService) {
        this.brouwerService = brouwerService;
    }

    @GetMapping
    public ModelAndView brouwers(){

        return new ModelAndView("brouwers", "brouwers",
                brouwerService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView brouwer(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("brouwer");
        brouwerService.findById(id).ifPresent(brouwer -> {
            modelAndView.addObject("brouwer", brouwer);
        });
        return modelAndView;
    }

}
