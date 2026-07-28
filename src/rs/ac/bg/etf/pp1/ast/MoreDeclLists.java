// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class MoreDeclLists extends MoreDeclList {

    private MoreDeclList MoreDeclList;
    private DeclList DeclList;

    public MoreDeclLists (MoreDeclList MoreDeclList, DeclList DeclList) {
        this.MoreDeclList=MoreDeclList;
        if(MoreDeclList!=null) MoreDeclList.setParent(this);
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
    }

    public MoreDeclList getMoreDeclList() {
        return MoreDeclList;
    }

    public void setMoreDeclList(MoreDeclList MoreDeclList) {
        this.MoreDeclList=MoreDeclList;
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreDeclList!=null) MoreDeclList.accept(visitor);
        if(DeclList!=null) DeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreDeclList!=null) MoreDeclList.traverseTopDown(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreDeclList!=null) MoreDeclList.traverseBottomUp(visitor);
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreDeclLists(\n");

        if(MoreDeclList!=null)
            buffer.append(MoreDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreDeclLists]");
        return buffer.toString();
    }
}
