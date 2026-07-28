// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class AssignDesignatorLabel extends DesignatorList {

    private Assignop Assignop;
    private AssignTail AssignTail;

    public AssignDesignatorLabel (Assignop Assignop, AssignTail AssignTail) {
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.AssignTail=AssignTail;
        if(AssignTail!=null) AssignTail.setParent(this);
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public AssignTail getAssignTail() {
        return AssignTail;
    }

    public void setAssignTail(AssignTail AssignTail) {
        this.AssignTail=AssignTail;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Assignop!=null) Assignop.accept(visitor);
        if(AssignTail!=null) AssignTail.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(AssignTail!=null) AssignTail.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(AssignTail!=null) AssignTail.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignDesignatorLabel(\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignTail!=null)
            buffer.append(AssignTail.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignDesignatorLabel]");
        return buffer.toString();
    }
}
