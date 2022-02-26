package co.com.sofka.crud.controllers;

import co.com.sofka.crud.dataTypes.TodoDTO;
import co.com.sofka.crud.repositories.GrupoRepository;
import co.com.sofka.crud.services.GrupoService;
import co.com.sofka.crud.services.TodoService;
import co.com.sofka.crud.entities.Todo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService serviceTodo;
    private GrupoService serviceGrupo;
    private GrupoRepository IGrupo;

    @GetMapping("/grupo/{idTodoList}")
    public ResponseEntity<List<TodoDTO>> getAllTodosOfList(@PathVariable("idTodoList") Long idTodoList){
        try{
            List<Todo> allOfGrupo = serviceTodo.findAllTodosByGrupoId(idTodoList);
            if(!allOfGrupo.isEmpty()){
                List<TodoDTO> todoDTO= new ArrayList<>();
                for(int i=0;i<allOfGrupo.size();i++){
                    todoDTO.add(new TodoDTO(allOfGrupo.get(i).getId(),
                            allOfGrupo.get(i).getName(),
                            allOfGrupo.get(i).isCompleted(),
                            allOfGrupo.get(i).getIdTodoList()
                    ));
                }
                return new ResponseEntity<List<TodoDTO>>(todoDTO,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
        try{
            List<Todo> todos = serviceTodo.findAll();
            if(!todos.isEmpty()){
                List<TodoDTO> todoDTO= new ArrayList<>();
                for(int i=0;i<todos.size();i++){
                    todoDTO.add(new TodoDTO(todos.get(i).getId(),
                            todos.get(i).getName(),
                            todos.get(i).isCompleted(),
                            todos.get(i).getIdTodoList()));
                }
                return new ResponseEntity<List<TodoDTO>>(todoDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody TodoDTO todoDTO){
        try{
            return new ResponseEntity<>(serviceTodo.save(new Todo(todoDTO.getName(), todoDTO.getIdTodoList())), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable("id") Long id, @RequestBody TodoDTO todo){
        try{
            Todo todoListUpdated = serviceTodo.update(id, new Todo(todo.getName(),todo.getIdTodoList()));
            return new ResponseEntity<>(todoListUpdated, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/completed/{id}")
    public ResponseEntity<String> toggleCompleted(@PathVariable("id") Long id){
        try{
            Todo todoAux= serviceTodo.findById(id);
            if(todoAux.isCompleted()){
                todoAux.setCompleted(false);
                serviceTodo.update(id, todoAux);
                return new ResponseEntity<>("BEFORE: True -> NOW: False",HttpStatus.OK);
            }
            todoAux.setCompleted(true);
            serviceTodo.update(id, todoAux);
            return new ResponseEntity<>("BEFORE: False -> NOW: True",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTodos(){
        try{
            serviceTodo.deleteAll();
            return new ResponseEntity<>("Todos los objetos TODO's han sido eliminado.", HttpStatus.OK);
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
    public TodoDTO get(@PathVariable("id") Long id){
        Todo todoAux = serviceTodo.findById(id);
        return new TodoDTO(todoAux.getName(),todoAux.getIdTodoList());
    }

}
