package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class Compiler {

    static {
        DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
        Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
    }

    public static void main(String[] args) {
        Logger log = Logger.getLogger(Compiler.class);

        if (args.length < 2) {
            log.error("Usage: Compiler <source-file> <output-file>");
            return;
        }

        Reader br = null;
        try {
            File sourceCode = new File(args[0]);
            log.info("Compiling source file: " + sourceCode.getAbsolutePath());

            br = new BufferedReader(new FileReader(sourceCode));
            Yylex lexer = new Yylex(br);

            MJParser parser = new MJParser(lexer);
            Symbol s = parser.parse();  // Start parsiranja
            if (!(s.value instanceof Program)) {
                System.err.println("Parsiranje nije završilo korektno (nema Program čvora).");
                return;
            }
            Program prog = (Program) s.value;
            

			if (parser.errorDetected) {
			    log.error("Parsiranje završeno sa greškama. Semantika i generisanje koda su preskočeni.");
			    return;
			}
			
            Tab.init();

            // Ispis sintaksnog stabla
            log.info(prog.toString(""));
            log.info("===================================");

            // Semanticka analiza
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            prog.traverseBottomUp(semanticAnalyzer);
            tsdump();

            if (semanticAnalyzer.passed()) {
                File objFile = new File(args[1]);
                if (objFile.exists()) objFile.delete();
                CodeGenerator.initStandardMethods();
                CodeGenerator.initSetMethods(); 
                // Generisanje koda
                CodeGenerator codeGenerator = new CodeGenerator();
                prog.traverseBottomUp(codeGenerator);

             
                Code.dataSize = semanticAnalyzer.nVars;
                Code.mainPc = codeGenerator.getMainPc();

                Code.write(new FileOutputStream(objFile));
                log.info("Generisanje uspešno završeno!");
            } else {
                log.error("Generisanje NIJE uspešno završeno!");
            }

        } catch (Exception e) {
        
            log.error("Došlo je do greške: ", e);
        } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void tsdump() {
        Tab.dump();
    }
}
