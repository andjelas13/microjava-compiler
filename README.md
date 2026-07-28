# MicroJava Compiler

A complete compiler for MicroJava — a Java-like teaching language — covering all four compilation phases, from source text to executable bytecode for the MicroJava virtual machine (MJVM).

University of Belgrade, School of Electrical Engineering — Compiler Construction course project.

## Compilation phases

### 1. Lexical analysis — `spec/mjlexer.lex`

A scanner generated with **JFlex**, returning tokens on demand through `next_token()`. It recognizes keywords (`program`, `class`, `namespace`, `const`, `void`, `static`, `extends`, `if`/`else`, `for`, `break`/`continue`, `return`, `new`, `read`/`print`), set operators (`union`, `intersect`, `difference`, `in`, `remove`, `minop`, `maxop`), literals, identifiers and operators — reporting lexical errors with line numbers rather than aborting.

### 2. Syntax analysis — `spec/mjparser.cup`, `spec/mjparser_astbuild.cup`

An LALR parser generated with **AST-CUP**, a CUP extension for building syntax trees, with **error recovery** written into the grammar: instead of stopping at the first mistake the parser synchronizes on `error` productions and continues, so a single run reports many errors at once. Recovery points cover the realistic cases — a malformed declaration recovers at the next `;`, a broken item in a comma-separated list recovers at the next comma, a bad statement recovers at the statement boundary.

The annotated grammar generates the abstract syntax tree automatically — **158 node classes** under `src/rs/ac/bg/etf/pp1/ast/`, plus `Visitor` and `VisitorAdaptor`, so later phases traverse the tree by overriding only the nodes they care about.

### 3. Semantic analysis — `SemanticAnalyzer.java`

A visitor over the AST that builds the symbol table and enforces the language rules:

- redeclaration within a scope, and use of undeclared names
- type compatibility in assignments, expressions, and actual against formal parameters
- `int`-only contexts — array indices, arithmetic, the `##` operator
- `print` argument restricted to `int`, `char`, `bool` or set
- constant declarations checked against their declared type
- `main` rules — must exist, must be `void`, must take no arguments, may not return a value
- `return` outside a function, and a value returned from a `void` function
- sets may not be arrays or matrices

Errors are reported with line numbers through log4j, and an error counter decides whether code generation runs at all.

### 4. Code generation — `CodeGenerator.java`

Emission of MJVM bytecode by a second traversal of the validated tree: method prologues and epilogues sized from counts collected by `CounterVisitor` (constants, global and local variables), expression evaluation onto the operand stack, array and matrix addressing, control flow with backpatched jumps, function calls, and the built-in `read`/`print` operations.

## Language extensions implemented

Beyond the base MicroJava specification:

| Extension | Grammar support |
|---|---|
| **Namespaces** | `namespace Name { declarations { methods } }`, with `namespace::name` resolution |
| **Set type** | `union`, `intersect`, `difference` as assignment operators; membership test `x in s`; `remove`; `minop` / `maxop` reductions over a set |
| **Matrices** | two-dimensional declaration `[][]`, element access `m[i][j]`, allocation, and a dedicated matrix print statement |
| **Sequence swap** | `name # expr, expr #;` — swaps array elements in place |
| **Compound remove-assign** | `designator remop designator` and `designator remop int` |
| **`##` operator** | integer-only, with its own semantic check |
| **Classes** | `class` / `extends` / `static` |

Sets maintain uniqueness — adding an existing element is a no-op — and behave as references on assignment, so two names bound to the same set observe each other's changes.

## Test programs

`test/` contains `.mj` programs written to exercise the features individually and in combination: set creation and printing, duplicate rejection, bulk insertion from an array, `union` over sets, empty-set printing, set aliasing, plus deliberately broken programs that verify each class of syntax and semantic error is detected and recovered from.

## Structure

```
spec/    JFlex lexer specification and CUP grammars
src/     compiler phases + generated parser, lexer and 158 AST node classes
test/    MicroJava test programs (.mj) and the test driver
lib/     JFlex, CUP, MicroJava runtime, symbol table, log4j
config/  log4j configuration
```

## Building and running

```bash
ant
```

The Ant build regenerates the lexer and parser from the specifications and compiles the sources. `Compiler.java` is the entry point: it takes the input `.mj` file and the output path as command-line arguments, parses, prints the syntax tree, runs semantic analysis and dumps the symbol table, then generates the `.obj` bytecode file for the MJVM.

## Technologies

Java, JFlex, AST-CUP, Apache Ant, log4j, MicroJava VM
