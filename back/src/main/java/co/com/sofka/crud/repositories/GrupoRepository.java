package co.com.sofka.crud.repositories;


import co.com.sofka.crud.entities.Grupo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GrupoRepository extends CrudRepository<Grupo, Long> {
    /*
    public abstract Iterable<Grupo> list();
    public abstract Grupo save(Grupo grupo);
    public abstract void delete(Long id);
    public abstract Grupo get(Long id);
    public abstract Optional<Grupo> getByID(Long id);
    public abstract Grupo update(Long id, Grupo todoList);
    public abstract void deleteById(Long id);
    public abstract List<Grupo> findAll();
    public abstract Optional<Grupo> findById(Long id);
    */
}
