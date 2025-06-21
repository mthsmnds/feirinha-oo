package com.feirinha.api.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.models.Item;

@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping()
    public String getItems(){
        return "Lista de Items!!";
    }

    @GetMapping("/{id}")
    public String getItemId(@PathVariable("id") Long id){
        return "Um item específico com o id " + id;

    }

    @PostMapping()
    public String postItem(@RequestBody String body){
        return "Receita Criada: " + body;
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id, @RequestBody String body){
        return "Edição do item: " + id + body;
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        return "Deletando item " + id;
    }
}
