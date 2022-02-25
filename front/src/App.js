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
import ListProvider from './provider/ListProvider';
import ListForm from './components/Grupo/ListForm';
import ListView from './components/Grupo/ListView';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
    return (
        <Fragment>
              <div>
                  <ListProvider>
                      <ListForm />
                      <ListView />
                  </ListProvider>
              </div>
        </Fragment>
    );
}

export default App;
