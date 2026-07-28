// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class VarItem implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String varName;
    private BracketList BracketList;

    public VarItem (String varName, BracketList BracketList) {
        this.varName=varName;
        this.BracketList=BracketList;
        if(BracketList!=null) BracketList.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public BracketList getBracketList() {
        return BracketList;
    }

    public void setBracketList(BracketList BracketList) {
        this.BracketList=BracketList;
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
        if(BracketList!=null) BracketList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BracketList!=null) BracketList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BracketList!=null) BracketList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarItem(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(BracketList!=null)
            buffer.append(BracketList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarItem]");
        return buffer.toString();
    }
}
