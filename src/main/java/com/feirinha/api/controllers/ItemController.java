package com.feirinha.api.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
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
import com.feirinha.api.services.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    final ItemService itemService ;

    ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping()
    public ResponseEntity<Object> getItems(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItems())
        ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemId(@PathVariable("id") Long id){
        Optional<ItemModel> item = itemService.getItemId(id);
        
        if(!item.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item com este ID não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(item.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> postItem(@RequestBody @Valid ItemDTO body){
        ItemModel item = itemService.postItem(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable("id") Long id, @RequestBody @Valid ItemDTO body){
        Optional<ItemModel> item = itemService.updateItem(id, body);
        
        if(!item.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(item.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
