/************** DELETES **************/
DELETE FROM version;
DELETE FROM keywords;
DELETE FROM template;
DELETE FROM history;

/**************  RESET AUTOINCREMENT IDs **************/
ALTER TABLE version AUTO_INCREMENT = 1;
ALTER TABLE keywords AUTO_INCREMENT = 1;
ALTER TABLE template AUTO_INCREMENT = 1;
ALTER TABLE history AUTO_INCREMENT = 1;

/**************  POVOAMENTO **************/ 
INSERT INTO version VALUES (DEFAULT,1);
INSERT INTO keywords VALUES (DEFAULT,1,0,1,0,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0);
INSERT INTO template VALUES (DEFAULT, 1, 1, 34, 'O "POS_JOG" do "CLUBE" assinalou no ultimo jogo a sua presenca numero "NR_JOGOS_JOG". "NOME_JOG" estreou-se a "ESTREIA_JOG", numa partida onde o "CLUBE" enfrentou o "ADVERSARIO", com um resultado final de "RESULTADO_ESTREIA".',0);

/**************  QUERY TESTING **************/ 
SELECT text, keywords, id_template, size FROM template, version WHERE template.version = id_version AND version.type = 1;
SELECT * FROM keywords WHERE id_keywords = 1;
INSERT INTO history VALUES (DEFAULT, NOW(), 'blablabla', 1, null, null, null, null);