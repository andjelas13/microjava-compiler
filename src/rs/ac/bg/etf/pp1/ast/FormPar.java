// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class FormPar implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String I2;
    private FormBracketList FormBracketList;

    public FormPar (Type Type, String I2, FormBracketList FormBracketList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.FormBracketList=FormBracketList;
        if(FormBracketList!=null) FormBracketList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public FormBracketList getFormBracketList() {
        return FormBracketList;
    }

    public void setFormBracketList(FormBracketList FormBracketList) {
        this.FormBracketList=FormBracketList;
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
        if(Type!=null) Type.accept(visitor);
        if(FormBracketList!=null) FormBracketList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormBracketList!=null) FormBracketList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormBracketList!=null) FormBracketList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPar(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(FormBracketList!=null)
            buffer.append(FormBracketList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPar]");
        return buffer.toString();
    }
}
