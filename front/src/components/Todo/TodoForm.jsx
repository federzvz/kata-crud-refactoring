import React, { useRef, useContext, useState } from 'react';
import { TodoStore, HOST_API } from '../../provider/TodoProvider';

const TodoForm = (props) => {
    const formTodoRef = useRef(null);
    const { dispatch, state: { item } } = useContext(TodoStore);
    const [ state, setState ] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: null,
            isComplete: false,
            idTodoList: props.listId
        }
        fetch(HOST_API + "/todos", {
            method: "POST",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((todo) => {
            dispatch({ type: "add-item", item: todo });
            setState({ name: "" });
            formTodoRef.current.reset();
        });
    }

    const onEdit = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: item.id,
            isComplete: item.isComplete,
            idTodoList: state.idTodoList
        };
    
        fetch(HOST_API + "/todos/"+item.id, {
            method: "PUT",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((todo) => {
            dispatch({ type: "update-item", item: todo });
            setState({ name: "" });
            formTodoRef.current.reset();
        });
    };

    return (
        /*<form ref={formTodoRef}>
            <input type="text" name="name" defaultValue={item.name}
            onChange={(event) => {
                setState({ ...state, name: event.target.value });
            }}></input>
            {!item.id && <button onClick={onAdd}>Add</button>}
            {item.id && <button onClick={onEdit}>Edit</button>}
        </form>*/
        <div class="container px-4 d-flex justify-content-center" style={{textalign: 'center'}}>
        <form ref={formTodoRef} name="login" method="POST" action=""
            class="needs-validation" novalidate>
            <div class="form-group">
                <input type="text" name="name" class="form-control rounded-pill mb-2" id="validationCustom01"
                    placeholder="¿Qué piensas hacer hoy?" required defaultValue={item.name} onChange={(event) => {
                        setState({ ...state, name: event.target.value });
                    }}></input>
                <div class="invalid-feedback" style={{margintop: '10px', marginbottom: '10px'}}>No ha ingresado un nombre</div>
            </div>
            <div class="d-flex justify-content-center align-items-center">
            {!item.id && <button id="login" onClick={onAdd} class="btn btn-secondary rounded-pill mb-4"
                    style={{margintop: '10px', textalign: 'center'}}>
                    Add
                    <i class="bi bi-chevron-right"></i>
                </button>}
                {item.id && <button id="login" onClick={onEdit} class="btn btn-secondary rounded-pill mb-4"
                    style={{margintop: '10px', textalign: 'center'}}>
                    Edit
                    <i class="bi bi-chevron-right"></i>
                </button>}
            </div>
        </form>
    </div>
    );
}
 
export default TodoForm;