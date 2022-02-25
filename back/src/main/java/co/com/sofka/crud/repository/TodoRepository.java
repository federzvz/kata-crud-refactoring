package co.com.sofka.crud.repository;

import co.com.sofka.crud.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {

}
