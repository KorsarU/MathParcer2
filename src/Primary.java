/**
 * Created by Master on 04.09.2016.
 */
public class Primary extends Expression {
    Primary left;
    Primary right;
    String opcode;
    Integer value;

    public Primary(){}
    public Primary(String opcode){this.opcode=opcode;}
    public Primary(Integer value){this.value = value;}
    public Primary(MyInteger left, MyInteger right, String opcode) {
        this.left = left;
        this.right = right;
        this.opcode = opcode;
    }

    public Primary getLeft() {
        return left;
    }

    public void setLeft(Primary left) {
        this.left = left;
    }

    public Primary getRight() {
        return right;
    }

    public void setRight(Primary right) {
        this.right = right;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
