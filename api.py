from flask import Flask, request, jsonify
import subprocess
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

def formatar_resultados(resultados):
    """Formata os resultados, capitalizando a primeira letra e substituindo underscores."""
    if resultados:
        # Substitui '_' por ' ' e capitaliza a primeira letra
        return [item.replace('_', ' ').capitalize() for item in resultados]
    return []

def consultar_prolog(query):
    """Função para interagir com Prolog"""
    # Define o comando Prolog para ser executado
    cmd = f"swipl -q -s dados.pl -g \"{query},halt.\""
    
    # Exibe o comando Prolog para depuração
    print(f"Comando Prolog: {cmd}")  
    
    try:
        # Executa o comando e captura o resultado
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=10)
        
        # Verifica se houve erro na execução
        if result.returncode != 0:
            print(f"Erro ao executar Prolog: {result.stderr}")
            return None  # Ou uma mensagem apropriada

        # Retorna o resultado formatado como uma lista de pratos
        pratos = result.stdout.strip().replace("[", "").replace("]", "").split(', ')
        
        # Exibe o resultado para depuração
        print("Resultado Prolog:", result.stdout)
        
        # Se houver pratos, retorne a lista de pratos, senão, retorne None
        if pratos and pratos != ['']:
            return pratos
        else:
            return None
    
    except subprocess.TimeoutExpired:
        print("Tempo limite para a execução do Prolog excedido.")
        return None  # Ou uma mensagem apropriada
    
    except Exception as e:
        print(f"Ocorreu um erro: {e}")
        return None  # Ou uma mensagem apropriada

@app.route('/listar_ingredientes', methods=['GET'])
def listar_ingredientes():
    prato = request.args.get('prato')
    if prato:
        resultado = consultar_prolog(f"listar_ingredientes({prato})")
        ingredientes_formatados = formatar_resultados(resultado)
        return jsonify({'resultado': ' / '.join(ingredientes_formatados)})  # Retorna ingredientes formatados com '/'
    return jsonify({'erro': 'Prato não fornecido'})

@app.route('/buscar_prato', methods=['GET'])
def buscar_prato():
    ingredientes = request.args.getlist('ingredientes')  # Obtendo os ingredientes da query string
    query_ingredientes = ','.join(ingredientes)  # Transformando a lista em uma string

    # Consultando o Prolog para todos os pratos que contenham os ingredientes
    query = f"buscar_pratos([{query_ingredientes}], Pratos)"
    resultado = consultar_prolog(query)

    if resultado:  # Verifica se algum resultado foi encontrado
        pratos_formatados = formatar_resultados(resultado)
        return jsonify({'resultado': ' / '.join(pratos_formatados)})  # Retornando pratos formatados com '/'
    else:
        return jsonify({'resultado': 'Nenhum prato encontrado'})

if __name__ == '__main__':
    app.run(debug=True)
