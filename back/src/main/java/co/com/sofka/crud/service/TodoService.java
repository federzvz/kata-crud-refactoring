package co.com.sofka.crud.service;

import co.com.sofka.crud.entity.Todo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Todo update(Long id, Todo todo){
        Todo _todo = repository.findById(id).orElseThrow();
        if(todo.getName() != null) _todo.setName(todo.getName());
        if(todo.getId() != null) _todo.setId(todo.getId());
        _todo.setCompleted(todo.isCompleted());
        Todo _todoUpdated = repository.save(_todo);
        return _todoUpdated;
    }


    public Optional<Todo> findAllTodosByGrupoId(Long idTodoList){
        Optional<Todo> todosOfList = repository.findById(idTodoList);
        return todosOfList;
    }

}
