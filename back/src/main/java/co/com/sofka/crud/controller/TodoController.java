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
@CrossOrigin(origins = "*")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService serviceTodo;
    private GrupoService serviceGrupo;
    private GrupoRepository IGrupo;

    @GetMapping("/grupo/{idTodoList}")
    public ResponseEntity<List<Todo>> getAllTodosOfList(@PathVariable("idTodoList") Long idTodoList){
        try{
            List<Todo> todosOfList = serviceTodo.findAllTodosByGrupoId(idTodoList);
            if(!todosOfList.isEmpty()){
                return new ResponseEntity<>(todosOfList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        try{
            List<Todo> todos = serviceTodo.findAll();
            if(!todos.isEmpty()){
                return new ResponseEntity<>(todos, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody Todo todo){
        try{
            return new ResponseEntity<>(serviceTodo.save(todo), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Todo todo){
        try{
            Todo todoListUpdated = serviceTodo.update(id, todo);
            return new ResponseEntity<>(todoListUpdated, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTodos(){
        try{
            serviceTodo.deleteAll();
            return new ResponseEntity<>("Todos los 'TODOs' han sido eliminados.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/grupo/{idTodoList}")
    public ResponseEntity<String> deleteAllTodosOfList(@PathVariable("idTodoList") Long idTodoList){
        try{
            serviceTodo.deleteAllTodosByGrupoId(idTodoList);
            return new ResponseEntity<>("Todos los 'TODOs' de la lista '"+idTodoList+"' han sido eliminados.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        try{
            serviceTodo.deleteById(id);
            return new ResponseEntity<>("El 'TODO' con id '"+id+"' ha sido eliminado.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id){
        return serviceTodo.findById(id);
    }

}
