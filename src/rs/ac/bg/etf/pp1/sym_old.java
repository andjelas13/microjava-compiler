package rs.ac.bg.etf.pp1;

/** CUP generated class containing symbol constants. */
public class sym_old {
    /* Terminals */
    public static final int EOF = 0;
    public static final int error = 1;

    /* Keywords */
    public static final int PROG = 2; // "program"
    public static final int BREAK = 3; // "break"
    public static final int CLASS = 4; // "class"
    public static final int ELSE = 5; // "else"
    public static final int CONST = 6; // "const"
    public static final int IF = 7; // "if"
    public static final int NEW = 8; // "new"
    public static final int PRINT = 9; // "print"
    public static final int READ = 10; // "read"
    public static final int RETURN = 11; // "return"
    public static final int VOID = 12; // "void"
    public static final int EXTENDS = 13; // "extends"
    public static final int CONTINUE = 14; // "continue"
    public static final int FOR = 15; // "for"
    public static final int STATIC = 16; // "static"
    public static final int NAMESPACE = 17; // "namespace"

    /* Identifiers and Constants */
    public static final int IDENT = 18; // ident = letter {letter | digit | '_'}
    public static final int INT = 19; // numConst = digit {digit}
    public static final int CHAR = 20; // charConst = ' printableChar '
    public static final int BOOL = 21; // boolConst = ("true" | "false")

    /* Operators */
    public static final int PLUS = 22; // '+'
    public static final int MINUS = 23; // '-'
    public static final int MUL = 24; // '*'
    public static final int DIV = 25; // '/'
    public static final int MOD = 26; // '%'
    public static final int EQ = 27; // '=='
    public static final int NEQ = 28; // '!='
    public static final int GT = 29; // '>'
    public static final int GEQ = 30; // '>='
    public static final int LT = 31; // '<'
    public static final int LEQ = 32; // '<='
    public static final int AND = 33; // '&&'
    public static final int OR = 34; // '||'
    public static final int EQUAL = 35; // '='
    public static final int INC = 36; // '++'
    public static final int DEC = 37; // '--'

    /* Delimiters */
    public static final int SEMI = 38; // ';'
    public static final int COLON = 39; // ':'
    public static final int COMMA = 40; // ','
    public static final int DOT = 41; // '.'
    public static final int LPAREN = 42; // '('
    public static final int RPAREN = 43; // ')'
    public static final int LBRACKET = 44; // '['
    public static final int RBRACKET = 45; // ']'
    public static final int LBRACE = 46; // '{'
    public static final int RBRACE = 47; // '}'

    /* Special Symbols */
    public static final int LAMBDA = 48; // '=>'
    public static final int DCOLON = 49; // '::'
}
