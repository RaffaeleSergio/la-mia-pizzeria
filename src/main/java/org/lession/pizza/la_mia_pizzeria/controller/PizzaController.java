package org.lession.pizza.la_mia_pizzeria.controller;

import java.util.Optional;

import org.lession.pizza.la_mia_pizzeria.model.Pizza;
import org.lession.pizza.la_mia_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pizze", pizzaRepository.findAll());
        return "pizze/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizze/show";
        } else {
            return "redirect:/pizze";
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }

        pizzaRepository.save(pizza);
        return "redirect:/pizze";
    }

}
