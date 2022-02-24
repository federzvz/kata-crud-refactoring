package co.com.sofka.crud.model;

import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean completed;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_tarea")
    private Grupo groupListId;

    public Todo() {
    }

    public Todo(Long id, String name, boolean completed, Grupo groupListId) {
        this.id = id;
        this.name = name;
        this.completed = completed;
        this.groupListId = groupListId;
    }

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Grupo getGroupListId() {
        return groupListId;
    }

    public void setGroupListId(Grupo groupListId) {
        this.groupListId = groupListId;
    }
}
