import { buscarTodosPratos } from "../../services/api";

export function BoxResults({resultado, setResultado}){
    
    const verListaCompleta = async () =>{
        try {
            const response = await buscarTodosPratos();
            if (response.data.resultado) {
                setResultado(response.data.resultado); 
            } else {
                setResultado("Nenhum prato encontrado");
            }
        } catch (error) {
            console.error("Erro ao buscar pratos:", error);
            setResultado("Erro ao buscar pratos.");
        }
    };
    return(
        <>
        <div className="glass-effect rounded-xl p-6 mt-8 w-4/5 md:w-1/2 ">
                <div className="flex justify-between items-center mb-4">
                    <h2 className="text-xl font-semibold text-white">Resultados</h2>
                    <button id="ver-lista-completa"
                        className="w-1/2 sm:w-56 px-4 py-2 text-sm bg-blue-600 hover:bg-blue-700 text-white rounded-full transition-all duration-300" onClick={verListaCompleta}>
                        Ver Lista Completa de Pratos
                    </button>
                </div>
                <div className="result-container bg-opacity-10 bg-white rounded-lg p-4">
                    <div id="resultado" className="text-gray-300 mb-2 flex flex-col">{resultado}</div>
                </div>
            </div>
        </>
    )
}