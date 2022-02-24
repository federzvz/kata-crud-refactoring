package co.com.sofka.crud.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tareas_grupos",
            joinColumns = { @JoinColumn(name = "grupo_id") },
            inverseJoinColumns = { @JoinColumn(name = "tarea_id") })
    private List<Todo> tareas = new ArrayList<Todo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTareas() {
        return tareas;
    }

    public void setTareas(List<Todo> tareas) {
        this.tareas = tareas;
    }

    public Grupo(Long id, String name, List<Todo> tareas) {
        this.id = id;
        this.name = name;
        this.tareas = tareas;
    }

    public Grupo() {

    }
}
