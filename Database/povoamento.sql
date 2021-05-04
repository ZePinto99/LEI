/****************************************/ 
/************** POVOAMENTO **************/ 
/****************************************/ 

/*
	template VALUES (ID, version, keywords, size, text, primary);

types:
	0. 	  Nenhum (conteúdo secundário)
	1.    Golos marcados nesta época 
	2.    Golos marcados (total) 
	3.    Número jogos nesta época
	4.    Número jogos (total)
*/

-- version 1 - assunto: jogos totais pelo clube (type 4)
INSERT INTO version VALUES (1,4);

INSERT INTO keywords VALUES (1 ,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 1, 18, 'O "POS_JOG" do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_TOTAL" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 1, 18, 'O "POS_JOG" do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos pelo clube.', 1);

INSERT INTO keywords VALUES (2 ,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 2, 19, '"NOME_JOG", jogador do "CLUBE", assinalou na última partida a sua presença número "NR_JOGOS_JOG_TOTAL" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 2, 19, '"NOME_JOG", jogador do "CLUBE", atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos pelo clube.', 1);

INSERT INTO keywords VALUES (3 ,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 3, 21, 'O jogador de "IDADE_JOG" anos do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_TOTAL" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 3, 21, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos pelo clube.', 1);

INSERT INTO keywords VALUES (4 ,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 4, 13, '"NOME_JOG" assinalou na última partida a sua presença número "NR_JOGOS_JOG_TOTAL" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 4, 13, '"NOME_JOG" atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos pelo clube.', 1);

INSERT INTO keywords VALUES (5 ,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 5, 8, 'Este conta com "NR_JOGOS_JOG_TOTAL" partidas disputadas pelo clube.', 0);
INSERT INTO template VALUES (DEFAULT, 1, 5, 9, 'Desde que chegou ao clube, já disputou "NR_JOGOS_JOG_TOTAL" partidas.', 0);

INSERT INTO keywords VALUES (6 ,1,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 6, 23, 'A presença no último jogo, frente ao "ADVERSARIO", permitiu que "NOME_JOG" alcançasse a marca dos "NR_JOGOS_JOG_TOTAL" jogos pelo "CLUBE".', 1);

INSERT INTO keywords VALUES (7 ,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 7, 16, 'O "POS_JOG" atingiu este registo ao cabo de "NR_JOGOS_JOG_TOTAL" jogos e com "IDADE_JOG" anos de idade.', 0);

INSERT INTO keywords VALUES (8 ,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 8, 16, 'Este registo foi atingido por "NOME_JOG" aos "NR_JOGOS_JOG_TOTAL" jogos e com "IDADE_JOG" anos de idade.', 0);

-- version 2 - assunto: info estreia (type 0)
INSERT INTO version VALUES (2,0);

INSERT INTO keywords VALUES (9 ,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 9, 28,'"NOME_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 9, 24,'"NOME_JOG" estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (10 ,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 8, 10,'"O POS_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 8, 10,'O "POS_JOG" estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (11 ,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 11, 26,'Este estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 11, 22,'Este estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (12 ,0,2,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 12, 31,'O jogador de "IDADE_JOG" anos estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 12, 27,'O jogador de "IDADE_JOG" anos estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (13 ,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 13, 25,'"NOME_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 13, 21,'"NOME_JOG" estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (14 ,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 14, 25,'"O POS_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 14, 21,'O "POS_JOG" estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (15 ,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 15, 23,'Este estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 15, 19,'Este estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (16 ,0,2,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 16, 28,'O jogador de "IDADE_JOG" anos estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 16, 24,'O jogador de "IDADE_JOG" anos estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);


-- version 3 - assunto: numero golos totais(type 2) 
INSERT INTO version VALUES (3,2);

INSERT INTO keywords VALUES (17 ,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 17, 11, '"NOME_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 17, 12, '"NOME_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (18 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 18, 11, 'O "POS_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 18, 12, 'O "POS_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (19 ,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 19, 14, 'O jogador de "IDADE_JOG" anos alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 19, 15, 'O jogador de "IDADE_JOG" anos atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (20 ,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 20, 17, '"NOME_JOG", jogador do "CLUBE", alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 20, 18, '"NOME_JOG", jogador do "CLUBE", atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (21 ,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 21, 16, 'O "POS_JOG" do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 21, 17, 'O "POS_JOG" do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (22 ,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 22, 19, 'O jogador de "IDADE_JOG" anos do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 22, 20, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (23 ,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 23, 12, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 23, 13, 'Desde que chegou ao clube, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);

INSERT INTO keywords VALUES (4,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 24, 18, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados com a camisola do "CLUBE" "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 24, 16, 'Desde que chegou ao "CLUBE", já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);

INSERT INTO keywords VALUES (25 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 25, 14, 'O "POS_JOG" conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 25, 15, 'Desde que chegou ao clube, o "POS_JOG" já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);


-- version 4 - assunto: golos marcados nesta época (type 1) 
INSERT INTO version VALUES (4,1);

INSERT INTO keywords VALUES (26 ,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 26, 13, '"NOME_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 4, 26, 14, '"NOME_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (27 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 27, 13, 'O "POS_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 4, 27, 14, 'O "POS_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (28 ,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 28, 16, 'O jogador de "IDADE_JOG" anos alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 4, 28, 17, 'O jogador de "IDADE_JOG" anos atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (29 ,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 29, 19, '"NOME_JOG", jogador do "CLUBE", alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 4, 29, 20, '"NOME_JOG", jogador do "CLUBE", atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (30 ,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 30, 18, 'O "POS_JOG" do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 4, 30, 19, 'O "POS_JOG" do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (31 ,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 31, 21, 'O jogador de "IDADE_JOG" anos do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 4, 31, 22, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (32 ,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 32, 14, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 4, 32, 12, 'Esta época, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (33 ,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 33, 20, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados com a camisola do "CLUBE" "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 4, 33, 15, 'Esta época, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (34 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 34, 16, 'O "POS_JOG" conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 4, 34, 12, 'Esta época, o "POS_JOG" já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);


-- version 5 - assunto: info idade, nacionalidade  (type 0)
INSERT INTO version VALUES (5,0);

INSERT INTO keywords VALUES (35 ,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 35, 16, 'O jogador do "CLUBE", nascido em "NAC_JOG", tem "IDADE_JOG" anos e atingiu esta marca aos "NR_JOGOS_JOG_TOTAL".', 0);


-- version 6 - assunto: número jogos nesta época (type 3) 
INSERT INTO version VALUES (6,3);

INSERT INTO keywords VALUES (36 ,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 36, 20, 'O "POS_JOG" do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_EPOCA" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 36, 20, 'O "POS_JOG" do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos pelo clube esta época.', 1);

INSERT INTO keywords VALUES (37 ,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 37, 21, '"NOME_JOG", jogador do "CLUBE", assinalou na última partida a sua presença número "NR_JOGOS_JOG_EPOCA" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 37, 21, '"NOME_JOG", jogador do "CLUBE", atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos pelo clube esta época.', 1);

INSERT INTO keywords VALUES (38 ,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 38, 21, 'O jogador de "IDADE_JOG" anos do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_EPOCA" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 38, 21, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos pelo clube esta época.', 1);

INSERT INTO keywords VALUES (39 ,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 39, 15, '"NOME_JOG" assinalou na última partida a sua presença número "NR_JOGOS_JOG_EPOCA" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 39, 15, '"NOME_JOG" atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos pelo clube esta época.', 1);

INSERT INTO keywords VALUES (40 ,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 40, 8, 'Este conta com "NR_JOGOS_JOG_EPOCA" partidas disputadas pelo clube esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 40, 9, 'Desde que chegou ao clube, já disputou "NR_JOGOS_JOG_EPOCA" partidas.', 0);

INSERT INTO keywords VALUES (41 ,1,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 41, 23, 'A presença no último jogo, frente ao "ADVERSARIO", permitiu que "NOME_JOG" alcançasse a marca dos "NR_JOGOS_JOG_EPOCA" jogos pelo "CLUBE".', 1);

INSERT INTO keywords VALUES (42 ,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 42, 16, 'O "POS_JOG" atingiu este registo ao cabo de "NR_JOGOS_JOG_EPOCA" jogos e com "IDADE_JOG" anos de idade.', 0);

INSERT INTO keywords VALUES (43 ,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 43, 16, 'Este registo foi atingido por "NOME_JOG" aos "NR_JOGOS_JOG_EPOCA" jogos e com "IDADE_JOG" anos de idade.', 0);


-- version 7 - assunto: info sobre último jogo



