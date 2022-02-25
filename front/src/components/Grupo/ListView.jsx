import React, { Fragment, useContext, useEffect } from 'react'
import { ListStore, HOST_API } from '../../provider/ListProvider';
import TodoProvider from '../../provider/TodoProvider';
import TodoForm from '../Todo/TodoForm';
import TodoView from '../Todo/TodoView';


const ListView = () => {
    const { dispatch, state } = useContext(ListStore);

    useEffect(() => {
        fetch(HOST_API + "/grupos")
        .then((response) => response.json())
        .then((list) => {
            dispatch({ type: "update-list", list });
        })
        .catch(() => {});
    }, [state.list.length, dispatch]);

    const onDelete = (id) => {
        fetch(HOST_API + "/grupos/" + id, {
            method: "DELETE",
        }).then(() => {
            dispatch({ type: "delete-item", id });
        });
    };

    const onEdit = (list) => {
        dispatch({ type: "edit-item", item: list });
    };

    return (
        <Fragment>
            {state.list.map((list) => { return (
            /*<div key={list.id}>
                <div>
                    <div className='flex-row'>
                        <h3>ID: <span>{list.id}</span></h3>
                        <h3>Name: <span>{list.name}</span></h3>
                        <button onClick={() => onDelete(list.id)}>Delete</button>
                        <button onClick={() => onEdit(list)}>Edit</button>
                    </div> 
                </div>
                <div>
                    <TodoProvider>
                        <div className='flex-row'>
                            <h3>TODO's de la lista:</h3>
                            <TodoForm listId = {list.id} />
                        </div>
                        <TodoView listId = {list.id} />
                    </TodoProvider>
                </div>
            </div>*/
            <div key={list.id}>
            <figure class="text-center">
                <div>
                    <div className='flex-row'>
                        <h3>ID: <span>{list.id}</span></h3>
                        <h3>Name: <span>{list.name}</span></h3>
                        <button onClick={() => onDelete(list.id)} class="btn btn-secondary rounded-pill mb-4"
                        style={{margintop: '10px', textalign: 'center'}}>
                        Eliminar
                        <i class="bi bi-chevron-right"></i>
                         </button>
                         <button onClick={() => onEdit(list)} class="btn btn-secondary rounded-pill mb-4"
                        style={{margintop: '10px', textalign: 'center'}}>
                        Editar
                        <i class="bi bi-chevron-right"></i>
                         </button>
                    </div> 
                </div>
    <div class="container px-4">
        <div class="table-responsive">
        <TodoProvider>
            <TodoForm listId = {list.id} />
            <TodoView listId = {list.id} />
        </TodoProvider>
        </div>
    </div>
</figure></div>
            );
            })}
        </Fragment>
    );
}
 
export default ListView;