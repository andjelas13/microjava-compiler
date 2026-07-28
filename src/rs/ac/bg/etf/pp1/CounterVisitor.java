package rs.ac.bg.etf.pp1;


import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.ConstDecls;
import rs.ac.bg.etf.pp1.ast.FormPar;
import rs.ac.bg.etf.pp1.ast.FormPars;

import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	protected int count;
	
	public int getCount(){
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
	
		public void visit(FormPar mDecl){
			count++;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarItem varItem){
			count++;
		}
	}
	
	
	public static class ConstCounter extends CounterVisitor{
		
		public void visit(ConstDecls constDecl){
			count++;
		}
	}
	
	
	
	
	
	

}
