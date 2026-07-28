package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());
	private int mainPc;
	private boolean returnFound = false;
	private boolean matix = false;
	private final Struct boolType = Tab.find("bool").getType();
	private final Struct setType  = Tab.find("set").getType();

	public int getMainPc() {
		return mainPc;
	}
	
	public static void initSetMethods() {
	    genziAdd();
	    geneziAll();
	    genzieRemove();
	    generateCon() ;
	    emMinBody();
	    emMaxBody();
	    generateRemoveAll(); 
	}
	
	// add(set s, int e)
	
	
	private static void genTrap(int code) {
	    Code.put(Code.trap);
	    Code.put(code);
	}
	private static void emMaxBody() {
//	    Obj maxF = Tab.find("max");
//	    maxF.setAdr(Code.pc);
//
//	    // enter(#form=1 [S], #locals=2 [max, i])
//	    Code.put(Code.enter);
//	    Code.put(1);
//	    Code.put(2);
//
//	    // if (s[0] == 0) trap(2);
//	    Code.put(Code.load_n);   // s
//	    Code.loadConst(0);       // s, 0
//	    Code.put(Code.aload);    // size
//	    Code.loadConst(0);       // size, 0
//	    int ok = Code.pc + 1;
//	    Code.putFalseJump(Code.eq, 0);  // ako size != 0 -> preskoči trap
//	    genTrap(2);                     // ako size == 0 -> trap
//	    Code.fixup(ok);
//	    // max = S[1];
//	    Code.put(Code.load_n);   // S
//	    Code.loadConst(1);       // S, 1
//	    Code.put(Code.aload);    // S[1]
//	    Code.put(Code.store_n + 1); // max = S[1]
//
//	    // i = 2;
//	    Code.loadConst(2);
//	    Code.put(Code.store_n + 2);
//
//	    int loop = Code.pc;
//
//	    // if (!(i <= S[0])) break;
//	    Code.put(Code.load_n + 2); // i
//	    Code.put(Code.load_n);     // i, S
//	    Code.loadConst(0);         // i, S, 0
//	    Code.put(Code.aload);      // i, size
//	    Code.putFalseJump(Code.le, 0);
//	    int jEnd = Code.pc - 2;
//
//	    // val = S[i];
//	    Code.put(Code.load_n);     // S
//	    Code.put(Code.load_n + 2); // S, i
//	    Code.put(Code.aload);      // val
//
//	    // if (val > max) max = val; else pop val
//	    Code.put(Code.dup);        // val, val
//	    Code.put(Code.load_n + 1); // val, val, max
//	    Code.putFalseJump(Code.gt, 0);
//	    int jNoUpd = Code.pc - 2;
//
//	    // then: max = val
//	    Code.put(Code.store_n + 1);
//	    int jCont = Code.pc + 1;
//	    Code.putJump(0);
//
//	    // else:
//	    Code.fixup(jNoUpd);
//	    Code.put(Code.pop);
//
//	    // cont:
//	    Code.fixup(jCont);
//
//	    // i++
//	    Code.put(Code.load_n + 2);
//	    Code.loadConst(1);
//	    Code.put(Code.add);
//	    Code.put(Code.store_n + 2);
//	    Code.putJump(loop);
//
//	    // kraj
//	    Code.fixup(jEnd);
//	    Code.put(Code.load_n + 1); // return max
//	    Code.put(Code.exit);
//	    Code.put(Code.return_);
	}

	
	private static void emMinBody() {
//		 Obj minF = Tab.find("min");
//		    minF.setAdr(Code.pc);
//
//		    // enter(#form=1 [S], #locals=2 [min, i])
//		    Code.put(Code.enter);
//		    Code.put(1);
//		    Code.put(2);
//
//		    // if (s[0] == 0) trap(2);
//		    Code.put(Code.load_n);
//		    Code.loadConst(0);
//		    Code.put(Code.aload);
//		    Code.loadConst(0);
//		    int ok = Code.pc + 1;
//		    Code.putFalseJump(Code.eq, 0); // ako size != 0 -> preskoči trap
//		    genTrap(2);                    // ako size == 0 -> trap
//		    Code.fixup(ok);
//
//		    // min = S[1];
//		    Code.put(Code.load_n);   // S
//		    Code.loadConst(1);       // S, 1
//		    Code.put(Code.aload);    // S[1]
//		    Code.put(Code.store_n + 1); // min = S[1]
//
//		    // i = 2;
//		    Code.loadConst(2);
//		    Code.put(Code.store_n + 2);
//
//		    int loop = Code.pc;
//
//		    // if (!(i <= S[0])) break;
//		    Code.put(Code.load_n + 2); // i
//		    Code.put(Code.load_n);     // i, S
//		    Code.loadConst(0);         // i, S, 0
//		    Code.put(Code.aload);      // i, size
//		    Code.putFalseJump(Code.le, 0);
//		    int jEnd = Code.pc - 2;
//
//		    // val = S[i]
//		    Code.put(Code.load_n);     // S
//		    Code.put(Code.load_n + 2); // S, i
//		    Code.put(Code.aload);      // val
//
//		    // if (val < min) min = val; else (pop val)
//		    Code.put(Code.dup);        // val, val
//		    Code.put(Code.load_n + 1); // val, val, min
//		    Code.putFalseJump(Code.lt, 0);
//		    int jNoUpd = Code.pc - 2;
//
//		    // then: min = val (potroši preostali val)
//		    Code.put(Code.store_n + 1);
//		    int jCont = Code.pc + 1;
//		    Code.putJump(0);
//
//		    // else: odbaci val
//		    Code.fixup(jNoUpd);
//		    Code.put(Code.pop);
//
//		    // cont:
//		    Code.fixup(jCont);
//
//		    // i++
//		    Code.put(Code.load_n + 2);
//		    Code.loadConst(1);
//		    Code.put(Code.add);
//		    Code.put(Code.store_n + 2);
//		    Code.putJump(loop);
//
//		    // kraj
//		    Code.fixup(jEnd);
//		    Code.put(Code.load_n + 1); // vrati min
//		    Code.put(Code.exit);
//		    Code.put(Code.return_);
		
	}


	
	private static void generateCon() {
		
		    Obj contains = Tab.find("contains");   // mora postojati u Tab-u (vidi tačku 3)
		    contains.setAdr(Code.pc);

		    // formals=2 (s, e), locals=3 (s, e, i)
		    Code.put(Code.enter);
		    Code.put(2);
		    Code.put(3);

		    // i = 1;
		    Code.loadConst(1);
		    Code.put(Code.store_n + 2);

		    int loop = Code.pc;
		    // if (!(i <= s[0])) return 0;
		    Code.put(Code.load_n + 2);  // i
		    Code.put(Code.load_n);      // i, s
		    Code.loadConst(0);          // i, s, 0
		    Code.put(Code.aload);       // i, size
		    Code.putFalseJump(Code.le, 0);
		    int jNotFound = Code.pc - 2;

		    // if (s[i] == e) return 1;
		    Code.put(Code.load_n);      // s
		    Code.put(Code.load_n + 2);  // s, i
		    Code.put(Code.aload);       // s[i]
		    Code.put(Code.load_n + 1);  // s[i], e
		    Code.putFalseJump(Code.eq, 0);
		    int jContinue = Code.pc - 2;

		    // found:
		    Code.loadConst(1);
		    Code.put(Code.exit);
		    Code.put(Code.return_);

		    // continue: i++
		    Code.fixup(jContinue);
		    Code.put(Code.load_n + 2);
		    Code.loadConst(1);
		    Code.put(Code.add);
		    Code.put(Code.store_n + 2);
		    Code.putJump(loop);

		    // not found:
		    Code.fixup(jNotFound);
		    Code.loadConst(0);
		    Code.put(Code.exit);
		    Code.put(Code.return_);
		
		
	}
		
	
	private static void genzieRemove() {
		 Obj rem = Tab.find("remove");
		    rem.setAdr(Code.pc);

		    Code.put(Code.enter);
		    Code.put(2); // formali: s, e
		    Code.put(3); // lokali:  s, e, i

		    // i = 1
		    Code.loadConst(1);
		    Code.put(Code.store_n + 2);

		    int loop = Code.pc;
		    // while (i <= s[0]) ...
		    Code.put(Code.load_n + 2); // i
		    Code.put(Code.load_n);     // s
		    Code.loadConst(0);
		    Code.put(Code.aload);      // size
		    Code.putFalseJump(Code.le, 0);
		    int jNotFound = Code.pc - 2;

		    // if (s[i] == e) found
		    Code.put(Code.load_n);     // s
		    Code.put(Code.load_n + 2); // i
		    Code.put(Code.aload);      // s[i]
		    Code.put(Code.load_n + 1); // e
		    Code.putFalseJump(Code.eq, 0);
		    int jContinue = Code.pc - 2;

		    // --- shift left from i ---
		    int shift = Code.pc;
		    // while (i < s[0]) { s[i] = s[i+1]; i++; }
		    int loop2 = Code.pc;
		    Code.put(Code.load_n + 2); // i
		    Code.put(Code.load_n);     // s
		    Code.loadConst(0);
		    Code.put(Code.aload);      // size
		    Code.putFalseJump(Code.lt, 0);
		    int jDoneShift = Code.pc - 2;

		    // s[i] = s[i+1]
		    Code.put(Code.load_n);         // s
		    Code.put(Code.load_n + 2);     // i
		    Code.put(Code.load_n);         // s
		    Code.put(Code.load_n + 2);     // i
		    Code.loadConst(1);
		    Code.put(Code.add);            // i+1
		    Code.put(Code.aload);          // s[i+1]
		    Code.put(Code.astore);         // s[i] = s[i+1]

		    // i++
		    Code.put(Code.load_n + 2);
		    Code.loadConst(1);
		    Code.put(Code.add);
		    Code.put(Code.store_n + 2);
		    Code.putJump(loop2);

		    Code.fixup(jDoneShift);

		    // s[0] = s[0] - 1;
		    Code.put(Code.load_n);  // s
		    Code.loadConst(0);
		    Code.put(Code.load_n);  // s
		    Code.loadConst(0);
		    Code.put(Code.aload);   // size
		    Code.loadConst(1);
		    Code.put(Code.sub);
		    Code.put(Code.astore);
		    // return
		    Code.put(Code.exit);
		    Code.put(Code.return_);

		    // continue: i++
		    Code.fixup(jContinue);
		    Code.put(Code.load_n + 2);
		    Code.loadConst(1);
		    Code.put(Code.add);
		    Code.put(Code.store_n + 2);
		    Code.putJump(loop);

		    // not found -> return
		    Code.fixup(jNotFound);
		    Code.put(Code.exit);
		    Code.put(Code.return_);	
//	    Obj addMeth = Tab.find("remove");
//	    addMeth.setAdr(Code.pc);
//	   
//	    Code.put(Code.enter);
//	    Code.put(2); 
//	    Code.put(3);
//
//	   
//	    Code.put(Code.load_n+1);  // STACK: , e
//	    Code.put(Code.load_n);      // STACK:es
//	    Code.loadConst(0);          // STACK: es, 0
//	    Code.put(Code.aload);       // STACK: e size     ako je e > size skaci na kraj  manje jednako
//	    // Jmp ako !(size > e) --- ako size < e  → van opsega => return 
//	    Code.putFalseJump(Code.le, 0);  //da li je size vec od i jeste nastavi, nije skoci 
//	    
//	    int jover = Code.pc - 2;    // fixup na EXIT/RETURN dole
//	    Code.put(Code.load_n+1); //e   ako je e < 1 skaci  vec jed
//	    Code.loadConst(1); // E, 1        E < ili JEDako  1 SKACI  da li je e manje ili jednako od 1  le 
//	    Code.putFalseJump(Code.ge, 0); 
//	    int jexit = Code.pc - 2;    // manji od opsega izadjemo 
//	    
//	    Code.put(Code.load_n);      // STACK: s
//	    Code.loadConst(0);          // STACK: s, 0
//	    Code.put(Code.aload); // size
//	    Code.put(Code.load_n+1); //ako je jednako siye poslednji elem //siye, e 
//	    Code.putFalseJump(Code.ne, 0); 
//	    int notsize = Code.pc - 2;  
//	    
//	    /// ELEM U SEDINI FOR PETLJA 
//	
//	    Code.put(Code.load_n+1);//E
//	    Code.put(Code.store_n + 2); // I=E
//	    
// 
//	    int loop = Code.pc;
//	    
//	    Code.put(Code.load_n); //S 
//	    Code.loadConst(0);          // STACK: s, 0
//	    Code.put(Code.aload);
//	    Code.put(Code.load_n+2); //sizee,j 
//	    Code.putFalseJump(Code.ne, 0); 
//	    int kraj = Code.pc - 2;  
//	    
//
//	    Code.put(Code.load_n); //S 
//	    Code.put(Code.load_n+2);// S , J 
//	    
//	    Code.put(Code.load_n); //S , j, s
//	    Code.put(Code.load_n+2); //s, j, s, j
//	    Code.loadConst(1);// // s, j, s , j, 1
//	    Code.put(Code.add);// s, j, s, j+1
//	    Code.put(Code.aload); // s , j , s[j+11] 
//	    Code.put(Code.astore);  // smestili 
//	    
//	    Code.put(Code.load_n+2); //j
//	    Code.loadConst(1);//  j, 1
//	    Code.put(Code.add); //j+1 
//	    Code.put(Code.store_n + 2); 
//	    Code.putJump(loop); 
//	    
//	    Code.fixup(kraj);
//
//	    //KRAJ SMANJ VEL SKUPA
//	    
//	    Code.fixup(notsize);// za kraj 
//	    Code.put(Code.load_n);  // s
//	    Code.loadConst(0);  //s, 0
//	    Code.put(Code.load_n);  // s, 0, s
//	    Code.loadConst(0);  //s, 0,
//	    Code.put(Code.aload); //s, 0,  size 
//	    Code.loadConst(1); // s, 0 , size ,1 
//	    Code.put(Code.sub);    //s, 0, size-1 
//	    Code.put(Code.astore); // smanjili vel skupa za 1
//	     
//	         
//	    Code.fixup(jover);          
//	    Code.fixup(jexit);  
//	    Code.put(Code.exit);
//	    Code.put(Code.return_);
//	
//		
		
	}
 
	private static void genziAdd() {
	    Obj addMeth = Tab.find("add");
	    addMeth.setAdr(Code.pc);

	    // enter(#form=2, #locals=3) ,  lokalni: 0:s, 1:e, 2:i
	    Code.put(Code.enter);
	    Code.put(2); // formalni(s, e)
	    Code.put(3); // lokalni (s, e, i)

	    //  size = s[0] 
	    Code.put(Code.load_n);      // STACK: s
	    Code.loadConst(0);          // STACK: s, 0
	    Code.put(Code.aload);       // STACK: size          (size := s[0])

	    //  cap = s.length - 1 ; ako size >= cap => return 
	    Code.put(Code.load_n);  // STACK: size, s
	    Code.put(Code.arraylength); // STACK: size, len
	    Code.loadConst(1);          // STACK: size, len, 1
	    Code.put(Code.sub);         // STACK: size, cap     (cap := len-1)

	    // Jmp ako !(size < cap) --- ako size >= cap → nema mesta => return
	    Code.putFalseJump(Code.lt, 0); 
	    int jFull = Code.pc - 2;    // fixup na EXIT/RETURN dole

	    // i = 1 
	    Code.loadConst(1);          // STACK: 1
	    Code.put(Code.store_n + 2); // i := 1  (STACK: —)

	    //  while (i <= size) 
	    int loop = Code.pc;

	    Code.put(Code.load_n + 2);  // STACK: i
	    Code.put(Code.load_n);      // STACK: i, s
	    Code.loadConst(0);          // STACK: i, s, 0
	    Code.put(Code.aload);       // STACK: i, size
	    // Ako !(i <= size) ---  i > size → break
	    Code.putFalseJump(Code.le, 0);
	    int jBreak = Code.pc - 2;

	    //  if (s[i] == e) return; 
	    Code.put(Code.load_n);      // STACK: s
	    Code.put(Code.load_n + 2);  // STACK: s, i
	    Code.put(Code.aload);       // STACK: s[i]
	    Code.put(Code.load_n + 1);  // STACK: s[i], e
	    // Ako !(s[i] != e) --- s[i] == e → return
	    Code.putFalseJump(Code.ne, 0);
	    int jRet = Code.pc - 2;

	    //  i++ 
	    Code.put(Code.load_n + 2);  // STACK: i
	    Code.loadConst(1);          // STACK: i, 1
	    Code.put(Code.add);         // STACK: i+1
	    Code.put(Code.store_n + 2); // i := i+1   (STACK: —)
	    Code.putJump(loop);         // nazad na uslov

	    // break:kraj 
	    Code.fixup(jBreak);

	    // s[size+1] = e
	    Code.put(Code.load_n);      // STACK: s
	    Code.put(Code.load_n);      // STACK: s, s
	    Code.loadConst(0);          // STACK: s, s, 0
	    Code.put(Code.aload);       // STACK: s, size
	    Code.loadConst(1);          // STACK: s, size, 1
	    Code.put(Code.add);         // STACK: s, size+1
	    Code.put(Code.load_n + 1);  // STACK: s, size+1, e
	    Code.put(Code.astore);      // STACK: —               (s[size+1] := e)

	    // s[0] = size+1
	    Code.put(Code.load_n);      // STACK: s
	    Code.loadConst(0);          // STACK: s, 0
	    Code.put(Code.load_n);      // STACK: s, 0, s
	    Code.loadConst(0);          // STACK: s, 0, s, 0
	    Code.put(Code.aload);       // STACK: s, 0, size
	    Code.loadConst(1);          // STACK: s, 0, size, 1
	    Code.put(Code.add);         // STACK: s, 0, size+1
	    Code.put(Code.astore);      // STACK: —               (s[0] := size+1)

	    //  return
	    Code.fixup(jRet);           
	    Code.fixup(jFull);          

	    Code.put(Code.exit);
	    Code.put(Code.return_);
	}


	private static void geneziAll() {
	    Obj addAll  = Tab.find("addAll");
	    Obj addMeth = Tab.find("add");  
	    addAll.setAdr(Code.pc);

	    Code.put(Code.enter);
	    Code.put(2); // #formalni = 2  (local0: s, local1: arr)
	    Code.put(3); // #localni  = 3  (local2: i)

	    // i = 0;
	    Code.loadConst(0);           // STACK: 0
	    Code.put(Code.store_n + 2);  // i := 0   STACK: —

	    int loop = Code.pc;

	    // if (i >= arr.length) break;
	    Code.put(Code.load_n + 2);   // STACK: i
	    Code.put(Code.load_n + 1);   // STACK: i, arr
	    Code.put(Code.arraylength);  // STACK: i, len
	    // putFalseJump(Code.lt, target) skok ako (i < len) == false,ako i >= len
	    Code.putFalseJump(Code.lt, 0);
	    int jEnd = Code.pc - 2;     

	    //  add(s, arr[i]) 
	
	    Code.put(Code.load_n);       // STACK: s

	    Code.put(Code.load_n + 1);   // STACK: s, arr
	    Code.put(Code.load_n + 2);   // STACK: s, arr, i
	    Code.put(Code.aload);        // STACK: s, elem  

	    // call add(s, elem) 
	    Code.put(Code.call);
	    Code.put2(addMeth.getAdr() - Code.pc +1 ); 

	 
	    // i++
	    Code.put(Code.load_n + 2);   // STACK: i
	    Code.loadConst(1);           // STACK: i, 1
	    Code.put(Code.add);          // STACK: i+1
	    Code.put(Code.store_n + 2);  // i := i+1   STACK: —

	    Code.putJump(loop);


	    Code.fixup(jEnd);

	    Code.put(Code.exit);
	    Code.put(Code.return_);
	}

	
	public void visit(MinSetOp n) {
	    Obj min = Tab.find("min");
	    Code.load(n.getDesignatorName().obj);      // gurne set
	    Code.put(Code.call);
	    Code.put2(min.getAdr() - Code.pc + 1);     // ostavlja int (min) na steku
	}
	public void visit(MaxSetOp n) {
	    Obj max = Tab.find("max");
	    Code.load(n.getDesignatorName().obj);
	    Code.put(Code.call);
	    Code.put2(max.getAdr() - Code.pc + 1);
	}



	public void visit(PrintStatementLabel print) {
	    Struct exprType = print.getExpr().struct;
	  
	    if (exprType == setType) {
		        //  setRef
		        Code.put(Code.dup);              //  setRef, setRef
		        Code.loadConst(0);               //  setRef, setRef,0
		        Code.put(Code.aload);            //  setRef, n = setRef[0]
		        Code.loadConst(0);               // setref, n  ,0
		                                         // ako je n <= 0 -> preskocimo print
		        Code.putFalseJump(Code.gt, 0);   // setref
		        int endEmpty = Code.pc - 2;      //  setRef
		         
		        // i = 1
		        Code.loadConst(1);               // setref , i
		        int loopStart = Code.pc;

		        // print(setRef[i])
		        Code.put(Code.dup2);             // setref , i, setRef, i
		        Code.put(Code.aload);            // setr,  i, value
		        Code.loadConst(5);
		        Code.put(Code.print);            // setref, i  

		        // i++
		        Code.loadConst(1);  
		        Code.put(Code.add);              //  setref, i+1
		        
		        Code.put(Code.dup2);             //  S, i+1, S, i+1
		        Code.put(Code.dup_x1); 
		        Code.put(Code.pop);              //S,I,i, S ,0 
		        Code.loadConst(0);
		        Code.put(Code.aload);            // ..., i+1, n = S[0]
		        Code.putFalseJump(Code.gt, loopStart);  
		                                        // ako i+1 <= n -> nazad
		        Code.put(Code.pop);              // skinem i
		     
		        
		        Code.fixup(endEmpty);
		      
	            Code.put(Code.pop); 

		      
		    }


	    else if (exprType == Tab.intType ) {
	        Code.loadConst(5);
	        Code.put(Code.print);
	    }
	  
	    else if (exprType == Tab.charType) {
	        Code.loadConst(1);
	        Code.put(Code.bprint);
	    }
	    	
	    	
	    else if (exprType == boolType) {
	        Code.loadConst(1);   // širina
	        Code.put(Code.print); 
	    }
	}

	public void visit(NumConstLabel numConst) {
		Code.loadConst(numConst.getI1());
	}

	public void visit(CharConstLabel charConst) {
		Code.loadConst(charConst.getC1());
	}

	public void visit(BoolConstLabel boolConst) {
		Code.loadConst(boolConst.getB1() ? 1 : 0);
	}

	public void visit(VoidReturnLabel type) {
		Obj methodObj = ((MethodSignature) (type.getParent())).obj;
		if ("main".equalsIgnoreCase(methodObj.getName())) {
			mainPc = Code.pc;
		}
		methodObj.setAdr(Code.pc);

		 SyntaxNode methodNode = ((MethodSignature) type.getParent()).getParent();

		CounterVisitor.VarCounter varCnt = new CounterVisitor.VarCounter();
		CounterVisitor.FormParamCounter formCnt = new CounterVisitor.FormParamCounter();
		methodNode.traverseTopDown(varCnt);
	    methodNode.traverseTopDown(formCnt);
		int nForm = formCnt.getCount();
		int nLoc  = varCnt.getCount();
		
		Code.put(Code.enter);
		Code.put(nForm);
		Code.put(nForm + nLoc);
	}

