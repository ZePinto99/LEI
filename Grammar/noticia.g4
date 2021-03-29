grammar noticia;

@header {
    import java.io.*;
    import java.text.*;
    import java.util.*;
    import java.util.stream.*;
    import java.sql.Timestamp;
}

noticias[Values valores,StringBuilder noticiaIn]:
(keyword[$valores] {$noticiaIn.append($keyword.value);}|texto {$noticiaIn.append($texto.value.replace("\"",""));})+;

texto returns[String value]
@init {$value="";}: string{$value=$string.text;};

keyword[Values valores] returns[String value]
@init {$value="";}: KEY {$value=valores.getValue($KEY.text.replace("\"",""));};

string: WORDS;

WORDS : [a-zA-Z, .-]+ ;

KEY:  '"' (~('"'))* '"' ;

ESTRELAS: '**';

ESTRELASponto : '*?';

WS: ('\r'? '\n' | ' ' | '\t')+ -> skip;
