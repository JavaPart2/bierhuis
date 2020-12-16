package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {
    private final Mandje mandje;

    public MyControllerAdvice(Mandje mandje) {
        this.mandje = mandje;
    }

    public Mandje getMandje() {
        return mandje;
    }

    @ModelAttribute
    void extraDataVoorModel(Model model){
        model.addAttribute(mandje);
    }
}
