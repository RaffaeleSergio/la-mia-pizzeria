package org.lession.pizza.la_mia_pizzeria.repository;

import java.util.List;

import org.lession.pizza.la_mia_pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNomeContainingIgnoreCase(String nome);
}