//    
	public void visit(IncrementDesignatorLabel inc) {
	    // Uvek prvo ucitam trenutnu vrednost
	    Obj d = ((DesignatorStatements) inc.getParent()).getDesignator().obj;
	    Code.load(d);           
	    Code.loadConst(1);
	    Code.put(Code.add);
	}

	public void visit(DecrementDesignatorLabel dec) {
	    Obj d = ((DesignatorStatements) dec.getParent()).getDesignator().obj;
	    Code.load(d);            
	    Code.loadConst(1);
	    Code.put(Code.sub);
	}


	public void visit(MethodDeclList methodDecl) {

		Code.put(Code.exit);
		Code.put(Code.return_);
	}



	public void visit(DesignatorStatements designatorStatement) {

	    // DODELA
	    if (designatorStatement.getDesignatorList() instanceof AssignDesignatorLabel) {

	        AssignDesignatorLabel assignList = (AssignDesignatorLabel) designatorStatement.getDesignatorList();

	        // DODELA IZRAZA
	        if (assignList.getAssignTail() instanceof AssignExprLabel) {
	            this.visitcount = 0;
	            Code.store(designatorStatement.getDesignator().obj);

	            if (this.matix) {
	                Code.put(Code.pop);
	                this.matix = false;
	            }
	        }

	        // DODELA SKUPA 
	        else if (assignList.getAssignTail() instanceof AssignSetopLabel) {
	          
//	            Code.store(designatorStatement.getDesignator().obj);
	        }
	    }
	    else if (designatorStatement.getDesignatorList() instanceof MethodCallDesignatorLabel) {
	        Obj m = designatorStatement.getDesignator().obj;   // ovo je Obj.Meth
	        Code.put(Code.call);
	        Code.put2(m.getAdr() - Code.pc+1);
	     
	        if (m.getType() != Tab.noType) Code.put(Code.pop);
	        return;
	    }

	
	    else if (designatorStatement.getDesignatorList() instanceof IncrementDesignatorLabel) {
	        Code.store(designatorStatement.getDesignator().obj);
	    }


	    else if (designatorStatement.getDesignatorList() instanceof DecrementDesignatorLabel) {
	        Code.store(designatorStatement.getDesignator().obj);
	    }


	}
	
	@Override
	public void visit(DesignatorNames dn) {
	    Obj o = dn.obj;
	    SyntaxNode p = dn.getParent();

	    if (o.getKind() == Obj.Meth) return;

	    if (p instanceof MethodCallExpr || p instanceof MethodCallDesignatorLabel) return;

	    if (p instanceof DesignatorStatements) return;         
	    if (p instanceof ReadStatementLabel) return;
	    if (p instanceof AssignSetopLabel) return;         
	    if (p instanceof AssignRemD) return;      
	    if (p instanceof AssignRemInt) return;    

	    //  R-value 
	    Code.load(o);
	}


	
	
	public void visit(AssignSetopLabel n) {
	    Obj target = ((DesignatorStatements)((AssignDesignatorLabel)n.getParent()).getParent()).getDesignator().obj;
	    Obj left   = n.getDesignator().obj;
	    Obj right  = n.getDesignator1().obj;

	  

	    //  target[0] = 0
	    Code.load(target);
	    Code.loadConst(0);
	    Code.loadConst(0);
	    Code.put(Code.astore);

	    if (n.getSetop() instanceof SetopUnion) {
	        // UNION 
	        Obj add = Tab.find("add");
	        genAddAllElems(target, left, add);
	        genAddAllElems(target, right, add);
	        return;
	    }

	    if (n.getSetop() instanceof SetopInter) {
	    	 Obj add      = Tab.find("add");
	         Obj contains = Tab.find("contains");   // vidi tačku 2)
	         genInterElems(target, left, right, add, contains);
	        return;
	    }
	    if (n.getSetop() instanceof SetopDiffer) {
	        Obj add = Tab.find("add");
	        Obj contains = Tab.find("contains");
	        genDiffElems(target, left, right, add, contains);  // NOVO
	        return;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	}

	
	

	
	public void visit(AssignRemD n) {
	    Obj target = ((DesignatorStatements)((AssignDesignatorLabel)n.getParent()).getParent()).getDesignator().obj;
	    Obj left   = n.getDesignator().obj;
	    Obj right  = n.getDesignator1().obj;

	  

	    //  target[0] = 0
	    Code.load(target);
	    Code.loadConst(0);
	    Code.loadConst(0);
	    Code.put(Code.astore);

	        Obj add = Tab.find("add");
	        genAddAllElems(target, left, add);
	        Obj remove = Tab.find("remove");
	        Code.load(target);
	        
	        Code.load(right);
	        
	        Code.put(Code.call);
		    Code.put2(remove.getAdr() - Code.pc+1);
	        /// remove
	        return;
	    

   
	}
	
	

	
	public void visit(AssignRemInt n) {
	    Obj target = ((DesignatorStatements)((AssignDesignatorLabel)n.getParent()).getParent()).getDesignator().obj;
	    Obj left   = n.getDesignator().obj;
	
	  

	    //  target[0] = 0
	    Code.load(target);
	    Code.loadConst(0);
	    Code.loadConst(0);
	    Code.put(Code.astore);
	    
	    
	    
	    Code.load(left);
	    Code.put(Code.arraylength);
	    Code.put(Code.newarray);
	    Code.put(1);
	    Code.store(target);

	        // UNION (tvoj stari kod)
	        Obj add = Tab.find("add");
	        genAddAllElems(target, left, add);
	        // UNION (tvoj stari kod)
	        Obj remove = Tab.find("remove");
	        Code.load(target);
	        
	        Code.loadConst(n.getI3());
	        
	        Code.put(Code.call);
		    Code.put2(remove.getAdr() - Code.pc+1);
	        /// remove
	        /// remove
	        return;
	    

   
	}

	
		private void genDiffElems(Obj target, Obj left, Obj right, Obj add, Obj contains) {
			
		
//			    // i = 1
//			    Code.loadConst(1);                 // STACK: i
//			    int loop = Code.pc;
//
//			    // if (!(i <= left[0])) break;
//			    Code.put(Code.dup);                // i, i
//			    Code.load(left);                   // i, i, left
//			    Code.loadConst(0);                 // i, i, left, 0
//			    Code.put(Code.aload);              // i, i, sizeL
//			    Code.putFalseJump(Code.le, 0);     // jump kad i > sizeL
//			    int jEnd = Code.pc - 2;            // STACK: i
//
//			    // elem = left[i]
//			    Code.put(Code.dup);                // i, i
//			    Code.load(left);                   // i, i, left
//			    Code.put(Code.dup_x1); Code.put(Code.pop); // i, left, i
//			    Code.put(Code.aload);              // i, elem
//
//			    // res = contains(right, elem)
//			    Code.load(right);                  // i, elem, right
//			    Code.put(Code.dup_x1); Code.put(Code.pop); // i, right, elem
//			    Code.put(Code.call);
//			    Code.put2(contains.getAdr() - Code.pc + 1); // i, res
//
//			    // ako res != 0 -> SKIP (ne dodaj)
//			    Code.loadConst(0);                 // i, res, 0
//			    Code.putFalseJump(Code.eq, 0);     // skok kad !(res == 0) -> res != 0
//			    int jSkip = Code.pc - 2;           // STACK: i
//
//			    // add(target, left[i])  (ponovo dohvati elem jer je potrošen)
//			    Code.put(Code.dup);                // i, i
//			    Code.load(left);                   // i, i, left
//			    Code.put(Code.dup_x1); Code.put(Code.pop); // i, left, i
//			    Code.put(Code.aload);              // i, elem
//			    Code.load(target);                 // i, elem, target
//			    Code.put(Code.dup_x1); Code.put(Code.pop); // i, target, elem
//			    Code.put(Code.call);
//			    Code.put2(add.getAdr() - Code.pc + 1);     // i
//
//			    // skip:
//			    Code.fixup(jSkip);
//
//			    // i++
//			    Code.loadConst(1);
//			    Code.put(Code.add);                // i+1
//			    Code.putJump(loop);
//
//			    // kraj
//			    Code.fixup(jEnd);
//			    Code.put(Code.pop);                // skini i
			}


		private static void genInterElems(Obj target, Obj left, Obj right, Obj add, Obj contains) {
//		    // i = 1
//		    Code.loadConst(1);                 // STACK: i
//		    int loop = Code.pc;
//
//		    // if (!(i <= left[0])) break;
//		    Code.put(Code.dup);                // i, i
//		    Code.load(left);                   // i, i, left
//		    Code.loadConst(0);                 // i, i, left, 0
//		    Code.put(Code.aload);              // i, i, sizeL
//		    Code.putFalseJump(Code.le, 0);     // skok kad i > sizeL
//		    int jEnd = Code.pc - 2;            // STACK: i
//
//		    // elem = left[i]   (zadržavamo i na steku)
//		    Code.put(Code.dup);                // i, i
//		    Code.load(left);                   // i, i, left
//		    Code.put(Code.dup_x1); // i,left  i, left
//		    Code.put(Code.pop); // i, left, i
//		    Code.put(Code.aload);              // i, elem
//
//		    // call contains(right, elem) -> res
//		    Code.load(right);                  // i, elem, right
//		    Code.put(Code.dup_x1); Code.put(Code.pop); // i, right, elem
//		    Code.put(Code.call);
//		    Code.put2(contains.getAdr() - Code.pc + 1); // i, res
//
//		    // if (res == 0) skip add;
//		    Code.loadConst(0);                 // i, res, 0
//		    Code.putFalseJump(Code.ne, 0);     // skok kad res == 0
//		    int jSkip = Code.pc - 2;           // STACK: i
//
//		    // add(target, left[i])  (ponovo dohvatimo elem)
//		    Code.put(Code.dup);                // i, i
//		    Code.load(left);                   // i, i, left
//		    Code.put(Code.dup_x1); Code.put(Code.pop); // i, left, i
//		    Code.put(Code.aload);              // i, elem
//		    Code.load(target);                 // i, elem, target
//		    Code.put(Code.dup_x1); Code.put(Code.pop); // i, target, elem
//		    Code.put(Code.call);
//		    Code.put2(add.getAdr() - Code.pc + 1);     // i
//
//		    // skip:
//		    Code.fixup(jSkip);
//
//		    // i++
//		    Code.loadConst(1);
//		    Code.put(Code.add);                // i+1
//		    Code.putJump(loop);
//
//		    // kraj petlje
//		    Code.fixup(jEnd);
//		    Code.put(Code.pop);                // skini i
		}

		
		
		

	private void genAddAllElems(Obj target, Obj src, Obj addMeth) {

	    // i = 1
	    Code.loadConst(1);                 // STACK: i
	    int loop = Code.pc;                // if (i > src[0]) break;

	    Code.put(Code.dup);                // STACK: i, i

	    Code.load(src);                    // STACK: i, i, src

	    Code.loadConst(0);                 // STACK: i, i, src, 0

	    Code.put(Code.aload);              // STACK: i, i, n   (n = src[0])

	    Code.putFalseJump(Code.le, 0);       //i <= n; ako je FALSE (i > n),  na exit
	    
	    // STACK: i
	    int jExit = Code.pc - 2;

	    // add(target, elem)
	    Code.put(Code.dup);    
	    Code.load(target);                   // STACK: i,i, target

	    Code.put(Code.dup_x1);  // STACK: i,target, i, target
	    Code.put(Code.pop);  // STACK: i,target, i
	    Code.load(src);                     // STACK: i, target,i,  src

	    Code.put(Code.dup_x1);              // STACK: i, target, src, i, src
	    Code.put(Code.pop);    // STACK: i, target, src, i
	  
	
	    Code.put(Code.aload);                 // STACK: i, target, elem

	    // CALL add(target, elem)
	    Code.put(Code.call);
	    Code.put2(addMeth.getAdr() - Code.pc+1);  // STACK: i

	    // i++
	    Code.loadConst(1);                   // STACK: i, 1

	    Code.put(Code.add);                  // STACK: i+1

	    Code.putJump(loop);                

	   
	    Code.fixup(jExit);
	    Code.put(Code.pop);                
	   
	}


	
	public void visit(ReadStatementLabel readStmt) {
        if (readStmt.getDesignator().obj.getType() == Tab.intType || readStmt.getDesignator().obj.getType() == boolType) {
            Code.put(Code.read);
            Code.store(readStmt.getDesignator().obj);
            
        } else if(readStmt.getDesignator().obj.getType() == Tab.charType ) {
            Code.put(Code.bread);
            Code.store(readStmt.getDesignator().obj);
        }
        
        Obj des = readStmt.getDesignator().obj;
        Struct t = des.getType();

        if (t == setType) {
            // read(setVar): učitaj int i dodaj u set
            Obj add = Tab.find("add");   // potpis: add(set, int)

            Code.load(des);              // gurne referencu na set (radi i za S[i])
            Code.put(Code.read);         // pročita int sa ulaza
            Code.put(Code.call);
            Code.put2(add.getAdr() - Code.pc + 1);

            // nema store — add je void i sam menja set
            return;
        }
//        else if (readStmt.getDesignator().obj.getType().getKind() == Struct.Array) {
//        	
//           if(designator.getType().getElemType() == Tab.intType ||designator.getType().getElemType() == boolType) {
//	        Code.loadConst(0); 
//			int loop = Code.pc + 1;
//			Code.put(Code.dup); // i, i
//			Code.load(designator); // i, i , adr
//			Code.put(Code.arraylength); // i , i, n
//			
//			int pc = Code.pc + 1;
//			Code.putFalseJump(Code.ne, 0); // i // skace na kraj petlje
//			
//		    ////	if za tip
//			Code.put(Code.dup); // i , i
//			Code.put(Code.read) ;// i, i , val
//			Code.load(designator); // i, i ,val ,adr
//			Code.put(Code.dup_x2) ;
//			Code.put(Code.pop) ;// i, adr, i, val
//			Code.put(Code.astore); // i
//			Code.put(Code.const_1); // i, 1
//		
//			Code.put(Code.add); // i
//			Code.putJump(loop);
//
//			Code.fixup(pc);
//			Code.put(Code.pop) ;//
//    	   
//
//           }
//           else {    
//        	   
//        	 Code.loadConst(0); 
//			int loop = Code.pc + 1;
//			Code.put(Code.dup); // i, i
//			Code.load(designator); // i, i , adr
//			Code.put(Code.arraylength); // i , i, n
//			
//			int pc = Code.pc + 1;
//			Code.putFalseJump(Code.ne, 0); // i // skace na kraj petlje
//			
//		////	if za tip
//			Code.put(Code.dup); // i , i
//			Code.put(Code.bread) ;// i, i , val
//			Code.load(designator); // i, i ,val ,adr
//			Code.put(Code.dup_x2) ;
//			Code.put(Code.pop) ;// i, adr, i, val
//			Code.put(Code.astore); // i
//			Code.put(Code.const_1); // i, 1
//		
//			Code.put(Code.add); // i
//			Code.putJump(loop);
//
//			Code.fixup(pc);
//			Code.put(Code.pop) ;//
//   	   
//        	   
//           }
//           
//        }
        
      
    }

	public void visit(ReturnStatement retur) {
		returnFound = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(NoReturnStatement noreturn) {
		returnFound = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(MultipleExpr expr) {
		if (expr.getAddop() instanceof PlusLabel) {
			Code.put(Code.add);
		} else if (expr.getAddop() instanceof MinusLabel) {
			Code.put(Code.sub);

		}
		if (expr.getParent() instanceof ArrayAccessLabel) {

			Obj designator = ((ArrayAccessLabel) expr.getParent()).getDesignatorName().obj;
			Code.load(designator);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}

	}

	public void visit(MultipleTerms term) {
		if (term.getMulop() instanceof MultiplyLabel) {
			Code.put(Code.mul);
		} else if (term.getMulop() instanceof DivideLabel) {
			Code.put(Code.div);
		} else if (term.getMulop() instanceof ModuloLabel) {
			Code.put(Code.rem);
		}
	}

	public void visit(NegativeExpr expr) {
		Code.put(Code.neg);
	}

	public void visit(ExprInParensLabel expr) {

	}

	public void visit(ArrayAccessLabel arrayAccess) {
		// Code.load(arrayAccess.getDesignatorName().obj);
		if (arrayAccess.getParent() instanceof DesignatorStatement) {
//    		Code.load(arrayAccess.getDesignatorName().obj);
//    		Code.put(Code.dup_x1);
//    		Code.put(Code.pop);

		} else {
			if (arrayAccess.getDesignatorName().obj.getType().getElemType() == Tab.charType) {

				Code.put(Code.baload);
			} else {
				Code.put(Code.aload);
			}

		}

	}

	private int visitcount = 0;

	public void visit(SingleExpr sin) {

		if (sin.getParent() instanceof ArrayAccessLabel) {

			Obj designator = ((ArrayAccessLabel) sin.getParent()).getDesignatorName().obj;
			Code.load(designator);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}
//    	if(sin.getParent() instanceof MatrixArray) {
//    		visitcount++;
////    	    
//    		if (visitcount<=2) {
//    		Obj designator = (	(MatrixArray)sin.getParent()).getDesignatorName().obj;
//    		Code.load(designator);
//    		Code.put(Code.dup_x1);
//    		Code.put(Code.pop);
//    		Code.put(Code.aload);
//    
//    		
//    	}} NEMA 

	}

	public void visit(DesignatorName name) {
		if (name.getParent() instanceof MatrixArray) {
			Code.load(name.obj);
		}

		if (name.getParent() instanceof PrintMatrica) {
			designator = name.obj;

		}
		if (name.getParent().getParent() instanceof ReadStatementLabel) {
			designator = name.obj;

		}
	}

	public void visit(Swapniz niz) {

		// 1, 2 niz # 1, 2 niz[1] =niz[2] niz[2] niz[1]
		Code.put(Code.dup2); // 1 2 1 2
		Code.load(niz.getDesignatorName().obj); // 1 2 1 2 adr

		Code.put(Code.dup_x1); // 1 2 1 adr 2 adr
		Code.put(Code.pop);
		Code.put(Code.aload); // 1 2 1 niz2
		Code.put(Code.dup_x1);
		Code.put(Code.pop); // 1 2 niz2 1

		Code.load(niz.getDesignatorName().obj); // 1 2 niz2 1 adr
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);// 1 2 niz2 niz1 // adr indeks vrednost
		Code.put(Code.dup_x2); // 1 niz1 2 niz2 niz1
		Code.put(Code.pop); // 1 niz1 2 niz2
		Code.put(Code.dup_x2);
		Code.put(Code.pop);// 1 niz2 niz1 2
		Code.put(Code.dup_x1);
		Code.put(Code.pop); // 1 niz2 2 niz1
		Code.load(niz.getDesignatorName().obj);
		Code.put(Code.dup_x2); // 1 niz2 adr 2 niz1
		Code.put(Code.pop);
		Code.put(Code.astore); // 1 niz2
		Code.load(niz.getDesignatorName().obj); // 1 niz2 adr
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.astore);
	}

	public void visit(PrintMatrica matrica) {

		Code.put(Code.dup2);
		Code.load(designator);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);

		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.aload);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);

		Code.load(designator);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);

		Code.put(Code.add);

	}

	public void visit(DummyMatrix dummy) {

		Code.put(Code.aload);

	}

	public void visit(DummyMatrix2 dummy) {
		if (dummy.getParent().getParent() instanceof Factor) {

			MatrixArray parent = (MatrixArray) dummy.getParent();
			if (parent.getDesignatorName().obj.getType().getElemType() == Tab.charType) {
				Code.put(Code.baload);
			} else {
				Code.put(Code.aload);
			}

		}

	}
	
	public void visit(ElemInSetDesignator n) {
	    Obj contains = Tab.find("contains"); //

	    //  (set, elem)
	    Code.load(n.getDesignatorName().obj); // push set
	    Code.load(n.getDesignator().obj);     // push elem (int)

	    // call contains
	    Code.put(Code.call);
	    Code.put2(contains.getAdr() - Code.pc + 1);

	    // 0/1 (bool)
	}

	
	
	
	
	
