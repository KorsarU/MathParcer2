import java.lang.*;
import java.lang.Integer;
import java.util.*;

/**
 * Created by Master on 04.09.2016.
 */
public class Parser {
    String input;
    Stack<String> operand;
    Stack<String> operations;
    String standartOperations = "orxorand<><=>===/=+-*/";
    String complexOperand = "orxorand<><=>===/=";
    String leftP = "(";
    String rightParenthesized = ")";
    public Parser() {
        operand = new Stack<>();
        operations = new Stack<>();
    }

    public Stack<String> run(String input){
        this.input = input;
        String buffer = "";
        char[] c = input.toCharArray();
        for(int i = 0; i < c.length; i++){
            if(c[i]==' ' || (c[i]+"").contains(" ") || (int)(c[i])==32)
                continue;
            if(leftP.contains(c[i]+"")){
                operations.push(c[i]+"");
                continue;
            }
            boolean digitInBuf = false;
            while(i<c.length && Character.isDigit(c[i])) {
                buffer += "" + c[i];
                i++;
                digitInBuf = true;
            }
            if(digitInBuf){
                digitInBuf = false;
                operand.push(buffer);
                buffer = "";
            }
            if(i<c.length && c[i]==' ')continue;
            while (i<c.length && !Character.isDigit(c[i]) && !isParenthesized(c[i])){
                buffer+=c[i];
                i++;
           }

            if(buffer != "" && standartOperations.contains(buffer)){//""+c
                while(!operations.isEmpty() && getPriority(operations.peek())>=getPriority(buffer)){//""+c
                    operand.push(operations.pop());
                }
                operations.push(buffer);//""+c
                buffer = "";
                i--;
                continue;
            }
            if(i==c.length)break;
            if(rightParenthesized.contains(c[i]+"")){//""+c
                while(!operations.isEmpty()){
                    buffer = operations.pop();
                    if(buffer.contains("("))
                        break;
                    operand.push(buffer);

                }
                buffer = "";
                continue;
            }
        }
        if(buffer!="")operand.push(buffer);
        buffer = "";
        while(!operations.isEmpty())operand.push(operations.pop());

        System.out.println("RPN - " + operand.toString());
        //Primary primary = generatePrimal(operand);
        return operand;
    }

    private boolean isParenthesized(char c) {
        return leftP.contains(c+"")||rightParenthesized.contains(c+"");
    }

    private boolean isComplexOperand(String buffer) {
        if(buffer.contains("or")||buffer.contains("xor")||buffer.contains("and")||
                buffer.contains("<")||buffer.contains(">")||buffer.contains("==")||
                buffer.contains("<=")||buffer.contains(">=")||buffer.contains("/=")) return true;
        else return false;
    }

    private static Deque<Primary> result = new ArrayDeque<>();
    Primary generatePrimal(Stack<String> rpn){

        while(!rpn.isEmpty()){
            try{
            if(standartOperations.contains(rpn.peek())){
                result.push(new Primary(rpn.pop()));
                if(standartOperations.contains(rpn.peek())) {
                    result.peek().setRight(generatePrimal(rpn));
                }else
                    result.peek().setRight(/*new Primary*/(new MyInteger(Integer.parseInt(rpn.pop()))));
                if(standartOperations.contains(rpn.peek())) {
                    result.peek().setLeft(generatePrimal(rpn));
                }else
                    result.peek().setLeft(/*new Primary*/(new MyInteger(Integer.parseInt(rpn.pop()))));
                return result.pop();
            }else{
                return /*new Primary*/(new MyInteger(Integer.parseInt(rpn.pop())));
            }
            }catch (EmptyStackException e){return null;}
        }
        return null;
    }

    private int getPriority(String operation){
        switch(operation){
            case "+":
                return 2;
            case "-":
                return 2;
            case "*":
                return 3;
            case "/":
                return 3;
            case "or":
            case "xor":
            case "and":
                return 0;
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "==":
            case "/=":
                return 1;
            default:
                return -1;
        }
    }

}
