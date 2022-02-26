package co.com.sofka.crud.entities;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_complete")
    private boolean completed;

    @Column(name = "list_todo_id")
    private Long idTodoList;

    public Todo(){
    }

    public Todo(String name, boolean completed, Long idTodoList) {
        this.name = name;
        this.completed = completed;
        this.idTodoList = idTodoList;
    }

    public Todo(String name, Long idTodoList) {
        this.name = name;
        this.completed = completed;
        this.idTodoList = idTodoList;
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

    public Long getIdTodoList() {
        return idTodoList;
    }

    public void setIdTodoList(Long idTodoList) {
        this.idTodoList = idTodoList;
    }
}
