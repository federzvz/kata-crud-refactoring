import React, { createContext, useReducer } from "react";
import GrupoReducer from "../reducer/GrupoReducer";


const ListProvider = ({ children }) => {
    const listInitialState = {
        list: [],
        item: {}
    };
    const [state, dispatch] = useReducer(GrupoReducer, listInitialState);
    return (
        <ListStore.Provider value={{ state, dispatch }}>{ children }</ListStore.Provider>
    );
}

export const HOST_API = "http://localhost:8081/api";
export const ListStore = createContext();
export default ListProvider;