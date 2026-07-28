// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class Namespace extends NamespaceList {

    private String I1;
    private MoreDeclList MoreDeclList;
    private MoreMethodDeclList MoreMethodDeclList;

    public Namespace (String I1, MoreDeclList MoreDeclList, MoreMethodDeclList MoreMethodDeclList) {
        this.I1=I1;
        this.MoreDeclList=MoreDeclList;
        if(MoreDeclList!=null) MoreDeclList.setParent(this);
        this.MoreMethodDeclList=MoreMethodDeclList;
        if(MoreMethodDeclList!=null) MoreMethodDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public MoreDeclList getMoreDeclList() {
        return MoreDeclList;
    }

    public void setMoreDeclList(MoreDeclList MoreDeclList) {
        this.MoreDeclList=MoreDeclList;
    }

    public MoreMethodDeclList getMoreMethodDeclList() {
        return MoreMethodDeclList;
    }

    public void setMoreMethodDeclList(MoreMethodDeclList MoreMethodDeclList) {
        this.MoreMethodDeclList=MoreMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreDeclList!=null) MoreDeclList.accept(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreDeclList!=null) MoreDeclList.traverseTopDown(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreDeclList!=null) MoreDeclList.traverseBottomUp(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Namespace(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(MoreDeclList!=null)
            buffer.append(MoreDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreMethodDeclList!=null)
            buffer.append(MoreMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Namespace]");
        return buffer.toString();
    }
}