//	
//	public void visit(ElemInSetLabel n) { //  Expr IN DesignatorName
//	    Obj contains = Tab.find("contains");
//
//	    
//	    Code.load(n.getDesignatorName().obj); // e, s
//	  
//	    Code.put(Code.dup_x1); // e, s -> s, e, s
//	    Code.put(Code.pop);    // s, e
//
//	    Code.put(Code.call);
//	    Code.put2(contains.getAdr() - Code.pc + 1);
//	}

	
	
	
	
	
	
// public void visit(PrintHash printHash) {
//	    // Učitaj promenljivu na stek
//	    Code.load(printHash.getDesignatorName().obj);  // a
//	    
//	    // Dupliraj vrednost pre nego što radiš modulo
//	    Code.put(Code.dup); // a a
//	    Code.put(Code.dup);
//	    // Uradi modulo 2 da proveriš parnost
//	    Code.loadConst(2); // a a 2
//	    Code.put(Code.rem); // a (ostatak)
//	    
//	    // Ako je rezultat 0, broj je paran, skok na kvadriranje
//	    int skipCubing = Code.pc + 1; // Adresa za skok ako je broj paran
//	    Code.putFalseJump(Code.eq, 0); // Skok ako nije 0 (tj. broj je neparan)
//
//	   
//	    // Broj je paran, množi samog sebe
//	    Code.put(Code.dup); // a a
//	    Code.put(Code.mul); // a^2
//
//	    // Skok preko dela za kubiranje
//	    int end = Code.pc + 1;
//	    Code.putJump(0);
//
//	    // Popuni adresu skoka za kubiranje
//	    Code.fixup(skipCubing);
//
//
//	    // Broj je neparan, množi tri puta (kub)
//	  
//	    Code.put(Code.dup); // a a
//	    Code.put(Code.dup); // a a a
//	    Code.put(Code.mul); // a*a
//	    Code.put(Code.mul); // a*a*a
//
//	    // Popuni adresu za kraj
//	    Code.fixup(end);
//	    
//	
//	}

