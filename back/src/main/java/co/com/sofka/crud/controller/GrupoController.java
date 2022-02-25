package co.com.sofka.crud.controller;

import co.com.sofka.crud.entity.Grupo;
import co.com.sofka.crud.entity.Todo;
import co.com.sofka.crud.service.GrupoService;
import co.com.sofka.crud.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GrupoController {
    @Autowired
    private GrupoService service;

    @GetMapping(value = "api/grupos")
    public ResponseEntity<List<Grupo>> list(){
        try{
            List<Grupo> todoLists = service.findAll();
            if(!todoLists.isEmpty()){
                return new ResponseEntity<>(todoLists, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "api/grupo")
    public ResponseEntity<Grupo> save(@RequestBody Grupo grupo){
        try{
            return new ResponseEntity<>(service.save(grupo), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "api/grupo/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Grupo grupo){
        try{
            Grupo todoListUpdated = service.update(id, grupo);
            return new ResponseEntity<>(todoListUpdated, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping(value = "api/{id}/grupo")
    public ResponseEntity<String> deleteTodoList(@PathVariable("id") Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("La lista de 'TODOs' con id '"+id+"' ha sido eliminada.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("api/{id}/grupo")
    public ResponseEntity<Grupo> getTodoList(@PathVariable("id") Long id){
        try{
            Grupo todoList = service.findById(id);
            if(todoList != null){
                return new ResponseEntity<>(todoList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
