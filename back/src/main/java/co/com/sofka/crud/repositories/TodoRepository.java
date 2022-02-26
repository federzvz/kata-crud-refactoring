package co.com.sofka.crud.repositories;

import co.com.sofka.crud.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    /*public abstract Todo save(Todo todo);
    public abstract  void deleteById(Long id);
    public abstract  List<Todo> findAll();
    public abstract  Todo findById(Long id);
    public abstract  void deleteAll();
    public abstract  Todo update(Long id, Todo todo);
    public abstract  void deleteAllTodosByGrupoId(Long idTodoList);
    public abstract  List<Todo> findAllTodosByGrupoId(Long idTodoList);*/
}
