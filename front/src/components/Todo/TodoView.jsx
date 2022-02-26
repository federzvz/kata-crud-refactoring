import React, { useContext, useEffect, Fragment } from "react";
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

  const onToggleStatus = (todo) => {
    console.log(todo);
    fetch(HOST_API + "/todos/completed/" + todo,{
      method: "PUT",
      headers: {
        'Content-Type': 'application/json'
      }}).then(response => response.json())
          .then((todo) => {
            dispatch({ type: "update-item", item: todo });
          });
      };

  return (
      <Fragment>
    <figure class="text-center">
      <div class="container px-4">
        <div class="table-responsive">
          {state.list.length > 0 ? (
            <div></div>
          ) : (
            <h3>El Grupo no tiene ninguna tarea.</h3>
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
                    <td> {(todo.isComplete === true) ? 
                            <input class="form-check-input" type="checkbox" onClick={() => onToggleStatus(todo.id)} checked></input> : 
                            <input class="form-check-input" type="checkbox" onClick={() => onToggleStatus(todo.id)}></input>
                            }
                    </td>
                    <td>
                      <button class="btn btn-secondary rounded-pill " onClick={() => onDelete(todo.id)}>
                        Eliminar {todo.name}
                      </button>
                    </td>
                    <td>
                      <button class="btn btn-secondary rounded-pill " onClick={() => onEdit(todo)}>
                        Editar {todo.name}
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
    </Fragment>
  );
};

export default TodoView;
