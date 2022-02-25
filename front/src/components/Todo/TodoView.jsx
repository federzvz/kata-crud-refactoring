import React, { useContext, useEffect } from "react";
import { TodoStore, HOST_API } from '../../provider/TodoProvider';

const TodoView = (props) => {
  const { dispatch, state } = useContext(TodoStore);

  useEffect(() => {
    fetch(HOST_API + "/todos/grupo/" + props.listId)
      .then((response) => response.json())
      .then((list) => {
        dispatch({ type: "update-list", list });
      })
      .catch(() => {});
  }, [state.list.length, dispatch]);

  const onDelete = (id) => {
    fetch(HOST_API + "/todos/" + id, {
      method: "DELETE",
    }).then(() => {
      dispatch({ type: "delete-item", id });
    });
  };

  const onEdit = (todo) => {
    dispatch({ type: "edit-item", item: todo });
  };

  return (
    /*<div className='flex-column'>
            <div className='flex-row'>
                {
                    (state.list.length > 0) ? 
                    <div className='flex-row'>
                        <h3>ID</h3>
                        <h3>Name</h3>
                        <h3>It is complete?</h3>
                    </div> :
                    <h3>Lista vacía</h3>
                }
            </div>
            <div>
                {state.list.map((todo) => { return (
                    <div key={todo.id} className='flex-row'>
                        <h3>{todo.id}</h3>
                        <h3>{todo.name}</h3>
                        <h3>{todo.isComplete ? 'Si' : 'No'}</h3>
                        <button onClick={() => onDelete(todo.id)}>Delete</button>
                        <button onClick={() => onEdit(todo)}>Edit</button>
                    </div> );
                })}
            </div>
        </div>*/
    <figure class="text-center">
      <div class="container px-4">
        <div class="table-responsive">
          {state.list.length > 0 ? (
            <div></div>
          ) : (
            <h3>Lista vacía</h3>
          )}
        </div>
        <div class="table-responsive">
          {state.list.map((todo) => {
            return (
              <table class="table table-dark">
                <thead>
                  <tr>
                    <td>ID</td>
                    <td>Tarea</td>
                    <td>¿Completado?</td>
                  </tr>
                </thead>
                <tbody>
                  <tr key={todo.id}>
                    <td>{todo.id}</td>
                    <td>{todo.name}</td>
                    <td>
                      <input class="form-check-input" type="checkbox"></input>
                    </td>
                    <td>
                      <button class="btn btn-secondary rounded-pill " onClick={() => onDelete(todo.id)}>
                        Eliminar
                      </button>
                    </td>
                    <td>
                      <button class="btn btn-secondary rounded-pill " onClick={() => onEdit(todo)}>
                        Editar
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            );
          })}
        </div>
      </div>
    </figure>
  );
};

export default TodoView;
