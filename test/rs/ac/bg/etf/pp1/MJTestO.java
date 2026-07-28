package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.symboltable.Tab;

public class MJTestO {

    public static void main(String[] args) throws Exception {
        // Log4J setup (kao u Compiler-u)
        DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
        Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
        Logger log = Logger.getLogger(MJTestO.class);

        // Ulazni fajl: ako nema argumenta, koristi test301.mj iz /test
        String srcPath = (args.length >= 1) ? args[0] : "test/test301staro.mj";
        File src = new File(srcPath);
        log.info("Compiling source file: " + src.getAbsolutePath());

        try (Reader br = new BufferedReader(new FileReader(src))) {
            Yylex lexer = new Yylex(br);
            MJParser parser = new MJParser(lexer);

            // PARSE
            Symbol s = parser.parse();

            // Ako nema Program čvora — korektno završi bez pucanja
            if (s == null || !(s.value instanceof Program)) {
                log.error("Parsiranje završeno sa greškama (AST nije kompletan: nema Program čvora).");
                return;
            }

            Program prog = (Program) s.value;

            // Ako su postojale SINTAKSNE greške (oporavak), prekini pre semantike i koda
            if (parser.errorDetected) {
                log.error("Parsiranje završeno sa greškama (sintaksa). Semantika i generisanje koda su preskočeni.");
                return;
            }

            // ——— (opciono) Semantika ———
            Tab.init();
            log.info(prog.toString(""));
            log.info("===================================");

            SemanticAnalyzer sa = new SemanticAnalyzer();
            prog.traverseBottomUp(sa);
            Tab.dump(); // ili Compiler.tsdump();

            if (!sa.passed()) {
                log.error("Parsiranje NIJE uspešno završeno (semantika).");
                return;
            }

            // U ovom testu NE radimo codegen — cilj je proveriti parsiranje/oporavak
            log.info("Parsiranje uspešno završeno!");
        } catch (Exception e) {
            log.error("Došlo je do greške u testu: ", e);
        }
    }
}
