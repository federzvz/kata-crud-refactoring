package co.com.sofka.crud.dataTypes;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TodoDTO {
    private Long id;
    private String name;
    private boolean completed;
    private Long idTodoList;

    public TodoDTO() {
    }
    public TodoDTO(String name, Long idTodoList) {
        this.name = name;
        this.idTodoList = idTodoList;
    }

    public TodoDTO(Long id, String name, boolean completed, Long idTodoList) {
        this.id = id;
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
