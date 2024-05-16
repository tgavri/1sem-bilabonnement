package dk.kea.bilabonnement.controller;

import dk.kea.bilabonnement.service.bilOpretValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class bilabonnementController {

    @GetMapping("/")
    public String forside() {
        return "BilabonnementForside";
    }

    @GetMapping("/OpretBil")
    public String opretBil() {
        return "OpretBil";
    }

    @PostMapping("/bilDataTjek")
    public String bilDataTjek(@RequestParam("stelnummer") String stelnummer,
                              @RequestParam("brand") String brand,
                              @RequestParam("bilmodel") String bilmodel,
                              @RequestParam("type") String type,
                              @RequestParam("nummerplade") String nummerplade,
                              @RequestParam("fuel") String fuel,
                              Model model
    ) {

        bilOpretValidation validation = new bilOpretValidation();

        model.addAttribute("stelnummer", stelnummer);
        model.addAttribute("brand", brand);
        model.addAttribute("bilmodel", bilmodel);
        model.addAttribute("type", type);
        model.addAttribute("nummerplade", nummerplade);
        model.addAttribute("fuel", fuel);

        String fejlbesked = null;

        if (!validation.validateStelnummer(stelnummer)) {
            fejlbesked = "Ugyldigt Stelnummer";
            model.addAttribute(fejlbesked);
        } else if (!validation.validateBrand(brand)) {
            fejlbesked = "Ugyldigt Brand";
            model.addAttribute(fejlbesked);
        } else if (!validation.validateModel(bilmodel)) {
            fejlbesked = "Ugyldig Model";
            model.addAttribute(fejlbesked);
        } else if (!validation.validateNummerplade(nummerplade)) {
            fejlbesked = "Ugyldig Nummerplade";
            model.addAttribute(fejlbesked);
        }

        if (fejlbesked != null) {
            return "OpretBilFejl";
        }


        return "BilabonnementForside";
    }

    @GetMapping("/OpretBilFejl")
    public String opretBilFejl() {
        return "OpretBilFejl";
    }

}
