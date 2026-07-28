// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class MethodCallExpr extends Factor {

    private DesignatorName DesignatorName;
    private ActParsOpt ActParsOpt;

    public MethodCallExpr (DesignatorName DesignatorName, ActParsOpt ActParsOpt) {
        this.DesignatorName=DesignatorName;
        if(DesignatorName!=null) DesignatorName.setParent(this);
        this.ActParsOpt=ActParsOpt;
        if(ActParsOpt!=null) ActParsOpt.setParent(this);
    }

    public DesignatorName getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(DesignatorName DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public ActParsOpt getActParsOpt() {
        return ActParsOpt;
    }

    public void setActParsOpt(ActParsOpt ActParsOpt) {
        this.ActParsOpt=ActParsOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.accept(visitor);
        if(ActParsOpt!=null) ActParsOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorName!=null) DesignatorName.traverseTopDown(visitor);
        if(ActParsOpt!=null) ActParsOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.traverseBottomUp(visitor);
        if(ActParsOpt!=null) ActParsOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodCallExpr(\n");

        if(DesignatorName!=null)
            buffer.append(DesignatorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsOpt!=null)
            buffer.append(ActParsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodCallExpr]");
        return buffer.toString();
    }
}
