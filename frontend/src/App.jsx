import { useState } from "react";
import InputSearch from "./components/InputSearch";
import { listarIngredientes, buscarPrato } from "./services/api"; 
import { BoxResults } from "./components/BoxResults";

function App() {
    const [resultado, setResultado] = useState("");
    const [errorPrato, setErrorPrato] = useState(false);
    const [errorIngrediente, setErrorIngrediente] = useState(false);

    const onSearchPrato = async (prato) => {
        try {
            if (!prato.trim()) {
              setErrorPrato(true);
              setResultado("Por favor, insira o nome de um prato.");
              return;
            }

            setErrorPrato(false)
            const pratoComUnderlines = prato.replace(/(?!^)\s+(?=\S)/g, "_");;
          
            const response = await listarIngredientes(pratoComUnderlines);
            if (response.data.resultado) {
                setResultado(response.data.resultado);
            } else {
                setResultado("Nenhum resultado encontrado");
            }
        } catch (error) {
            console.error(error);
        }
    };

    const onSearchIngrediente = async (ingredientes) =>{
      try {
        if(!ingredientes.trim()){
          setErrorIngrediente(true);
          setResultado("Por favor, insira o nome de um ou mais ingredientes.");
          return;
        }

        setErrorIngrediente(false);
        const ingredientesArray = ingredientes.split(",").map((ing) => ing.trim());
  
        const response = await buscarPrato(ingredientesArray);
  
        if (response.data.resultado) {
          let ingredientes = response.data.resultado;

          if (typeof ingredientes === "string") {
            ingredientes = ingredientes.match(/([a-zA-Z0-9\u00C0-\u00FF]+(?: [a-zA-Z0-9\u00C0-\u00FF]+)*)/g);
          }

          setResultado(ingredientes.join(", "));
        } else {
          setResultado(""); 
        }
      } catch (error) {
        setResultado(""); 
        console.error(error);
      }
    }

  return (
    <>
      <body className="flex flex-col items-center">
        <h1 className="text-4xl font-extrabold bg-clip-text text-transparent text-center bg-gradient-to-r from-blue-400 to-blue-800 py-8">Sugestões Gastrômicas</h1>
        <p className="text-center text-lg text-gray-300">Encontre os ingredientes dos seus pratos favoritos e descubra novas receitas! </p>
        <InputSearch placeholder="Digite o nome do prato..." id="prato" onSearch={onSearchPrato} error={errorPrato} />
        <InputSearch placeholder="Busque por ingredientes..." id="ingredientes" onSearch={onSearchIngrediente} error={errorIngrediente} />
        <BoxResults resultado={resultado} setResultado={setResultado} />
      </body>
    </>
  )
}

export default App
