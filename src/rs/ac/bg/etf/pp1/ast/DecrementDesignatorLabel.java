// generated with ast extension for cup
// version 0.8
// 15/8/2025 20:48:4


package rs.ac.bg.etf.pp1.ast;

public class DecrementDesignatorLabel extends DesignatorList {

    public DecrementDesignatorLabel () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DecrementDesignatorLabel(\n");

        buffer.append(tab);
        buffer.append(") [DecrementDesignatorLabel]");
        return buffer.toString();
    }
}
