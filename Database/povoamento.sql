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
INSERT INTO template VALUES (DEFAULT, 1, 6, 23, 'A presença no último jogo, frente ao ADVERSARIO, permitiu que NOME_JOG alcançasse a marca dos "NR_JOGOS_JOG_TOTAL" jogos pelo "CLUBE".', 0);


-- version 2 - assunto: info estreia (type 0)
INSERT INTO version VALUES (2,0);

INSERT INTO keywords VALUES (7 ,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 7, 28,'"NOME_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 7, 24,'"NOME_JOG" estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (8 ,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 8, 28,'"O POS_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 8, 24,'O "POS_JOG" estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (9 ,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 9, 26,'Este estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 9, 22,'Este estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (10 ,0,2,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 10, 31,'O jogador de "IDADE_JOG" anos estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 10, 27,'O jogador de "IDADE_JOG" anos estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);


-- version 3 - assunto: numero golos totais(type 2) 
INSERT INTO version VALUES (3,2);

INSERT INTO keywords VALUES (11 ,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 11, 11, '"NOME_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 11, 12, '"NOME_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (12 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 12, 11, 'O "POS_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 12, 12, 'O "POS_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (13 ,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 13, 14, 'O jogador de "IDADE_JOG" anos alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 13, 15, 'O jogador de "IDADE_JOG" anos atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (14 ,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 14, 17, '"NOME_JOG", jogador do "CLUBE", alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 14, 18, '"NOME_JOG", jogador do "CLUBE", atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (15 ,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 15, 16, 'O "POS_JOG" do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 15, 17, 'O "POS_JOG" do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (16 ,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 16, 19, 'O jogador de "IDADE_JOG" anos do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO".', 1);
INSERT INTO template VALUES (DEFAULT, 3, 16, 20, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 1);

INSERT INTO keywords VALUES (17 ,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 17, 12, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 17, 13, 'Desde que chegou ao clube, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);

INSERT INTO keywords VALUES (18 ,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 18, 18, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados com a camisola do "CLUBE" "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 18, 16, 'Desde que chegou ao "CLUBE", já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);

INSERT INTO keywords VALUES (19 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 19, 14, 'O "POS_JOG" conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 19, 15, 'Desde que chegou ao clube, o "POS_JOG" já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);


-- version 4 - assunto: golos marcados nesta época (type 1) 
INSERT INTO version VALUES (4,1);

INSERT INTO keywords VALUES (20 ,1,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 11, 11, '"NOME_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 3, 11, 12, '"NOME_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (21 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 12, 11, 'O "POS_JOG" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 3, 12, 12, 'O "POS_JOG" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (22 ,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 13, 14, 'O jogador de "IDADE_JOG" anos alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 3, 13, 15, 'O jogador de "IDADE_JOG" anos atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (23 ,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 14, 17, '"NOME_JOG", jogador do "CLUBE", alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 3, 14, 18, '"NOME_JOG", jogador do "CLUBE", atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (24 ,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 15, 16, 'O "POS_JOG" do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 3, 15, 17, 'O "POS_JOG" do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (25 ,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 16, 19, 'O jogador de "IDADE_JOG" anos do "CLUBE" alcançou o golo número "NR_GOLOS_JOG_TOTAL" "COMPETICAO" esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 3, 16, 20, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu a marca dos "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 1);

INSERT INTO keywords VALUES (26 ,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 17, 12, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 3, 17, 13, 'Desde que chegou ao clube, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 0);

INSERT INTO keywords VALUES (27 ,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 18, 18, 'Este conta com "NR_GOLOS_JOG_TOTAL" golos marcados com a camisola do "CLUBE" "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 3, 18, 16, 'Desde que chegou ao "CLUBE", já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 0);

INSERT INTO keywords VALUES (28 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 19, 14, 'O "POS_JOG" conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 3, 19, 15, 'Desde que chegou ao clube, o "POS_JOG" já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" esta época.', 0);


-- version 5 - assunto: número jogos nesta época (type 3) 



