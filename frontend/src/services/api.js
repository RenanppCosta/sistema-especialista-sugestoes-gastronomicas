import axios from "axios";

const baseUrl = "http://127.0.0.1:5000/";

export async function listarIngredientes(prato) {
    const response = await axios.get(`${baseUrl}listar_ingredientes?prato=${prato}`);
    return response;
}

export async function buscarPrato(ingredientes) {
    const queryString = ingredientes.map((ing) => `ingredientes=${encodeURIComponent(ing)}`).join("&");

    const response = await axios.get(`${baseUrl}buscar_prato?${queryString}`);
        
    return response;
}

export async function buscarTodosPratos() {
    const response = await axios.get(`${baseUrl}listar_todos_pratos`);
        
    return response;
}