package com.alex.server;


import com.alex.server.entities.element.TreeElement;
import com.alex.server.repos.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/altasoft")
public class TreeController {
    @Autowired
    ElementRepository elementRepository;

    @PutMapping
    public ResponseEntity<String> addTreeElement(@RequestBody TreeElement treeElement){
        elementRepository.save(treeElement);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
