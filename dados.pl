% Base de dados de pratos e ingredientes
ingredientes(paella, [arroz, camarao, lula, mexilhao, pimentao, ervilha, acafrao, alho, cebola, azeite, sal, pimenta]).
ingredientes(bife_acebolado, [bife, cebola, alho, azeite, sal, pimenta]).
ingredientes(frango_quiabo, [frango, quiabo, cebola, alho, tomate, azeite, sal, pimenta]).
ingredientes(arroz_carreteiro, [arroz, carne_seca, linguica, cebola, alho, tomate, pimentao, azeite, sal, pimenta]).
ingredientes(lombo_assado_abacaxi, [lombo_de_porco, abacaxi, alho, cebola, azeite, sal, pimenta]).
ingredientes(bobo_camarao, [camarao, mandioca, leite_de_coco, azeite_de_dende, cebola, alho, pimentao, tomate, coentro, sal, pimenta]).
ingredientes(frango_milanesa, [peito_de_frango, farinha_de_rosca, ovo, farinha_de_trigo, sal, pimenta, oleo]).
ingredientes(caldo_verde, [batata, couve, linguica, cebola, alho, azeite, sal, pimenta]).
ingredientes(torta_palmito, [palmito, massa_de_torta, cebola, alho, tomate, azeitona, creme_de_leite, sal, pimenta]).
ingredientes(carne_louca, [carne_de_panela, cebola, alho, pimentao, tomate, azeite, sal, pimenta]).
ingredientes(frango_mostarda_mel, [peito_de_frango, mostarda, mel, alho, azeite, sal, pimenta]).
ingredientes(arroz_grega, [arroz, cenoura, ervilha, milho, passas, cebola, alho, azeite, sal, pimenta]).
ingredientes(file_peixe_molho_maracuja, [file_de_peixe, maracuja, creme_de_leite, cebola, alho, azeite, sal, pimenta]).
ingredientes(cozido_carne_legumes, [carne_de_panela, batata, cenoura, abobrinha, cebola, alho, tomate, louro, sal, pimenta]).
ingredientes(frango_batata_doce, [peito_de_frango, batata_doce, cebola, alho, azeite, sal, pimenta]).
ingredientes(espaguete_alho_oleo, [espaguete, alho, azeite, sal, pimenta, salsinha]).
ingredientes(bife_chorizo_chimichurri, [bife_de_chorizo, alho, salsinha, oregano, vinagre, azeite, sal, pimenta]).
ingredientes(frango_laranja, [peito_de_frango, laranja, alho, cebola, azeite, sal, pimenta]).
ingredientes(arroz_pato, [arroz, pato, cebola, alho, tomate, pimentao, azeite, sal, pimenta]).
ingredientes(salmao_molho_maracuja, [file_de_salmao, maracuja, creme_de_leite, cebola, alho, azeite, sal, pimenta]).
ingredientes(frango_requeijao, [peito_de_frango, requeijao, cebola, alho, azeite, sal, pimenta]).
ingredientes(arroz_lentilhas, [arroz, lentilhas, cebola, alho, azeite, sal, pimenta]).
ingredientes(bife_cavalo, [bife, ovo, alho, azeite, sal, pimenta]).
ingredientes(frango_alho_limao, [peito_de_frango, alho, limao, azeite, sal, pimenta]).

% Regras
listar_ingredientes(Prato) :- 
    ingredientes(Prato, Ingredientes), 
    atomic_list_concat(Ingredientes, ', ', IngredientesSeparados),
    format(' ~w~n', [IngredientesSeparados]).  % Imprime os ingredientes

% Encontrar todos os pratos que contêm os ingredientes buscados
buscar_pratos(IngredientesBuscados, Pratos) :- 
    findall(Prato, 
        (
            ingredientes(Prato, IngredientesPrato),
            subset(IngredientesBuscados, IngredientesPrato),
            write(' '), write(Prato), nl
        ), 
    Pratos).

listar_todos_pratos(Pratos) :-
    findall(Prato, ingredientes(Prato, _), Pratos),
    write(Pratos), nl.  % Escreve a lista de pratos no formato desejado

% Para ver se uma lista é subconjunto da outra
subset([], _).
subset([H|T], Lista) :- 
    member(H, Lista), 
    subset(T, Lista).