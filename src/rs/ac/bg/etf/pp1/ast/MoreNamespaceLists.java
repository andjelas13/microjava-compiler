// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class MoreNamespaceLists extends MoreNamespaceList {

    private MoreNamespaceList MoreNamespaceList;
    private NamespaceList NamespaceList;

    public MoreNamespaceLists (MoreNamespaceList MoreNamespaceList, NamespaceList NamespaceList) {
        this.MoreNamespaceList=MoreNamespaceList;
        if(MoreNamespaceList!=null) MoreNamespaceList.setParent(this);
        this.NamespaceList=NamespaceList;
        if(NamespaceList!=null) NamespaceList.setParent(this);
    }

    public MoreNamespaceList getMoreNamespaceList() {
        return MoreNamespaceList;
    }

    public void setMoreNamespaceList(MoreNamespaceList MoreNamespaceList) {
        this.MoreNamespaceList=MoreNamespaceList;
    }

    public NamespaceList getNamespaceList() {
        return NamespaceList;
    }

    public void setNamespaceList(NamespaceList NamespaceList) {
        this.NamespaceList=NamespaceList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreNamespaceList!=null) MoreNamespaceList.accept(visitor);
        if(NamespaceList!=null) NamespaceList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreNamespaceList!=null) MoreNamespaceList.traverseTopDown(visitor);
        if(NamespaceList!=null) NamespaceList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreNamespaceList!=null) MoreNamespaceList.traverseBottomUp(visitor);
        if(NamespaceList!=null) NamespaceList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreNamespaceLists(\n");

        if(MoreNamespaceList!=null)
            buffer.append(MoreNamespaceList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NamespaceList!=null)
            buffer.append(NamespaceList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreNamespaceLists]");
        return buffer.toString();
    }
}
