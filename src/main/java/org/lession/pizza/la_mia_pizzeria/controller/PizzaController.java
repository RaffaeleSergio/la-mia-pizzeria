package org.lession.pizza.la_mia_pizzeria.controller;

import java.util.Optional;

import org.lession.pizza.la_mia_pizzeria.model.Pizza;
import org.lession.pizza.la_mia_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
