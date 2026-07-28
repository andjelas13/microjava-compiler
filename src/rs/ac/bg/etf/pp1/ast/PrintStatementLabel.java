// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class PrintStatementLabel extends Statement {

    private Expr Expr;
    private MorePrintOpt MorePrintOpt;

    public PrintStatementLabel (Expr Expr, MorePrintOpt MorePrintOpt) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MorePrintOpt=MorePrintOpt;
        if(MorePrintOpt!=null) MorePrintOpt.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public MorePrintOpt getMorePrintOpt() {
        return MorePrintOpt;
    }

    public void setMorePrintOpt(MorePrintOpt MorePrintOpt) {
        this.MorePrintOpt=MorePrintOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(MorePrintOpt!=null) MorePrintOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MorePrintOpt!=null) MorePrintOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MorePrintOpt!=null) MorePrintOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatementLabel(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MorePrintOpt!=null)
            buffer.append(MorePrintOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatementLabel]");
        return buffer.toString();
    }
}
