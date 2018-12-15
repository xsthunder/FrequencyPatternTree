package club.hotpot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Util {
    public static Integer threshold;
    public static ArrayList<Integer> collect(Tree t){
        ArrayList<Integer> arr = new ArrayList<>();
        if( t == null )return arr;
        while( t.fa != null){
            arr.add(t.x);
            t = t.fa;
        }
        Collections.reverse(arr);
        return arr;
    }
}
