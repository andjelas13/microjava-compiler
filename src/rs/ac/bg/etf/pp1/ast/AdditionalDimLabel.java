// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class AdditionalDimLabel extends BracketExprList {

    private Expr Expr;
    private BracketExprList BracketExprList;

    public AdditionalDimLabel (Expr Expr, BracketExprList BracketExprList) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.BracketExprList=BracketExprList;
        if(BracketExprList!=null) BracketExprList.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public BracketExprList getBracketExprList() {
        return BracketExprList;
    }

    public void setBracketExprList(BracketExprList BracketExprList) {
        this.BracketExprList=BracketExprList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(BracketExprList!=null) BracketExprList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(BracketExprList!=null) BracketExprList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(BracketExprList!=null) BracketExprList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AdditionalDimLabel(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BracketExprList!=null)
            buffer.append(BracketExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AdditionalDimLabel]");
        return buffer.toString();
    }
}
