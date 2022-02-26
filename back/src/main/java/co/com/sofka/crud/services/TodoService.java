package co.com.sofka.crud.services;

import co.com.sofka.crud.entities.Todo;
import co.com.sofka.crud.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo save(Todo todo){
        Todo _todo = repository.save(new Todo(todo.getName(), todo.getIdTodoList()));
        return _todo;
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Todo> findAll(){
        List<Todo> _todos = (List<Todo>) repository.findAll();
        return _todos;
    }

    public Todo findById(Long id){
        Todo _todo = repository.findById(id).orElseThrow();
        return _todo;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Todo update(Long id, Todo todo){
        Todo _todo = repository.findById(id).orElseThrow();
        if(todo.getName() != null) _todo.setName(todo.getName());
        if(todo.getId() != null) _todo.setId(todo.getId());
        _todo.setCompleted(todo.isCompleted());
        Todo _todoUpdated = repository.save(_todo);
        return _todoUpdated;
    }

    public void deleteAllTodosByGrupoId(Long idTodoList){
        List<Todo> todos = repository.findAll();
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getId()==idTodoList){
                repository.deleteById(todos.get(i).getId());
            }
        }
    }


    public List<Todo> findAllTodosByGrupoId(Long idTodoList){
        List<Todo> todos = repository.findAll();
        List<Todo> todoAux = new ArrayList<>();
        for(int i=0;i<todos.size();i++){
            if(todos.get(i).getId()==idTodoList){
                todoAux.add(todos.get(i));
            }
        }
        return todoAux;
    }

}
