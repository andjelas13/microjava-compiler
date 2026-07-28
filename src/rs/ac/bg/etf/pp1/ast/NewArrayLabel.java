// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class NewArrayLabel extends Factor {

    private Type Type;
    private Expr Expr;
    private BracketExprList BracketExprList;

    public NewArrayLabel (Type Type, Expr Expr, BracketExprList BracketExprList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.BracketExprList=BracketExprList;
        if(BracketExprList!=null) BracketExprList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(BracketExprList!=null) BracketExprList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(BracketExprList!=null) BracketExprList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(BracketExprList!=null) BracketExprList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewArrayLabel(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [NewArrayLabel]");
        return buffer.toString();
    }
}
