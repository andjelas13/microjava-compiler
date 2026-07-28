// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class MoreDeclConst extends ConstDeclList {

    private ConstDeclList ConstDeclList;
    private SingleDecl SingleDecl;

    public MoreDeclConst (ConstDeclList ConstDeclList, SingleDecl SingleDecl) {
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
        this.SingleDecl=SingleDecl;
        if(SingleDecl!=null) SingleDecl.setParent(this);
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
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
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
        if(SingleDecl!=null) SingleDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
        if(SingleDecl!=null) SingleDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        if(SingleDecl!=null) SingleDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreDeclConst(\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleDecl!=null)
            buffer.append(SingleDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreDeclConst]");
        return buffer.toString();
    }
}
