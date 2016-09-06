/**
 * Created by Master on 04.09.2016.
 */
class Expression {
    protected Expression left;
    protected Expression right;
    protected String Opcode;
    protected Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getOpcode() {
        return Opcode;
    }

    public void setOpcode(String operand) {
        this.Opcode = operand;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
}
