package co.com.sofka.crud.controllers;

import co.com.sofka.crud.dataTypes.GrupoDTO;
import co.com.sofka.crud.entities.Grupo;
import co.com.sofka.crud.repositories.GrupoRepository;
import co.com.sofka.crud.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/grupos")
public class GrupoController {
    @Autowired
    private GrupoService service;

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> list(){
        try{
            List<Grupo> todoLists = service.findAll();
            if(!todoLists.isEmpty()){
                List<GrupoDTO> gruposDTO= new ArrayList<>();
                for(int i=0;i<todoLists.size();i++){
                    gruposDTO.add(new GrupoDTO(todoLists.get(i).getId(),
                            todoLists.get(i).getName()));
                }
                return new ResponseEntity<List<GrupoDTO>>(gruposDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity<Grupo> save(@RequestBody GrupoDTO grupo){
        try{
            Grupo grupo1 = new Grupo(grupo.getName());
            return new ResponseEntity<>(service.save(grupo1), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> update(@PathVariable("id") Long id, @RequestBody GrupoDTO grupoDTO){
        try{
            Grupo grupoUpdated = service.update(id, new Grupo(grupoDTO.getName()));
            return new ResponseEntity<>(grupoUpdated, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoList(@PathVariable("id") Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("La lista de 'TODOs' con id '"+id+"' ha sido eliminada.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> getTodoList(@PathVariable("id") Long id){
        try{
            Grupo grupo = service.findById(id);
            if(grupo != null){
                return new ResponseEntity<>(new GrupoDTO(grupo.getName()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
