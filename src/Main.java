import com.sun.xml.internal.stream.StaxErrorReporter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Master on 04.09.2016.
 */
public class Main {
    public static void main(String[] argc){
        Parser p = new Parser();//(1+2)>=5+9and9*6>30 2+237*(345/4+3)/35>32and4<3
        Expression exp = p.generatePrimal(p.run("2+237*(345/4+3)/35>32and4<3"));
        System.out.println(exp);
        generateTreePicture(exp, 0);
        for(int i = 0; i<sb.size(); i++){
            String tab = " ";
            for(String s : sb.get(i).split(" ")){
                System.out.print(multiplyString(" ",(sb.size()*(sb.size()-i)/(2+1+i)))+s);

            }
            System.out.println();
        }
        System.out.println("Pause");
    }
    private static HashMap<Integer, String> sb = new HashMap<>();
    private static void generateTreePicture(Expression exp, Integer i) {
        String s = "";
        if (exp != null) {
            if (exp.getOpcode() != null) {
                s += (exp.getOpcode()) + " ";
                if (exp.getLeft() != null) {
                    generateTreePicture(exp.getLeft(), i+1);
                }else s+="\t";
                if (exp.getRight() != null) {
                    generateTreePicture(exp.getRight(), i+1);
                }else s = "\t" + s;

            }else if (exp.getValue() != null) {
                s += ((exp.getValue())) + " ";
                if(sb.containsKey(i))
                    sb.put(i, sb.get(i) + s);
                else sb.put(i,s);
                return;
            }
        } else return;
        if(sb.containsKey(i))
        sb.put(i, sb.get(i) + s);
        else sb.put(i,s);
        return;
    }

    private static String multiplyString(String s, int i){
        while(i>=0){
            s+=" ";
            i--;
        }
        return s;
    }
}

/*
"1+2*3+(1*6/2)"
* */
