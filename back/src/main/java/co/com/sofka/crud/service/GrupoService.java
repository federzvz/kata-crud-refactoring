package co.com.sofka.crud.service;

import co.com.sofka.crud.entity.Grupo;
import co.com.sofka.crud.entity.Todo;
import co.com.sofka.crud.repository.GrupoRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository repository;

    public Iterable<Grupo> list(){
        return repository.findAll();
    }

    public Grupo save(Grupo grupo){
        return repository.save(grupo);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Grupo get(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Optional<Grupo> getByID(Long id){
        return repository.findById(id);
    }

    public Grupo update(Long id, Grupo todoList){
        Grupo _todoList = repository.findById(id).orElseThrow();
        if(todoList.getName() != null) _todoList.setName(todoList.getName());
        Grupo _todoListUpdated = repository.save(_todoList);
        return _todoListUpdated;
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Grupo> findAll() {
        return (List<Grupo>) repository.findAll();
    }

    public Grupo findById(Long id){
        Grupo g = repository.findById(id).orElseThrow();
        return g;
    }
}
