// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class ProgramBody implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MoreNamespaceList MoreNamespaceList;
    private MoreDeclList MoreDeclList;
    private MoreMethodDeclList MoreMethodDeclList;

    public ProgramBody (MoreNamespaceList MoreNamespaceList, MoreDeclList MoreDeclList, MoreMethodDeclList MoreMethodDeclList) {
        this.MoreNamespaceList=MoreNamespaceList;
        if(MoreNamespaceList!=null) MoreNamespaceList.setParent(this);
        this.MoreDeclList=MoreDeclList;
        if(MoreDeclList!=null) MoreDeclList.setParent(this);
        this.MoreMethodDeclList=MoreMethodDeclList;
        if(MoreMethodDeclList!=null) MoreMethodDeclList.setParent(this);
    }

    public MoreNamespaceList getMoreNamespaceList() {
        return MoreNamespaceList;
    }

    public void setMoreNamespaceList(MoreNamespaceList MoreNamespaceList) {
        this.MoreNamespaceList=MoreNamespaceList;
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

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreNamespaceList!=null) MoreNamespaceList.accept(visitor);
        if(MoreDeclList!=null) MoreDeclList.accept(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreNamespaceList!=null) MoreNamespaceList.traverseTopDown(visitor);
        if(MoreDeclList!=null) MoreDeclList.traverseTopDown(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreNamespaceList!=null) MoreNamespaceList.traverseBottomUp(visitor);
        if(MoreDeclList!=null) MoreDeclList.traverseBottomUp(visitor);
        if(MoreMethodDeclList!=null) MoreMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramBody(\n");

        if(MoreNamespaceList!=null)
            buffer.append(MoreNamespaceList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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
        buffer.append(") [ProgramBody]");
        return buffer.toString();
    }
}
