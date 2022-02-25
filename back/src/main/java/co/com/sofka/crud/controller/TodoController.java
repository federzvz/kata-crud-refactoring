package co.com.sofka.crud.controller;

import co.com.sofka.crud.entity.Grupo;
import co.com.sofka.crud.repository.GrupoRepository;
import co.com.sofka.crud.repository.TodoRepository;
import co.com.sofka.crud.service.GrupoService;
import co.com.sofka.crud.service.TodoService;
import co.com.sofka.crud.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService serviceTodo;
    private GrupoService serviceGrupo;
    private GrupoRepository IGrupo;

    @GetMapping(value = "api/todos")
    public Iterable<Todo> list(){
        return serviceTodo.findAll();
    }
    
    @PostMapping(value = "api/todo")
    public ResponseEntity<Todo> save(@RequestBody Todo todo){
        try{
                serviceTodo.save(todo);
                return new ResponseEntity<>(todo, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e.toString());
        }
    }

    @PutMapping(value = "api/todo")
    public Todo update(@RequestBody Todo todo){
        if(todo.getId() != null){
            return serviceTodo.save(todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        serviceTodo.deleteById(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id){
        return serviceTodo.findById(id);
    }

}
