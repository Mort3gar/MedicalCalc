package com.example.MedicalCalc.MedicalCalc.Controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class FrontEndController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("name", "Medical Calc");
        log.info("GET / or /index");
        return "index";
    }
    @GetMapping({"/body-mass-index"})
    public String bodyMassIndex(Model model) {
        return "bmi_page";
    }
    @GetMapping({"/average-blood-pressure"})
    public String averageBloodPressure(Model model) {
        return "average-blood-pressure_page";
    }

    @GetMapping({"/titration-rate"})
    public String home(Model model) {
        return "titration-rate_page";
    }
}
