// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class SingleDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String name;
    private ConstList ConstList;

    public SingleDecl (String name, ConstList ConstList) {
        this.name=name;
        this.ConstList=ConstList;
        if(ConstList!=null) ConstList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public ConstList getConstList() {
        return ConstList;
    }

    public void setConstList(ConstList ConstList) {
        this.ConstList=ConstList;
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
        if(ConstList!=null) ConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstList!=null) ConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstList!=null) ConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleDecl(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(ConstList!=null)
            buffer.append(ConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleDecl]");
        return buffer.toString();
    }
}
