// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class SingleDeclConst extends ConstDeclList {

    private SingleDecl SingleDecl;

    public SingleDeclConst (SingleDecl SingleDecl) {
        this.SingleDecl=SingleDecl;
        if(SingleDecl!=null) SingleDecl.setParent(this);
    }

    public SingleDecl getSingleDecl() {
        return SingleDecl;
    }

    public void setSingleDecl(SingleDecl SingleDecl) {
        this.SingleDecl=SingleDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleDecl!=null) SingleDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleDecl!=null) SingleDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleDecl!=null) SingleDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleDeclConst(\n");

        if(SingleDecl!=null)
            buffer.append(SingleDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleDeclConst]");
        return buffer.toString();
    }
}