// public void visit(PrintHash printHash) {
//	    // Učitaj promenljivu na stek
//	    Code.load(printHash.getDesignatorName().obj);  // a
//	    
//	    // Dupliraj vrednost pre nego što radiš modulo
//	    Code.put(Code.dup); // a a
//	    Code.put(Code.dup); // a a a
//	    // Uradi modulo 2 da proveriš parnost
//	    Code.loadConst(2); // a a a 2
//	    Code.put(Code.rem); // a a (ostatak) 0 ili 1 
//
//	    // Ako je rezultat 0, broj je paran, skok na kvadriranje
//	    int skipCubing = Code.pc + 1;
//	    Code.putFalseJump(Code.ne, 0); // Skok ako nije uslov zadovoljen false je uslov  0 (tj. broj je neparan)
//
//	    // Broj je paran, množi samog sebe
//	    Code.put(Code.dup); // a a
//	    Code.put(Code.mul); // a^2
//	   
//	    
//	    // Skok preko dela za kubiranje (paran slučaj)
//	    int end = Code.pc + 1;
//	    Code.putJump(0);
//
//	    // Popuni adresu skoka za kubiranje
//	    Code.fixup(skipCubing);
//
//	
//	    // Broj je neparan, množi tri puta (kub)
//	    Code.put(Code.dup); // a a
//	    Code.put(Code.dup); // a a a
//	    Code.put(Code.mul); // a*a
//	    Code.put(Code.mul); // a*a*a
//
//	    // Popuni adresu za kraj (kub)
//	    Code.fixup(end);
//
//	    
//	}
// 
	public void visit(PrintHash printHash) {
		// Učitaj promenljivu na stek
		Code.load(printHash.getDesignatorName().obj); // a

		// Dupliraj vrednost pre nego što radiš modulo
		Code.put(Code.dup); // a a

		// Uradi modulo 2 da proveriš parnost
		Code.loadConst(2); // a a 2

		Code.put(Code.rem); // a (ostatak) 0 ili 1
		Code.loadConst(0); // a ostatak 0
		// Ako je rezultat 0, broj je paran, ići će samo na kvadratiranje
		int skipCubing = Code.pc + 1; // adresa za skip kvadrata i prelaz na kube
		Code.putFalseJump(Code.eq, 0); // Ako je rezultat 1, skok na kubiranje ako nije propadi
		// false jump skace ako uslov nije zadovoljen

		// Ako je broj paran, uradi kvadratiranje
		Code.put(Code.dup); // a a
		Code.put(Code.mul); // a^2

		// Skok na kraj ako smo kvadrirali
		int end = Code.pc + 1;
		Code.putJump(0); // bezuslovno skoci na kraj

		Code.fixup(skipCubing);
		Code.put(Code.dup); // a a
		Code.put(Code.dup); // a a a
		Code.put(Code.mul); // a a*a (posle kvadratiranja)
		Code.put(Code.mul); // a*a*a (kubiranje)

		// Popuni adresu skoka za kraj
		Code.fixup(end);
	}

	Obj designator = null;

	public void visit(AdditionalDimLabel matrixAccess) {
		this.matix = true;

		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.newarray);
		Code.put(1);

		Code.put(Code.dup);
		Code.put(Code.dup);
		Code.store(designator);

		Code.put(Code.arraylength);

		int whilepc = Code.pc;

		Code.put(Code.const_1);
		// Code.put(1);
		Code.put(Code.sub);

		Code.put(Code.dup);
		Code.put(Code.const_1);
		Code.put(Code.const_1);
		Code.put(Code.sub);

		int fixuppc = Code.pc + 1;
		Code.putFalseJump(Code.ge, 0);

		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);

		NewArrayLabel na = findEnclosingNewArray(matrixAccess);
		int kind = (na != null) ? kindForNewArray(na) : 1;  // fallback 1

		Code.put(Code.newarray);
		Code.put(kind);   

		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);

		Code.put(Code.astore);

		Code.putJump(whilepc);

		Code.fixup(fixuppc);

		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
	}

	public void visit(AssignExprLabel dummy) {
		if (dummy.getParent() instanceof AssignDesignatorLabel) {
			designator = ((DesignatorStatements) ((AssignDesignatorLabel) dummy.getParent()).getParent())
					.getDesignator().obj;
		}
	}
	
	private NewArrayLabel findEnclosingNewArray(SyntaxNode n) {
	    SyntaxNode p = n;
	    while (p != null && !(p instanceof NewArrayLabel)) p = p.getParent();
	    return (NewArrayLabel)p; 
	}

	private int kindForNewArray(NewArrayLabel na) {
	    //  0=char, 1=int/obj
	    return (na.getType().struct == Tab.charType) ? 0 : 1;
	}

	public void visit(NoAdditionalDimLabel n) {
	    NewArrayLabel na = findEnclosingNewArray(n);
	    if (na == null) return; 

	    Struct ty = na.getType().struct;

	  
	    if (ty == setType) {
	        Code.loadConst(1);
	        Code.put(Code.add);
	    }

	    Code.put(Code.newarray);
	    Code.put(kindForNewArray(na));  // 0 za char, 1 za ostalo
	}

	public void visit(MethodCallExpr call) {
	    String methodName = call.getDesignatorName().getI1();

	    Obj methodObj = Tab.find(methodName);
	    if (methodObj != Tab.noObj && methodObj.getKind() == Obj.Meth) {
	    	Code.put(Code.call);
	    	Code.put2(methodObj.getAdr() - Code.pc+1 );


	    }
	      
	}

	public static void initStandardMethods() {
		  Tab.chrObj.setAdr(Code.pc);
		    Code.put(Code.enter);
		    Code.put(1); 
		    Code.put(1); 
		    Code.put(Code.load_n); 
		    Code.put(Code.exit);
		    Code.put(Code.return_);

		 
		    Tab.ordObj.setAdr(Code.pc);
		    Code.put(Code.enter);
		    Code.put(1);
		    Code.put(1);
		    Code.put(Code.load_n);
		    Code.put(Code.exit);
		    Code.put(Code.return_);

		  
		    Tab.lenObj.setAdr(Code.pc);
		    Code.put(Code.enter);
		    Code.put(1);
		    Code.put(1);
		    Code.put(Code.load_n);      
		    Code.put(Code.arraylength);
		    Code.put(Code.exit);
		    Code.put(Code.return_);
		
	}
	
	
	
	private static void generateRemoveAll() {
	    Obj removeAll = Tab.find("removeAll");
	    removeAll.setAdr(Code.pc);

	    // enter(#form=1, #locals=1) – kao i za ord/chr/len kod tebe
	    Code.put(Code.enter);
	    Code.put(1);
	    Code.put(1);

	    // s[0] = 0;
	    Code.put(Code.load_n);   // s
	    Code.loadConst(0);       // s, 0
	    Code.loadConst(0);       // s, 0, 0
	    Code.put(Code.astore);   // —

	    Code.put(Code.exit);
	    Code.put(Code.return_);
	}

	
	
	
	
