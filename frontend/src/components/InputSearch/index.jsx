import { useState } from "react";


export default function InputSearch(props){
    const [inputValue, setInputValue] = useState("");

    const handleChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleClick = () => {
        props.onSearch(inputValue);
    };
    return (
        <>
            <div className="relative w-4/5 md:w-1/2 mt-6">
                <input type="text" 
                    id={props.id} 
                    value={inputValue} 
                    onChange={handleChange} 
                    className="search-input w-full py-4 sm:py-3 px-6 sm:text-lg text-gray-200 rounded-full focus:outline-none focus:ring-2 focus:ring-blue-600 border" 
                    placeholder={props.placeholder}
                    style={{
                        border: props.error ? "2px solid #971d1d" : "1px solid rgba(255, 255, 255, 0.1)",
                    }}/>
        
                <button 
                    id="buscar" 
                    onClick={handleClick} 
                    className="absolute right-2 top-2 px-6 py-2 accent-gradient text-white rounded-full hover:opacity-80 transition-all duration-300 font-medium">
                    Buscar
                </button>
            </div>
        </>
    )
}