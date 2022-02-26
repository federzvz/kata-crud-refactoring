import React, { useRef, useContext, useState } from 'react';
import { ListStore, HOST_API } from '../../provider/GrupoProvider';

const GrupoForm = () => {
    const formListRef = useRef(null);
    const { dispatch, state: { item } } = useContext(ListStore);
    const [ state, setState ] = useState(item);
    
    const onAdd = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: null,
        }
        fetch(HOST_API + "/grupos", {
            method: "POST",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((list) => {
            dispatch({ type: "add-item", item: list });
            setState({ name: "" });
            formListRef.current.reset();
        });
    }

    const onEdit = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: item.id,
        };
    
        fetch(HOST_API + "/grupos/ "+item.id, {
            method: "PUT",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((list) => {
            dispatch({ type: "update-item", item: list });
            setState({ name: "" });
            formListRef.current.reset();
        });
    };

    return (
        <div class="container px-4 d-flex justify-content-center" style={{textalign: 'center'}}>
        <form ref={formListRef} name="login" method="POST" action=""
            class="needs-validation" novalidate>
            <h1 class="mb-5 mt-5" style={{color:'azure'}}>DASHBOARD</h1>
            <div class="form-group">
                <input type="text" name="name" class="form-control rounded-pill mb-2" id="validationCustom01"
                    placeholder="Nuevo grupo de tareas" required defaultValue={item.name} onChange={(event) => {
                        setState({ ...state, name: event.target.value });
                    }}></input>
            </div>
            <div class="d-flex justify-content-center align-items-center">
            {!item.id && <button id="login" onClick={onAdd} class="btn btn-secondary rounded-pill mb-4"
                    style={{margintop: '10px', textalign: 'center'}}>
                    Añadir
                    <i class="bi bi-chevron-right"></i>
                </button>}
                {item.id && <button id="login" onClick={onEdit} class="btn btn-secondary rounded-pill mb-4"
                    style={{margintop: '10px', textalign: 'center'}}>
                    Editar
                    <i class="bi bi-chevron-right"></i>
                </button>}
            </div>
            <div class="invalid-feedback" style={{margintop: '10px', marginbottom: '10px'}}>Debe ingresar entre 3 y 50 carácteres.</div>
        </form>
    </div>
    );
}
 
export default GrupoForm;