package com.feirinha.api.controllers;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.dtos.ItemDTO;
import com.feirinha.api.models.ItemModel;
import com.feirinha.api.repositories.ItemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    final ItemRepository itemRepository;

    ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping()
    public List<ItemModel> getItems(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ItemModel> getItemId(@PathVariable("id") Long id){
        Optional<ItemModel> item = itemRepository.findById(id);
        
        if(!item.isPresent()){
            return Optional.empty();
        }else{
            return Optional.of(item.get());
        }
    }

    @PostMapping()
    public void postItem(@RequestBody @Valid ItemDTO body){
        ItemModel item = new ItemModel(body);
        itemRepository.save(item);
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
