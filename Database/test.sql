/************** DELETES **************/
DELETE FROM template;
DELETE FROM version;
DELETE FROM keywords;
DELETE FROM history;

/**************  RESET AUTOINCREMENT IDs **************/
ALTER TABLE version AUTO_INCREMENT = 1;
ALTER TABLE keywords AUTO_INCREMENT = 1;
ALTER TABLE template AUTO_INCREMENT = 1;
ALTER TABLE history AUTO_INCREMENT = 1;



/**************  QUERY TESTING **************/ 

/*
SELECT text, keywords, id_template, size FROM template, version WHERE template.version = id_version AND version.type = 1;
SELECT text, keywords, id_template, size, id_version FROM template, version WHERE template.version = id_version AND version.type = 1";
SELECT * FROM keywords WHERE id_keywords = 1;

INSERT INTO history VALUES (DEFAULT, NOW(), 'blablabla', 1, null, null, null, null);
*/
