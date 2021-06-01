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

INSERT INTO keywords VALUES (1 ,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 1, 23, 'O "POS_JOG" do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_TOTAL" "COMPETICAO" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 1, 23, 'O "POS_JOG" do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 1);

INSERT INTO keywords VALUES (2 ,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 2, 23, '"NOME_JOG", jogador do "CLUBE", assinalou na última partida a sua presença número "NR_JOGOS_JOG_TOTAL" "COMPETICAO" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 2, 23, '"NOME_JOG", jogador do "CLUBE", atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 1);

INSERT INTO keywords VALUES (3 ,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 3, 24, 'O jogador de "IDADE_JOG" anos do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_TOTAL" "COMPETICAO" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 3, 24, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 1);

INSERT INTO keywords VALUES (4 ,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 4, 17, '"NOME_JOG" assinalou na última partida a sua presença número "NR_JOGOS_JOG_TOTAL" "COMPETICAO" pelo clube.', 1);
INSERT INTO template VALUES (DEFAULT, 1, 4, 17, '"NOME_JOG" atingiu na última partida a marca dos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 1);

INSERT INTO keywords VALUES (5 ,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 5, 11, 'Conta já com um total de "NR_JOGOS_JOG_TOTAL" partidas disputadas pelo clube "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 1, 5, 9, 'Desde que chegou ao clube, já disputou "NR_JOGOS_JOG_TOTAL" partidas "COMPETICAO".', 0);

INSERT INTO keywords VALUES (6 ,1,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 6, 23, 'A presença no último jogo, frente ao "ADVERSARIO", permitiu que "NOME_JOG" alcançasse a marca dos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo "CLUBE".', 1);

INSERT INTO keywords VALUES (7 ,0,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 7, 20, 'O "POS_JOG" atingiu este registo ao cabo de um total de "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube, aos "IDADE_JOG" anos de idade.', 0);

INSERT INTO keywords VALUES (8 ,1,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 8, 19, 'Este registo foi atingido por "NOME_JOG" aos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" no total pelo clube, aos "IDADE_JOG" anos de idade.', 0);


-- version 2 - assunto: info estreia (type 0)
INSERT INTO version VALUES (2,0);

INSERT INTO keywords VALUES (9 ,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 9, 28,'"NOME_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 9, 24,'"NOME_JOG" estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (10 ,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 8, 10,'O "POS_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 8, 10,'O "POS_JOG" estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (11 ,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 11, 25,'Estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 11, 21,'Estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (12 ,0,2,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 12, 31,'O jogador de "IDADE_JOG" anos estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 12, 27,'O jogador de "IDADE_JOG" anos estreou-se pelo "CLUBE" a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (13 ,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 13, 25,'"NOME_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 13, 21,'"NOME_JOG" estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (14 ,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 14, 25,'O "POS_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 14, 21,'O "POS_JOG" estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

INSERT INTO keywords VALUES (15 ,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 2, 15, 22,'Estreou-se a "ESTREIA_JOG", numa partida onde o clube enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".', 0);
INSERT INTO template VALUES (DEFAULT, 2, 15, 18,'Estreou-se pelo clube a "ESTREIA_JOG", frente ao "ADVERSARIO", numa partida que terminou "RESULTADO_ESTREIA".', 0);

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
INSERT INTO template VALUES (DEFAULT, 3, 23, 12, 'Conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO".', 0);
INSERT INTO template VALUES (DEFAULT, 3, 23, 13, 'Desde que chegou ao clube, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);

INSERT INTO keywords VALUES (24,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 3, 24, 18, 'Conta já com "NR_GOLOS_JOG_TOTAL" golos marcados com a camisola do "CLUBE" "COMPETICAO".', 0);
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
INSERT INTO template VALUES (DEFAULT, 4, 32, 14, 'Conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 4, 32, 12, 'Esta época, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (33 ,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 33, 20, 'Conta já com "NR_GOLOS_JOG_TOTAL" golos marcados com a camisola do "CLUBE" "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 4, 33, 15, 'Esta época, já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (34 ,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 4, 34, 16, 'O "POS_JOG" conta já com "NR_GOLOS_JOG_TOTAL" golos marcados pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 4, 34, 12, 'Esta época, o "POS_JOG" já marcou "NR_GOLOS_JOG_TOTAL" golos "COMPETICAO".', 0);


-- version 5 - assunto: info idade, nacionalidade  (type 0)
INSERT INTO version VALUES (5,0);

INSERT INTO keywords VALUES (35 ,0,1,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 35, 25, 'O jogador do "CLUBE", nascido em "NAC_JOG", tem "IDADE_JOG" anos e atingiu esta marca aos "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (36 ,0,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 36, 23, 'Nascido em "NAC_JOG", o "POS_JOG" do "CLUBE" de "IDADE_JOG" anos já participou em "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 0);
INSERT INTO template VALUES (DEFAULT, 5, 36, 21, 'Nascido em "NAC_JOG", o "POS_JOG" de "IDADE_JOG" anos já participou em "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (37 ,0,1,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 36, 18, 'Nascido em "NAC_JOG", o "POS_JOG" de "IDADE_JOG" anos já participou em "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (38 ,0,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 36, 15, 'O "POS_JOG" do "CLUBE" de "IDADE_JOG" anos já participou em "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO", desde a sua estreia pelo clube.', 0);

INSERT INTO keywords VALUES (39 ,0,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 39, 25, 'O "POS_JOG" nascido em "NAC_JOG" atingiu este registo redondo ao cabo de "NR_JOGOS_JOG_TOTAL" jogos "COMPETICAO" pelo clube e com "IDADE_JOG" anos de idade.', 0);

INSERT INTO keywords VALUES (40 ,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 5, 40, 17, 'Nascido em "NAC_JOG", "NOME_JOG" tem "IDADE_JOG" anos e atua como "POS_JOG" pelo "CLUBE".', 0);

-- version 6 - assunto: número jogos nesta época (type 3) 
INSERT INTO version VALUES (6,3);

INSERT INTO keywords VALUES (41 ,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 41, 24, 'O "POS_JOG" do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_EPOCA" "COMPETICAO" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 41, 24, 'O "POS_JOG" do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta época.', 1);

INSERT INTO keywords VALUES (42 ,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 42, 25, '"NOME_JOG", jogador do "CLUBE", assinalou na última partida a sua presença número "NR_JOGOS_JOG_EPOCA" "COMPETICAO" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 42, 25, '"NOME_JOG", jogador do "CLUBE", atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta época.', 1);

INSERT INTO keywords VALUES (43 ,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 43, 27, 'O jogador de "IDADE_JOG" anos do "CLUBE" assinalou no último jogo a sua presença número "NR_JOGOS_JOG_EPOCA" "COMPETICAO" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 43, 26, 'O jogador de "IDADE_JOG" anos do "CLUBE" atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta época.', 1);

INSERT INTO keywords VALUES (44 ,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 44, 19, '"NOME_JOG" assinalou na última partida a sua presença número "NR_JOGOS_JOG_EPOCA" "COMPETICAO" pelo clube esta época.', 1);
INSERT INTO template VALUES (DEFAULT, 6, 44, 19, '"NOME_JOG" atingiu na última partida a marca dos "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta época.', 1);

INSERT INTO keywords VALUES (45 ,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 45, 14, 'Conta já com "NR_JOGOS_JOG_EPOCA" partidas disputadas pelo clube "COMPETICAO" esta época.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 45, 8, 'Esta época, já disputou "NR_JOGOS_JOG_EPOCA" partidas pelo clube.', 0);

INSERT INTO keywords VALUES (46 ,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 46, 32, 'A presença no último jogo, frente ao "ADVERSARIO", permitiu que "NOME_JOG" alcançasse a marca dos "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo "CLUBE" esta época.', 1);

INSERT INTO keywords VALUES (47 ,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 47, 24, 'O "POS_JOG" atingiu este registo ao cabo de "NR_JOGOS_JOG_EPOCA" jogos realizados "COMPETICAO" apenas esta época e com "IDADE_JOG" anos de idade.', 0);

INSERT INTO keywords VALUES (48 ,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 48, 24, 'Este registo foi atingido por "NOME_JOG" aos "NR_JOGOS_JOG_EPOCA" jogos realizados "COMPETICAO" apenas nesta época e com "IDADE_JOG" anos de idade.', 0);


-- version 7 - assunto: info sobre último jogo (type 0)
INSERT INTO version VALUES (7,0);

INSERT INTO keywords VALUES (49 ,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 49, 22,'A última partida disputada, frente ao "ADVERSARIO" e que o "CLUBE" "RESULTADO_JOGO" por "MARCADOR_JORNADA", permitiu-lhe atingir esta marca.', 0);

INSERT INTO keywords VALUES (50 ,1,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 50, 22,'"NOME_JOG" atingiu esta marca nesta jornada "JORNADA", numa partida disputada no "ESTADIO" contra o "ADVERSARIO" que terminou "MARCADOR_JORNADA".', 0);

INSERT INTO keywords VALUES (51 ,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 51, 20,'Atingiu esta marca nesta última jornada "JORNADA", numa partida disputada no "ESTADIO" contra o "ADVERSARIO" que terminou "MARCADOR_JORNADA".', 0);
INSERT INTO template VALUES (DEFAULT, 7, 51, 23,'A última partida disputada pelo clube, referente à jornada "JORNADA" e disputada no "ESTADIO" contra o "ADVERSARIO", terminou "MARCADOR_JORNADA".', 0);

INSERT INTO keywords VALUES (52 ,0,0,1,0,0,0,0,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 52, 22,'O "POS_JOG" atingiu esta marca nesta última jornada "JORNADA", numa partida disputada no "ESTADIO" contra o "ADVERSARIO" que terminou "MARCADOR_JORNADA".', 0);

INSERT INTO keywords VALUES (53 ,0,0,1,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 53, 26,'O "POS_JOG" atingiu esta marca nesta última partida disputada "COMPETICAO" contra o "ADVERSARIO" no "ESTADIO", que terminou "MARCADOR_JORNADA".', 0);

INSERT INTO keywords VALUES (54 ,0,0,1,0,0,0,0,1,0,1,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 54, 22,'Esta marca foi atingida nesta última partida disputada "COMPETICAO" contra o "ADVERSARIO", jogada no "ESTADIO" e apitada por "ARBITRO", que terminou "MARCADOR_JORNADA".', 0);

INSERT INTO keywords VALUES (55 ,1,0,1,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 7, 55, 22,'Esta marca foi atingida nesta última partida disputada "COMPETICAO" contra o "ADVERSARIO", jogada no "ESTADIO" e que terminou "MARCADOR_JORNADA", na qual "NOME_JOG" marcou "NR_GOLOS_JOG_JR".', 0);


-- version 8 - assunto: info sobre epoca anterior (type 0)
INSERT INTO version VALUES (8,0);

INSERT INTO keywords VALUES (56 ,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1);
INSERT INTO template VALUES (DEFAULT, 8, 56, 21,'Na época passada, o "POS_JOG" terminou com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo "CLUBE".', 0);
INSERT INTO template VALUES (DEFAULT, 8, 56, 26,'Relativamente à época transata, o "POS_JOG" havia terminado a mesma com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (57 ,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1);
INSERT INTO template VALUES (DEFAULT, 8, 57, 21,'Na época passada, "NOME_JOG" terminou com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo "CLUBE".', 0);
INSERT INTO template VALUES (DEFAULT, 8, 57, 26,'Relativamente à época transata, o "NOME_JOG" havia terminado a mesma com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (58 ,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1);
INSERT INTO template VALUES (DEFAULT, 8, 58, 19,'Na época passada, terminou com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo "CLUBE".', 0);
INSERT INTO template VALUES (DEFAULT, 8, 58, 24,'Relativamente à época transata, havia terminado a mesma com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (59 ,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1);
INSERT INTO template VALUES (DEFAULT, 8, 59, 18,'Na época passada, o "POS_JOG" terminou com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo clube.', 0);
INSERT INTO template VALUES (DEFAULT, 8, 59, 22,'Relativamente à época transata, o "POS_JOG" havia terminado a mesma com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (60 ,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1);
INSERT INTO template VALUES (DEFAULT, 8, 60, 18,'Na época passada, "NOME_JOG" terminou com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo clube.', 0);
INSERT INTO template VALUES (DEFAULT, 8, 60, 22,'Relativamente à época transata, "NOME_JOG" havia terminado a mesma com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (61 ,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1);
INSERT INTO template VALUES (DEFAULT, 8, 61, 16,'Na época passada, terminou com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo clube.', 0);
INSERT INTO template VALUES (DEFAULT, 8, 61, 20,'Relativamente à época transata, havia terminado a mesma com "NR_GOLOS_JOG_EPOCA_ANT" golos em "NR_JOGOS_JOG_EPOCA_ANT" jogos "COMPETICAO" pelo clube.', 0);


-- version 8 - assunto: info sobre epoca atual (type 0)
INSERT INTO version VALUES (9,0);

INSERT INTO keywords VALUES (62 ,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 62, 26, 'Com "IDADE_JOG" anos, o "POS_JOG" conta, até ao momento, com "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo "CLUBE" esta temporada.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 62, 25, 'Neste momento com "IDADE_JOG" anos, o "POS_JOG" tem, esta época, "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos disputados "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (63 ,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 63, 23, 'O "POS_JOG" conta, até ao momento, com "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo "CLUBE" esta temporada.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 63, 20, 'O "POS_JOG" tem, esta época, "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos disputados "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (64 ,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 64, 23, '"NOME_JOG" conta, até ao momento, com "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo "CLUBE" esta temporada.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 64, 20, '"NOME_JOG" tem, esta época, "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos disputados "COMPETICAO" pelo "CLUBE".', 0);

INSERT INTO keywords VALUES (65 ,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 65, 23, 'Com "IDADE_JOG" anos, o "POS_JOG" conta, até ao momento, com "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta temporada.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 65, 22, 'Neste momento com "IDADE_JOG" anos, o "POS_JOG" tem, esta época, "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos disputados "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (66 ,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 66, 20, 'O "POS_JOG" conta, até ao momento, com "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta temporada.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 66, 17, 'O "POS_JOG" tem, esta época, "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos disputados "COMPETICAO" pelo clube.', 0);

INSERT INTO keywords VALUES (67 ,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO template VALUES (DEFAULT, 6, 67, 20, '"NOME_JOG" conta, até ao momento, com "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos "COMPETICAO" pelo clube esta temporada.', 0);
INSERT INTO template VALUES (DEFAULT, 6, 67, 17, '"NOME_JOG" tem, esta época, "NR_GOLOS_JOG_EPOCA" golos em "NR_JOGOS_JOG_EPOCA" jogos disputados "COMPETICAO" pelo clube.', 0);
