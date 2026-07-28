// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class AssignRemInt extends AssignTail {

    private Designator Designator;
    private Remop Remop;
    private Integer I3;

    public AssignRemInt (Designator Designator, Remop Remop, Integer I3) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Remop=Remop;
        if(Remop!=null) Remop.setParent(this);
        this.I3=I3;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Remop getRemop() {
        return Remop;
    }

    public void setRemop(Remop Remop) {
        this.Remop=Remop;
    }

    public Integer getI3() {
        return I3;
    }

    public void setI3(Integer I3) {
        this.I3=I3;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Remop!=null) Remop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Remop!=null) Remop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Remop!=null) Remop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignRemInt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Remop!=null)
            buffer.append(Remop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I3);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignRemInt]");
        return buffer.toString();
    }
}
