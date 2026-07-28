// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class Types extends Type {

    private ScopedIdentList ScopedIdentList;
    private String typeName;

    public Types (ScopedIdentList ScopedIdentList, String typeName) {
        this.ScopedIdentList=ScopedIdentList;
        if(ScopedIdentList!=null) ScopedIdentList.setParent(this);
        this.typeName=typeName;
    }

    public ScopedIdentList getScopedIdentList() {
        return ScopedIdentList;
    }

    public void setScopedIdentList(ScopedIdentList ScopedIdentList) {
        this.ScopedIdentList=ScopedIdentList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName=typeName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ScopedIdentList!=null) ScopedIdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ScopedIdentList!=null) ScopedIdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ScopedIdentList!=null) ScopedIdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Types(\n");

        if(ScopedIdentList!=null)
            buffer.append(ScopedIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+typeName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Types]");
        return buffer.toString();
    }
}