//	
//	// if (size == 0) trap(2);
//	Code.put(Code.load_n);   // s
//	Code.loadConst(0);       // s, 0
//	Code.put(Code.aload);    // size = s[0]
//	Code.loadConst(0);       // size, 0
//	Code.putFalseJump(Code.eq, 0); // skok kad (size == 0) == false  => kad size != 0
//	int jOk = Code.pc - 2;          // posle skoka upamti operand adresu
//	Code.put(Code.trap); Code.put(2);  // trap kad je size == 0
//	Code.fixup(jOk);                // ovde stižu slučajevi size != 0
//	
//	
//	
//	
////	
//	
//	
//	
//	// if (!(1 <= i)) goto TRAP;
//	Code.loadConst(1);           // 1
//	Code.put(Code.load_n+1);     // 1, i
//	Code.putFalseJump(Code.le, 0);  // skok kad !(1 <= i) => i < 1
//	int jTrap1 = Code.pc - 2;
//
//	// if (!(i <= size)) goto TRAP;
//	Code.put(Code.load_n+1);     // i
//	Code.put(Code.load_n);       // i, s
//	Code.loadConst(0);           // i, s, 0
//	Code.put(Code.aload);        // i, size
//	Code.putFalseJump(Code.le, 0);   // skok kad !(i <= size) => i > size
//	int jTrap2 = Code.pc - 2;
//
//	// oba prošla => skipuj TRAP
//	int jEnd = Code.pc + 1;
//	Code.putJump(0);
//
//	// TRAP lokacija: dok je pc ovde, popuni skokove NA trap
//	Code.fixup(jTrap1);
//	Code.fixup(jTrap2);
//	Code.put(Code.trap); Code.put(3);
//
//	// kraj: popuni skip
//	Code.fixup(jEnd);

	
	
	
	
	
	
	
	//private static void genzieRemove() {
		
