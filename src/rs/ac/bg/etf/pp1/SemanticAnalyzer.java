package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	Struct currentType = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	Struct boolType = new Struct(Struct.Bool);
	boolean MatrixFound = false;
	boolean ArrayFound= false;
	boolean ArrayDesignator =false;
	boolean MatrixDesignator = false;
	
	Struct setType = new Struct(Struct.Enum, Tab.intType); 
	
	Logger log = Logger.getLogger(getClass());
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "set", setType));

		// add(set s, int e)
		Obj add = Tab.insert(Obj.Meth, "add", Tab.noType);
		Tab.openScope();
		Obj s = Tab.insert(Obj.Var, "s", setType);
		Obj e = Tab.insert(Obj.Var, "e", Tab.intType);
	
		Obj i = Tab.insert(Obj.Var, "i", Tab.intType);
		s.setFpPos(1);
		Tab.chainLocalSymbols(add);
		Tab.closeScope();
		

		
		// 
		Obj removeAll = Tab.insert(Obj.Meth, "removeAll", Tab.noType); // void
		Tab.openScope();
		Obj q= Tab.insert(Obj.Var, "s", setType);    
		q.setFpPos(1);                         // formalni parametar: set s                            // #formals = 1
		Tab.chainLocalSymbols(removeAll);
		Tab.closeScope();

		
		
		

		// addAll(set s, int[] arr)
		Obj addAll = Tab.insert(Obj.Meth, "addAll", Tab.noType);
		Tab.openScope();
		Obj s2 = Tab.insert(Obj.Var, "s", setType);
		Obj arr = Tab.insert(Obj.Var, "arr", new Struct(Struct.Array, Tab.intType));
		
		Obj i2 = Tab.insert(Obj.Var, "i", Tab.intType);
		s2.setFpPos(1);
		arr.setFpPos(2);
		Tab.chainLocalSymbols(addAll);
		Tab.closeScope();
		
		

		// remove(set s, int e)
		Obj remove = Tab.insert(Obj.Meth, "remove", Tab.noType);
		Tab.openScope();
		Obj s3 = Tab.insert(Obj.Var, "s", setType);
		Obj e3 = Tab.insert(Obj.Var, "e", Tab.intType);
	
		Obj i3 = Tab.insert(Obj.Var, "i", Tab.intType);
		s3.setFpPos(1);
		Tab.chainLocalSymbols(remove);
		Tab.closeScope();
		
		
		
		// contains(set s, int e) -> int
		Obj contains = Tab.insert(Obj.Meth, "contains", Tab.intType);
		Tab.openScope();
		Obj cs = Tab.insert(Obj.Var, "s", setType); cs.setFpPos(1);
		Obj ce = Tab.insert(Obj.Var, "e", Tab.intType); ce.setFpPos(2);
		Obj ci = Tab.insert(Obj.Var, "i", Tab.intType);
		Tab.chainLocalSymbols(contains);
		Tab.closeScope();
		
		
		// int min(set S)
		Obj fMin = Tab.insert(Obj.Meth, "min", Tab.intType);
		Tab.openScope();
		Obj j = Tab.insert(Obj.Var, "s", setType); j.setFpPos(1);
		Obj f = Tab.insert(Obj.Var, "e", Tab.intType); 
		Obj w = Tab.insert(Obj.Var, "i", Tab.intType);
		Tab.chainLocalSymbols(fMin);
		Tab.closeScope();
		
		// int max(set S)
		Obj fMax = Tab.insert(Obj.Meth, "max", Tab.intType);
		Tab.openScope();
		Obj p = Tab.insert(Obj.Var, "s", setType); p.setFpPos(1);
		Obj g = Tab.insert(Obj.Var, "e", Tab.intType); 
		Obj h = Tab.insert(Obj.Var, "i", Tab.intType);
		Tab.chainLocalSymbols(fMax);
		Tab.closeScope();

		
		
		
		
		
		
		

	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	

	
	
	public void visit(PrintMatrica matrica) {
		
		
		   Obj designatorNameObj = matrica.getDesignatorName().obj;

		      
	        if (designatorNameObj.getType().getKind() != Struct.Array) {
	            report_error("Promenljiva " + designatorNameObj.getName() + " nije matrica!", matrica);
	            return;  
	        }

	     
	        if (designatorNameObj.getType().getElemType().getKind() != Struct.Array) {
	            report_error("Promenljiva " + designatorNameObj.getName() + " nije matrica!", matrica);
	            return; 
	        }
		
	        matrica.struct = designatorNameObj.getType().getElemType().getElemType();
		
		
	}
	
	public void visit(Swapniz niz) {

		 Obj designatorNameObj = niz.getDesignatorName().obj; 
		   
	    if (designatorNameObj.getType().getKind() != Struct.Array) {
            report_error("Promenljiva " + designatorNameObj.getName() + " nije matrica!", niz);
            return;  
        }
	    

        if (niz.getExpr().struct != Tab.intType) {
            report_error("Izraz mora biti tipa int!", niz);
            return;
        }
 

        if (niz.getExpr1().struct != Tab.intType) {
            report_error("Izraz mora biti tipa int!", niz);
            return;
        }
 

	}
	
	
	
    public void visit(PrintStatementLabel print) {
    	Struct exprType = print.getExpr().struct;

    	if (!(exprType == Tab.intType 
    	          || exprType == Tab.charType 
    	          || exprType == this.boolType 
    	          || exprType == setType)) {
    	        report_error("Izraz u 'print' naredbi mora biti tipa int, char, bool ili set!", print);
    	    }
		printCallCount++;
	}
    
    
    public void visit(ProgName progName){
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program){
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	
    	Tab.closeScope();
    }
    
 
    
    public void visit(NumConst numconst) {
        if (Tab.find(((SingleDecl)(numconst.getParent())).getName()) != Tab.noObj ) {
            report_error("Greska: postoji već ime u tabeli simbola " + ((SingleDecl)(numconst.getParent())).getName(), numconst);
            return;
        }
        if (Tab.intType.equals(currentType)) {
            report_info("Definisana INT konstanta " + ((SingleDecl)(numconst.getParent())).getName(), numconst);
            Obj intNode = Tab.insert(Obj.Con, ((SingleDecl)(numconst.getParent())).getName(), Tab.intType);
            intNode.setAdr(numconst.getI1());
            report_info("Konstanta '" + intNode.getName() + "' je dodata sa tipom " + intNode.getType().getKind(), numconst);
        } else {
            report_error("Greska: Tip konstante " + ((SingleDecl)(numconst.getParent())).getName() 
                + " nije kompatibilan sa vrednošću konstante!", numconst);
        }
    }

    
    public void visit(CharConst charconst) {
    	
    	if(Tab.find(((SingleDecl)(charconst.getParent())).getName())!= Tab.noObj ) {
    		report_error("Greska postoji vec ime u tabeli simbola "+ ((SingleDecl)(charconst.getParent())).getName(), charconst);
    	    return;
    	}
    	if(Tab.charType.equals(currentType)) {
    		report_info("Definisana CHAR konstatna "+((SingleDecl)(charconst.getParent())).getName(),charconst );
    		Obj intNode = Tab.insert(Obj.Con,((SingleDecl)(charconst.getParent())).getName(), Tab.charType);
			intNode.setAdr(charconst.getC1());
    		
    	}else {
    		report_error("Greska: Tip konstante " +((SingleDecl)(charconst.getParent())).getName() 
    				+ " nije kompatibilan sa vrednoscu konstante!", charconst);
    		
    	}
    	
    	
    	
    }
    
 public void visit(BoolConst boolconst) {
    	
    	if(Tab.find(((SingleDecl)(boolconst.getParent())).getName())!= Tab.noObj ) {
    		report_error("Greska postoji vec ime u tabeli simbola "+ ((SingleDecl)(boolconst.getParent())).getName(), boolconst);
    	    return;
    	}
    	if(this.boolType.equals(currentType)) {
    		report_info("Definisana BOOL konstatna "+((SingleDecl)(boolconst.getParent())).getName(),boolconst );
    		int boolValue;
			if (boolconst.getB1() == true) {
				boolValue = 1;
			} else {
				boolValue = 0;
			}
			Obj boolNode = Tab.insert(Obj.Con, ((SingleDecl)(boolconst.getParent())).getName(), boolType);
			boolNode.setAdr(boolValue);    		
    	}else {
    		report_error("Greska: Tip konstante " +((SingleDecl)(boolconst.getParent())).getName() 
    				+ " nije kompatibilan sa vrednoscu konstante!", boolconst);
    		
    	}  	
    	
    }
    
 public void visit(VarItem varItem) {
	    varDeclCount++; 
	    report_info("Deklarisana promenljiva " + varItem.getVarName(), varItem);

	    if (Tab.currentScope.findSymbol(varItem.getVarName()) != null) {
	        report_error("Greska: Ime " + varItem.getVarName() + " vec postoji u tabeli simbola u tom opsegu!", varItem);
	    } else {
	      
	        if (this.currentType == setType) {
	            // Skup ne može biti niz 
	            if (this.ArrayFound || this.MatrixFound) {
	                report_error("Skupovi ne mogu biti nizovi ili matrice!", varItem);
	            } else {
	                Tab.insert(Obj.Var, varItem.getVarName(), setType);
	            }
	        }
	        else if (this.MatrixFound) {
	            // niz nizova
	            Struct arrayType = new Struct(Struct.Array, this.currentType);
	            Struct matrix = new Struct(Struct.Array, arrayType);
	            Tab.insert(Obj.Var, varItem.getVarName(), matrix);
	        }
	        else if (this.ArrayFound) {
	            //  niz
	            Struct arrayType = new Struct(Struct.Array, this.currentType);
	            Tab.insert(Obj.Var, varItem.getVarName(), arrayType);
	        }
	        else {
	            //  promenljiva
	            Tab.insert(Obj.Var, varItem.getVarName(), this.currentType);
	        }
	    }

	    this.ArrayFound = false;
	    this.MatrixFound = false;
	}
 
        
	
	public void visit(SingleBracketLabel array) {
		this.ArrayFound=true;	
		
	}
    
	public void visit (MatrixBracketLabel matrix) {
		this.MatrixFound= true;
	}
	

	public void visit(VoidReturnLabel methodDecl) {

	   
	    String imeMetode = ((MethodName) ((MethodSignature) methodDecl.getParent()).getMethodName()).getI1();

	    if (Tab.currentScope.findSymbol(imeMetode) != null) {
	        report_error("Greska: Ime metode " + imeMetode + " je već deklarisano!", methodDecl);
	        return;
	    }

	    if ("main".equals(imeMetode)) {

	        if (!(((MethodSignature) methodDecl.getParent()).getReturnType() instanceof VoidReturnLabel)) {
	            report_error("Greska: Main metoda mora biti void tipa!", methodDecl);
	            return;
	        }

	        if (!(((MethodSignature) methodDecl.getParent()).getMoreFormParsList() instanceof NoMoreFormPars)) {
	            report_error("Greska: Main metoda ne sme imati argumente!", methodDecl);
	            return;
	        }

	
	        this.currentMethod = Tab.insert(Obj.Meth, "main", Tab.noType);
	        ((MethodSignature) methodDecl.getParent()).obj = this.currentMethod;
	        Tab.openScope();

	    } else {
	        report_error("Greska: Samo main metoda je dozvoljena!", methodDecl);

	        this.currentMethod = Tab.insert(Obj.Meth, imeMetode, Tab.noType);
	        ((MethodSignature) methodDecl.getParent()).obj = this.currentMethod;
	        Tab.openScope();
	    }
	}

	
	public void visit(PrintHash printHash) {
	   
	    if (printHash.getDesignatorName().obj.getType() != Tab.intType) {
	        report_error("Operator ## se može koristiti samo nad celobrojnim vrednostima!", printHash);
	        printHash.struct = Tab.noType;  
	    } else {
	        printHash.struct = Tab.intType;  // Ako je int, nastavljamo
	    }
	}


	public void visit(MethodDeclList methodDecl ) {
		
        Tab.chainLocalSymbols(this.currentMethod);
        Tab.closeScope();
        this.currentMethod=null;
	}
	
	
	
	public void visit(NoReturnStatement noreturn) {
		
		if(this.currentMethod == null) {
			
			report_error("Greska return izraz ne moze da se nadje van funkcije", noreturn);
				
			
		}
		
		if(this.currentMethod.getType() != Tab.noType) {
			report_error("Funkcija main ne moze da ima povratan tip", noreturn);
		}
		
		
	}
	
	public void visit(ReturnStatement retur) {
		report_error("Funkcija ne sme da ima vraca vrednost ", retur);
		
	}

	public void visit(Types type) {
	    Obj typeNode = Tab.find(type.getTypeName());
	    if (typeNode == Tab.noObj) {
	        report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", type);
	        type.struct = Tab.noType;
	    } else {
	        if (Obj.Type == typeNode.getKind()) {
	            type.struct = typeNode.getType();

	            // Ako je tip set
	            if ("set".equals(type.getTypeName())) {
	                currentType = setType; 
	            } else {
	                currentType = type.struct;
	            }

	        } else {
	            report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
	            type.struct = Tab.noType;
	        }
	    }
	}

	public void visit(SingleType type) {
	    Obj typeNode = Tab.find(type.getTypeName());
	    if (typeNode == Tab.noObj) {
	        report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", type);
	        type.struct = Tab.noType;
	    } else {
	        if (Obj.Type == typeNode.getKind()) {
	            type.struct = typeNode.getType();

	            // Ako je tip set
	            if ("set".equals(type.getTypeName())) {
	                currentType = setType;
	            } else {
	                currentType = type.struct;
	            }

	        } else {
	            report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
	            type.struct = Tab.noType;
	        }
	    }
	}

	public void visit(DesignatorStatements designatorStatement) {
	    Obj designatorObj = designatorStatement.getDesignator().obj;

	    if (designatorObj == null) {
	        report_error("Designator nije pronađen ili nije ispravno inicijalizovan!", designatorStatement);
	        return;
	    }

	    // Designator mora biti promenljiva ili element niza
	    if (designatorObj.getKind() != Obj.Var && 
	    	    designatorObj.getKind() != Obj.Elem && 
	    	    designatorObj.getKind() != Obj.Meth) {
	    	    report_error("Designator mora označavati promenljivu ili element niza!", designatorStatement);
	    	}

	    if (designatorStatement.getDesignatorList() instanceof AssignDesignatorLabel) {
	        AssignDesignatorLabel assignLabel = 
	            (AssignDesignatorLabel) designatorStatement.getDesignatorList();

	        // dodela 
	        if (assignLabel.getAssignTail() instanceof AssignExprLabel) {
	            AssignExprLabel exprLabel = (AssignExprLabel) assignLabel.getAssignTail();
	            Expr expr = exprLabel.getExpr();

	            if (!expr.struct.assignableTo(designatorObj.getType())) {
	                report_error("Tip izraza mora biti kompatibilan pri dodeli sa tipom designatora!", designatorStatement);
	            }
	        }
	        //union
	        else if (assignLabel.getAssignTail() instanceof AssignSetopLabel) {
	            AssignSetopLabel setopLabel = (AssignSetopLabel) assignLabel.getAssignTail();

	            // Target skup je levi designator iz DesignatorStatements
	            Obj targetSet = designatorStatement.getDesignator().obj;

	            // Prvi operand levi skup u uniji
	            Obj left = setopLabel.getDesignator().obj;

	            // Drugi operand desni skup u uniji
	            Obj right = setopLabel.getDesignator1().obj;

	         
	            if (targetSet.getType() != setType || 
	                left.getType() != setType || 
	                right.getType() != setType) {
	                report_error("Svi skupovi u uniji moraju biti tipa 'set'!", designatorStatement);
	            }

	        }
	        else if (assignLabel.getAssignTail() instanceof AssignRemD) {
	        	AssignRemD setopLabel = (AssignRemD) assignLabel.getAssignTail();

	            // Target skup je levi designator iz DesignatorStatements
	            Obj targetSet = designatorStatement.getDesignator().obj;

	            // Prvi operand levi skup u uniji
	            Obj left = setopLabel.getDesignator().obj;

	            // Drugi operand desni skup u uniji
	            Obj right = setopLabel.getDesignator1().obj;

	         
	            if (targetSet.getType() != setType || 
	                left.getType() != setType 
	               ) {
	                report_error("Svi skupovi u uniji moraju biti tipa 'set'!", designatorStatement);
	            }

	            
	        } else if (assignLabel.getAssignTail() instanceof AssignRemInt) {
	        	AssignRemInt setopLabel = (AssignRemInt) assignLabel.getAssignTail();

	            // Target skup je levi designator iz DesignatorStatements
	            Obj targetSet = designatorStatement.getDesignator().obj;

	            // Prvi operand levi skup u uniji
	            Obj left = setopLabel.getDesignator().obj;

	            // Drugi operand desni skup u uniji
//	            Obj right = setopLabel.getDesignator1().obj;

	         
	            if (targetSet.getType() != setType || 
	                left.getType() != setType
	              ) {
	                report_error("Svi skupovi u uniji moraju biti tipa 'set'!", designatorStatement);
	            }

	        }
	    
	    }
	    

	    else if (designatorStatement.getDesignatorList() instanceof IncrementDesignatorLabel ||
	             designatorStatement.getDesignatorList() instanceof DecrementDesignatorLabel) {

	        if (designatorObj.getType() != Tab.intType) {
	            report_error("Designator mora biti tipa int za inkrement/dekrement operaciju!", designatorStatement);
	        }
	    }
	}
	
	
	public void visit(ElemInSetDesignator n) {
	    // levo: int
	    if (n.getDesignator().obj.getType() != Tab.intType) {
	        report_error("Leva strana 'IN' mora biti int.", n);
	    }
	    // desno: set
	    if (n.getDesignatorName().obj.getType() != setType) {
	        report_error("Desna strana 'IN' mora biti set.", n);
	    }
	    n.struct = boolType; // rezultat je bool
	}



	
    public void visit(DesignatorName designator) {
    	
    	Obj DesignatorNode = Tab.find(designator.getI1());
    	
    	if(DesignatorNode == Tab.noObj) {
    		report_error("Greska promenljiva nije deklarisana", designator);
    	}
    	designator.obj = DesignatorNode;
 

    }

    

    
    public void visit(DesignatorNames designatorNames) {
        Obj designatorObj = Tab.find(designatorNames.getDesignatorName().getI1());

        if (designatorObj == Tab.noObj) {
            report_error("Greska: Promenljiva '" + designatorNames.getDesignatorName().getI1() + "' nije deklarisana!", designatorNames);
        } else {
            designatorNames.obj = designatorObj;
        }
    }

  
    
    public void visit(ReadStatementLabel readStatement) {
        Obj designatorObj = readStatement.getDesignator().obj;
        if (designatorObj == Tab.noObj) {
            report_error("Designator nije pronađen u tabeli simbola!", readStatement);
            return;
        }

        if (!(designatorObj.getKind() == Obj.Var || designatorObj.getKind() == Obj.Elem)) {
            report_error("Designator u 'read' naredbi mora označavati promenljivu ili element niza/matrice!", readStatement);
            return;
        }

        // Zabrana da se ceo set prosledi u read ________________
        if (designatorObj.getType().equals(setType)) {
//            report_error("Skup ne može biti argument read naredbe!", readStatement);
//            return;
        }
        
//        Struct t = designatorObj.getType();
//     //  niza 
//        if (designatorObj.getKind() == Obj.Var && t.getKind() == Struct.Array) {
//            Struct elem = t.getElemType();
//
//           
//            if (elem != Tab.intType && elem != Tab.charType) {
//                report_error("read(niz): dozvoljeni su samo nizovi int[] ili char[]!", readStatement);
//            }   
//            return; 
//        }
//        //  seta 
//        if (designatorObj.getKind() == Obj.Var && t == setType) {
//            return; 
//        }
    
        Struct designatorType = designatorObj.getType();
//
//        if (this.ArrayDesignator) {
//            designatorType = designatorType.getElemType();
//            this.ArrayDesignator = false;
//        } else if (this.MatrixDesignator) {
//            designatorType = designatorType.getElemType().getElemType();
//            this.MatrixDesignator = false;
//        }

        if (!(designatorType == Tab.intType || designatorType == Tab.charType || designatorType == this.boolType || designatorType == this.setType)) {
            report_error("Designator u 'read' naredbi mora biti tipa int, char ili bool!", readStatement);
        }
    }

       
     
    
    public void visit(MatrixArray arrayOpt) {
        this.MatrixDesignator = true;

       
        Obj designatorNameObj = arrayOpt.getDesignatorName().obj;

      
        if (designatorNameObj.getType().getKind() != Struct.Array) {
            report_error("Promenljiva " + designatorNameObj.getName() + " nije niz!", arrayOpt);
            return;  
        }

     
        if (designatorNameObj.getType().getElemType().getKind() != Struct.Array) {
            report_error("Promenljiva " + designatorNameObj.getName() + " nije matrica!", arrayOpt);
            return; 
        }

      
        Struct firstExprType = arrayOpt.getExpr().struct;
        Struct secondExprType = arrayOpt.getExpr1().struct;

        if (firstExprType.getKind() != Struct.Int || secondExprType.getKind() != Struct.Int) {
            report_error("Oba izraza moraju biti tipa int!", arrayOpt);
            return; 
        }

      
        Struct elementType = designatorNameObj.getType().getElemType().getElemType();


//        arrayOpt.getExpr1().struct = elementType;  // Ovaj struct označava tip elementa matrice

       
        arrayOpt.obj = new Obj(Obj.Elem, designatorNameObj.getName(), elementType);
    }



    public void visit(ArrayAccessLabel arrayOpt) {
        this.ArrayDesignator = true;

        Obj designatorNameObj = arrayOpt.getDesignatorName().obj;

   
        if (designatorNameObj.getType().getKind() != Struct.Array) {
            report_error("Promenljiva " + designatorNameObj.getName() + " nije niz!", arrayOpt);
            return;
        }
        if (designatorNameObj.getType() == setType) {
            report_error("Skup ne može da se indeksira!", arrayOpt);
            return;
        }
   
        if (arrayOpt.getExpr().struct != Tab.intType) {
            report_error("Izraz mora biti tipa int!", arrayOpt);
            return;
        }
 
       
        Struct elementType = designatorNameObj.getType().getElemType();

    
        arrayOpt.obj = new Obj(Obj.Elem, designatorNameObj.getName(), elementType);
    }

    public void visit(NewArrayLabel newArray) {
        Struct type = newArray.getType().struct;

    
        if (type == Tab.noType) {
            newArray.struct = Tab.noType;
            return;
        }

        boolean isSet = type.equals(setType); 

        Struct firstExprType = newArray.getExpr().struct;
        if (firstExprType != Tab.intType) {
            report_error("Prvi izraz unutar [] mora biti tipa int.", newArray);
        }

  
        if (newArray.getBracketExprList() instanceof AdditionalDimLabel) {
            if (isSet) {
                report_error("Skupovi ne mogu biti nizovi ili matrice!", newArray);
                newArray.struct = Tab.noType;
                return;
            }

 
            Struct secondExprType = ((AdditionalDimLabel) newArray.getBracketExprList()).getExpr().struct;
            if (secondExprType != Tab.intType) {
                report_error("Drugi izraz unutar [] mora biti tipa int.", newArray);
            }

            if (firstExprType == Tab.intType && secondExprType == Tab.intType) {
                newArray.struct = new Struct(Struct.Array, new Struct(Struct.Array, type));
            } else {
                newArray.struct = Tab.noType;
            }
        } 

        else {
            if (firstExprType == Tab.intType) {
                if (isSet) {
                    // SET
                    newArray.struct = setType;
                } else {

                    newArray.struct = new Struct(Struct.Array, type);
                }
            } else {
                newArray.struct = Tab.noType;
            }
        }
    }

    public void visit(MinSetOp n) {
        if (n.getDesignatorName().obj.getType() != setType)
            report_error("Operator #< radi samo nad skupom.", n);
        n.struct = Tab.intType;
    }
    public void visit(MaxSetOp n) {
        if (n.getDesignatorName().obj.getType() != setType)
            report_error("Operator #> radi samo nad skupom.", n);
        n.struct = Tab.intType;
    }

    
    public void visit(MultipleTerms term) {
        Struct leftOperandType = term.getTerm().struct;
        Struct rightOperandType = term.getFactor().struct;
        Mulop mulop = term.getMulop(); 

      
        if (leftOperandType != Tab.intType || rightOperandType != Tab.intType) {
            report_error("Operator " + mulop + " može se koristiti samo sa operandima tipa int.", term);
            term.struct = Tab.noType; 
        } else {
            term.struct = Tab.intType; 
        }
    }

    public void visit(MultipleExpr expr) {

        Struct leftOperandType = expr.getExpr().struct;  
        Struct rightOperandType = expr.getTerm().struct; 

     
        if (leftOperandType != Tab.intType || rightOperandType != Tab.intType) {
            report_error("Operator može se koristiti samo sa operandima tipa int.", expr);
            expr.struct = Tab.noType; 
        } else {
            expr.struct = Tab.intType; 
        }
    }  
    
    public void visit(SingleTerms singleTerm) {
       
        singleTerm.struct = singleTerm.getFactor().struct;
    }

      
    public void visit(SingleExpr singleExpr) {
       
        singleExpr.struct = singleExpr.getTerm().struct;
    }
 
    
    public void visit(NegativeExpr negativeExpr) {
      
        if (negativeExpr.getTerm().struct != Tab.intType) {
            report_error("Negativni izraz mora biti tipa int.", negativeExpr);
            negativeExpr.struct = Tab.noType;
        } else {
            negativeExpr.struct = Tab.intType;
        }
    }
    
 
    public void visit(DesignatorLabel factor) {
        factor.struct = factor.getDesignator().obj.getType();
        report_info("Designator: " + factor.getDesignator().obj.getName() + ", Tip: " + factor.struct.getKind(), factor);
    }
 
    public void visit(NumConstLabel factor) {
        factor.struct = Tab.intType; 
    }

  
    public void visit(CharConstLabel factor) {
        factor.struct = Tab.charType; 
    }


    public void visit(BoolConstLabel factor) {
        factor.struct = this.boolType; 
    }


    public void visit(ExprInParensLabel factor) {
        factor.struct = factor.getExpr().struct; 
    }
 
    
    public boolean passed(){
    	return !errorDetected;
    }
    
    @Override
    public void visit(MethodCallExpr methodCall) {
        Obj methodObj = Tab.find(methodCall.getDesignatorName().getI1());

        if (methodObj == Tab.noObj || methodObj.getKind() != Obj.Meth) {
            report_error("Metoda " + methodCall.getDesignatorName().getI1() + " nije definisana!", methodCall);
            methodCall.struct = Tab.noType;
            return;
        }

        String methodName = methodObj.getName();
        List<Struct> argumentTypes = new ArrayList<>();

        ActParsOpt actParsOpt = methodCall.getActParsOpt();
        if (actParsOpt instanceof ActParsPresent) {
            ActPars actPars = ((ActParsPresent) actParsOpt).getActPars();
            while (actPars instanceof MultipleActParsLabel) {
                argumentTypes.add(((MultipleActParsLabel) actPars).getExpr().struct);
                actPars = ((MultipleActParsLabel) actPars).getActPars();
            }
            if (actPars instanceof SingleActParLabel) {
                argumentTypes.add(((SingleActParLabel) actPars).getExpr().struct);
            }
        }

        // povratni tip
        switch (methodName) {
            case "ord":
                methodCall.struct = (argumentTypes.size() == 1 && argumentTypes.get(0) == Tab.charType) 
                                    ? Tab.intType : Tab.noType;
                break;

            case "chr":
                methodCall.struct = (argumentTypes.size() == 1 && argumentTypes.get(0) == Tab.intType) 
                                    ? Tab.charType : Tab.noType;
                break;

            case "len":
                methodCall.struct = (argumentTypes.size() == 1 && argumentTypes.get(0).getKind() == Struct.Array)
                                    ? Tab.intType : Tab.noType;
                break;

            default:

                methodCall.struct = methodObj.getType();
                break;
        }
    }

    public void visit(MethodCallDesignatorLabel methodCall) {
        // Designator iz roditelja DesignatorStatements
        DesignatorStatements parent = (DesignatorStatements) methodCall.getParent();
        Obj methodObj = parent.getDesignator().obj;

        if (methodObj.getKind() != Obj.Meth) {
            report_error("Designator ne označava metodu!", methodCall);
            return;
        }

        String methodName = methodObj.getName();

        // lista tipova prosleđenih argumenata
        List<Struct> argumentTypes = new ArrayList<>();
        ActParsOpt actParsOpt = methodCall.getActParsOpt();

        if (actParsOpt instanceof ActParsPresent) {
            ActPars actPars = ((ActParsPresent) actParsOpt).getActPars();
            // idemo unazad kroz listu ActPars jer je MultipleActParsLabel rekurzivno
            while (actPars instanceof MultipleActParsLabel) {
            	argumentTypes.add(0, ((MultipleActParsLabel) actPars).getExpr().struct);
                actPars = ((MultipleActParsLabel) actPars).getActPars();
            }
            if (actPars instanceof SingleActParLabel) {
            	argumentTypes.add(0, ((SingleActParLabel) actPars).getExpr().struct);
            }
        }

        switch (methodName) {
        case "add":
        	
            if (argumentTypes.size() != 2 ||
                
                !(argumentTypes.get(0) == setType ) ||
   
                !(argumentTypes.get(1) == Tab.intType || argumentTypes.get(1).getKind() == Struct.Int)) {

                report_error("Metoda add mora imati parametre (set, int)", methodCall);
            }
            break;
          
            case "addAll":
                if (argumentTypes.size() != 2 ||
                    !(argumentTypes.get(0) == setType ) ||
                    argumentTypes.get(1).getKind() != Struct.Array ||
                    argumentTypes.get(1).getElemType() != Tab.intType) {

                    report_error("Metoda addAll mora imati parametre (set, int[])", methodCall);
                }
                break;
                
                
            case "remove":
            	
                if (argumentTypes.size() != 2 ||
                    
                    !(argumentTypes.get(0) == setType ) ||
       
                    !(argumentTypes.get(1) == Tab.intType || argumentTypes.get(1).getKind() == Struct.Int)) {

                    report_error("Metoda rem mora imati parametre (set, int)", methodCall);
                }
                break;
                
                
         case "contains":
            	
                if (argumentTypes.size() != 2 ||
                    
                    !(argumentTypes.get(0) == setType ) ||
       
                    !(argumentTypes.get(1) == Tab.intType || argumentTypes.get(1).getKind() == Struct.Int)) {

                    report_error("Metoda con mora imati parametre (set, int)", methodCall);
                }
                break;
         case "fMin":
        	  if (argumentTypes.size() != 2 ||
              
              !(argumentTypes.get(0) == setType ) ||
 
              !(argumentTypes.get(1) == Tab.intType || argumentTypes.get(1).getKind() == Struct.Int)) {

                 report_error("Metoda min mora imati jedan parametar tipa set", methodCall);
             }
             break;
         case "fMax":
        	  if (argumentTypes.size() != 2 ||
              
              !(argumentTypes.get(0) == setType ) ||
 
              !(argumentTypes.get(1) == Tab.intType || argumentTypes.get(1).getKind() == Struct.Int)) {

                 report_error("Metoda max mora imati jedan parametar tipa set", methodCall);
             }
             break;


         case "removeAll":
             if (argumentTypes.size() != 1 ||
                 argumentTypes.get(0) != setType) {
                 report_error("Metoda ord mora imati jedan parametar tipa set", methodCall);
             }
             break;

            case "ord":
                if (argumentTypes.size() != 1 ||
                    argumentTypes.get(0) != Tab.charType) {
                    report_error("Metoda ord mora imati jedan parametar tipa char", methodCall);
                }
                break;

            case "chr":
                if (argumentTypes.size() != 1 ||
                    argumentTypes.get(0) != Tab.intType) {
                    report_error("Metoda chr mora imati jedan parametar tipa int", methodCall);
                }
                break;

            case "len":
                if (argumentTypes.size() != 1 ||
                    argumentTypes.get(0).getKind() != Struct.Array) {
                    report_error("Metoda len mora imati jedan parametar tipa niz", methodCall);
                }
                break;

            default:
              
                break;
        }
    }

    
    
}

















































//Obj designatorNameObj = matrica.getDesignatorName().obj;
//int numberOfRows = designatorNameObj.getType().getNumberOfElements(); // Broj redova
//int numberOfCols = designatorNameObj.getType().getElemType().getNumberOfElements(); // Broj kolona
//
//// Provera da li je expr1 unutar dozvoljenih granica redova
//Code.loadConst(numberOfRows); // Učitavamo broj redova
//Code.put(Code.dup2); // Kopiramo expr1 sa steka
//Code.putFalseJump(Code.lt, Code.pc + 4); // Ako je expr1 >= numberOfRows, idi na kraj provere
//Code.put(Code.trap); // Ako ne prođe provera, izbaci runtime grešku
//
//// Provera da li je expr2 unutar dozvoljenih granica kolona
//Code.loadConst(numberOfCols); // Učitavamo broj kolona
//Code.put(Code.dup_x1); // Kopiramo expr2 sa steka
//Code.putFalseJump(Code.lt, Code.pc + 4); // Ako je expr2 >= numberOfCols, idi na kraj provere
//Code.put(Code.trap); // Ako ne prođe provera, izbaci runtime grešku
