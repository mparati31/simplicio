grammar MathExpression;

@header {package antlr;
}

start
    :   expr EOF
    ;

expr
    :   parens
    |   pow
    |   fraction
    |   sign
    |   mulDiv
    |   sumDiff
    |   number
    ;

// Somme e differenze che si trovano allo stesso livello di parentesizzazione vengono raccolte in modo tale
// che nell'albero di parsing siano tutte figlie dello stesso nodo.
// Lo stesso vale per moltiplicazioni e divisioni.

sumDiff
    :   (parens|number|pow|fraction|sign|mulDiv) (op=('+'|'-') (parens|number|pow|fraction|sign|mulDiv))+
    ;

mulDiv
    :   (parens|number|pow|fraction|sign) (op=('×'|':') (parens|number|pow|fraction|sign))+
    ;

sign
    :   opsign=('+'|'-') (parens|number|pow|fraction|sign)
    ;

// Frazioni e potenze vengono trattate come operazioni binarie quali sono, e quindi raggruppate due a due.

fraction
    :   (parens|number|pow) '/' (parens|number|pow)
    |   fraction '/' (parens|number|pow)
    ;

pow
    :   (parens|number) '^' (parens|number|pow)
    ;

parens
    :   '(' expr ')'
    ;

// Numero in notazione scientifica.
// Il segno del numero non è presente perchè viene utilizzato il segno unario.

number
    :   INT ('.' INT (('E'|'e') '-'? INT)? )?
    ;

POW : '^' ;
FRACT : '/' ;
MUL : '×' ;
DIV : ':' ;
ADD : '+' ;
SUB : '-' ;

INT : [0-9]+ ;
WS  : [ \t]+ -> skip ;