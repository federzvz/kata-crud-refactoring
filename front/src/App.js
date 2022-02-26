/*import React, { useContext, useReducer, useEffect, useRef, useState, createContext } from 'react';
import FormTarea from './components/FormTarea';
import {StoreProvider,Store} from './context/TodoContext'

function App() {
  return <StoreProvider>
    <h3>To-Do List</h3>
    <FormTarea />
    <List />
  </StoreProvider>
}

export default App;*/

import React, { Fragment } from 'react';
import GrupoProvider from './provider/GrupoProvider';
import GrupoForm from './components/Grupo/GrupoForm';
import GrupoView from './components/Grupo/GrupoView';
import TodoForm from './components/Todo/TodoForm';
import TodoView from './components/Todo/TodoView';
import 'bootstrap/dist/css/bootstrap.min.css';
import TodoProvider from './provider/TodoProvider';

function App() {
    return (
        <Fragment>
              <div>
                  <GrupoProvider>
                      <GrupoForm />
                      <GrupoView />
                  </GrupoProvider>
              </div>
        </Fragment>
    );
}

export default App;
