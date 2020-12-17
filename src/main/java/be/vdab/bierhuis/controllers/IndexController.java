package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.services.BierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final BierService bierService;
    private final MyControllerAdvice myControllerAdvice;


    public IndexController(BierService bierService, MyControllerAdvice myControllerAdvice) {
        this.bierService = bierService;
        this.myControllerAdvice = myControllerAdvice;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("aantalBieren", bierService.countAll());
        return modelAndView;
    }
}
