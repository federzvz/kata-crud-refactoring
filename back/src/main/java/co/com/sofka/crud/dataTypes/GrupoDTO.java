package co.com.sofka.crud.dataTypes;

import javax.persistence.*;

public class GrupoDTO {
        private Long id;
        private String name;

    public GrupoDTO() {
    }

    public GrupoDTO(String name) {
        this.name = name;
    }

    public GrupoDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