//	    Obj addMeth = Tab.find("remove");
//	    addMeth.setAdr(Code.pc);
//	   
//	    Code.put(Code.enter);
//	    Code.put(2); 
//	    Code.put(3);
//
//	   
//	    Code.put(Code.load_n+1);  // STACK: , e
//	    Code.put(Code.load_n);      // STACK:es
//	    Code.loadConst(0);          // STACK: es, 0
//	    Code.put(Code.aload);       // STACK: e size     ako je e > size skaci na kraj  manje jednako
//	    // Jmp ako !(size > e) --- ako size < e  → van opsega => return 
//	    Code.putFalseJump(Code.le, 0);  //da li je size vec od i jeste nastavi, nije skoci 
//	    
//	    int jover = Code.pc - 2;    // fixup na EXIT/RETURN dole
//	    Code.put(Code.load_n+1); //e   ako je e < 1 skaci  vec jed
//	    Code.loadConst(1); // E, 1        E < ili JEDako  1 SKACI  da li je e manje ili jednako od 1  le 
//	    Code.putFalseJump(Code.ge, 0); 
//	    int jexit = Code.pc - 2;    // manji od opsega izadjemo 
//	    
//	    Code.put(Code.load_n);      // STACK: s
//	    Code.loadConst(0);          // STACK: s, 0
//	    Code.put(Code.aload); // size
//	    Code.put(Code.load_n+1); //ako je jednako siye poslednji elem //siye, e 
//	    Code.putFalseJump(Code.ne, 0); 
//	    int notsize = Code.pc - 2;  
//	    
//	    /// ELEM U SEDINI FOR PETLJA 
//	
//	    Code.put(Code.load_n+1);//E
//	    Code.put(Code.store_n + 2); // I=E
//	    
// 
//	    int loop = Code.pc;
//	    
//	    Code.put(Code.load_n); //S 
//	    Code.loadConst(0);          // STACK: s, 0
//	    Code.put(Code.aload);
//	    Code.put(Code.load_n+2); //sizee,j 
//	    Code.putFalseJump(Code.ne, 0); 
//	    int kraj = Code.pc - 2;  
//	    
//
//	    Code.put(Code.load_n); //S 
//	    Code.put(Code.load_n+2);// S , J 
//	    
//	    Code.put(Code.load_n); //S , j, s
//	    Code.put(Code.load_n+2); //s, j, s, j
//	    Code.loadConst(1);// // s, j, s , j, 1
//	    Code.put(Code.add);// s, j, s, j+1
//	    Code.put(Code.aload); // s , j , s[j+11] 
//	    Code.put(Code.astore);  // smestili 
//	    
//	    Code.put(Code.load_n+2); //j
//	    Code.loadConst(1);//  j, 1
//	    Code.put(Code.add); //j+1 
//	    Code.put(Code.store_n + 2); 
//	    Code.putJump(loop); 
//	    
//	    Code.fixup(kraj);
//
//	    //KRAJ SMANJ VEL SKUPA
//	    
//	    Code.fixup(notsize);// za kraj 
//	    Code.put(Code.load_n);  // s
//	    Code.loadConst(0);  //s, 0
//	    Code.put(Code.load_n);  // s, 0, s
//	    Code.loadConst(0);  //s, 0,
//	    Code.put(Code.aload); //s, 0,  size 
//	    Code.loadConst(1); // s, 0 , size ,1 
//	    Code.put(Code.sub);    //s, 0, size-1 
//	    Code.put(Code.astore); // smanjili vel skupa za 1
//	     
//	         
//	    Code.fixup(jover);          
//	    Code.fixup(jexit);  
//	    Code.put(Code.exit);
//	    Code.put(Code.return_);
//	
//		
		
	//}
	
	
	
	
	
	
	
	
	
}
