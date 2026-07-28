// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class ScopedDesignatorLabel extends Designator {

    private ScopedIdentList ScopedIdentList;
    private String ident;
    private ArrayOpt ArrayOpt;

    public ScopedDesignatorLabel (ScopedIdentList ScopedIdentList, String ident, ArrayOpt ArrayOpt) {
        this.ScopedIdentList=ScopedIdentList;
        if(ScopedIdentList!=null) ScopedIdentList.setParent(this);
        this.ident=ident;
        this.ArrayOpt=ArrayOpt;
        if(ArrayOpt!=null) ArrayOpt.setParent(this);
    }

    public ScopedIdentList getScopedIdentList() {
        return ScopedIdentList;
    }

    public void setScopedIdentList(ScopedIdentList ScopedIdentList) {
        this.ScopedIdentList=ScopedIdentList;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident=ident;
    }

    public ArrayOpt getArrayOpt() {
        return ArrayOpt;
    }

    public void setArrayOpt(ArrayOpt ArrayOpt) {
        this.ArrayOpt=ArrayOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ScopedIdentList!=null) ScopedIdentList.accept(visitor);
        if(ArrayOpt!=null) ArrayOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ScopedIdentList!=null) ScopedIdentList.traverseTopDown(visitor);
        if(ArrayOpt!=null) ArrayOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ScopedIdentList!=null) ScopedIdentList.traverseBottomUp(visitor);
        if(ArrayOpt!=null) ArrayOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ScopedDesignatorLabel(\n");

        if(ScopedIdentList!=null)
            buffer.append(ScopedIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+ident);
        buffer.append("\n");

        if(ArrayOpt!=null)
            buffer.append(ArrayOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ScopedDesignatorLabel]");
        return buffer.toString();
    }
}
