package org.lession.pizza.la_mia_pizzeria.controller;

import org.lession.pizza.la_mia_pizzeria.model.Pizza;
import org.lession.pizza.la_mia_pizzeria.model.SpecialOffer;
import org.lession.pizza.la_mia_pizzeria.repository.PizzaRepository;
import org.lession.pizza.la_mia_pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SpecialOfferRepository offerRepository;

    @GetMapping("/create/{pizzaId}")
    public String createOffer(@PathVariable("pizzaId") Integer pizzaId, Model model) {
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(pizzaId);
        if (pizzaOpt.isPresent()) {
            model.addAttribute("pizza", pizzaOpt.get());
            model.addAttribute("offer", new SpecialOffer());
            return "offers/create";
        } else {
            return "redirect:/pizze";
        }
    }

    @PostMapping("/create")
    public String saveOffer(
        @ModelAttribute("offer") SpecialOffer offer,
        @RequestParam("pizzaId") Integer pizzaId) {

    Optional<Pizza> pizzaOpt = pizzaRepository.findById(pizzaId);
    if (pizzaOpt.isPresent()) {
        Pizza pizza = pizzaOpt.get();
        offer.setPizza(pizza); 
        offerRepository.save(offer);
        return "redirect:/pizze/" + pizza.getId();
    } else {
        return "redirect:/pizze";
    }
}

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable("id") Integer id, Model model) {
        Optional<SpecialOffer> offerOpt = offerRepository.findById(id);
        if (offerOpt.isPresent()) {
            model.addAttribute("offer", offerOpt.get());
            return "offers/edit";
        } else {
            return "redirect:/pizze";
        }
    }

    @PostMapping("/edit")
    public String updateOffer(@ModelAttribute("offer") SpecialOffer offer) {
        offerRepository.save(offer);
        return "redirect:/pizze/" + offer.getPizza().getId();
    }
    @PostMapping("/delete/{id}")
public String deleteOffer(@PathVariable("id") Integer id) {
    Optional<SpecialOffer> offerOpt = offerRepository.findById(id);
    if (offerOpt.isPresent()) {
        Integer pizzaId = offerOpt.get().getPizza().getId();
        offerRepository.deleteById(id);
        return "redirect:/pizze/" + pizzaId;
    } else {
        return "redirect:/pizze";
    }
}
}
