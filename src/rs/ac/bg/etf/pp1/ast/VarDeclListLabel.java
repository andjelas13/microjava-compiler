// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListLabel extends MoreVarDeclList {

    private MoreVarDeclList MoreVarDeclList;
    private VarDecl VarDecl;

    public VarDeclListLabel (MoreVarDeclList MoreVarDeclList, VarDecl VarDecl) {
        this.MoreVarDeclList=MoreVarDeclList;
        if(MoreVarDeclList!=null) MoreVarDeclList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public MoreVarDeclList getMoreVarDeclList() {
        return MoreVarDeclList;
    }

    public void setMoreVarDeclList(MoreVarDeclList MoreVarDeclList) {
        this.MoreVarDeclList=MoreVarDeclList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreVarDeclList!=null) MoreVarDeclList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreVarDeclList!=null) MoreVarDeclList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclListLabel(\n");

        if(MoreVarDeclList!=null)
            buffer.append(MoreVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListLabel]");
        return buffer.toString();
    }
}
