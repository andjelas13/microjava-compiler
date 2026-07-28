// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class MoreMethodDeclLists extends MoreMethodDeclList {

    private MoreMethodDeclList MoreMethodDeclList;
    private MethodDeclList MethodDeclList;

    public MoreMethodDeclLists (MoreMethodDeclList MoreMethodDeclList, MethodDeclList MethodDeclList) {
        this.MoreMethodDeclList=MoreMethodDeclList;
        if(MoreMethodDeclList!=null) MoreMethodDeclList.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public MoreMethodDeclList getMoreMethodDeclList() {
        return MoreMethodDeclList;
    }

    public void setMoreMethodDeclList(MoreMethodDeclList MoreMethodDeclList) {
        this.MoreMethodDeclList=MoreMethodDeclList;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreMethodDeclList!=null) MoreMethodDeclList.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreMethodDeclList!=null) MoreMethodDeclList.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreMethodDeclLists(\n");

        if(MoreMethodDeclList!=null)
            buffer.append(MoreMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreMethodDeclLists]");
        return buffer.toString();
    }
}
