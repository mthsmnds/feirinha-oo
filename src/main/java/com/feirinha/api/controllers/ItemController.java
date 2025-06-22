package com.feirinha.api.controllers;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getItems(){
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.findAll())
        ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemId(@PathVariable("id") Long id){
        Optional<ItemModel> item = itemRepository.findById(id);
        
        if(!item.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item com este ID não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(item.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> postItem(@RequestBody @Valid ItemDTO body){
        ItemModel item = new ItemModel(body);
        itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable("id") Long id, @RequestBody @Valid ItemDTO body){
        Optional<ItemModel> item = itemRepository.findById(id);
        
        if(!item.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ItemModel newItem = new ItemModel(body);
        newItem.setId(id);
        itemRepository.save(newItem);
        return ResponseEntity.status(HttpStatus.OK).body(newItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
