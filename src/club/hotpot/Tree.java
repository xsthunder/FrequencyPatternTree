package club.hotpot;

import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
    Tree fa;
    Integer x;
    Integer weight;
    HashMap<Integer,Tree> son = new HashMap<>();
    Tree(Tree fa,Integer x){
        this.fa= fa;
        this.x = x;
        this.weight = 0;
    }
    Tree addSon(Integer x,Integer weight){
        if(!this.son.containsKey(x))son.put(x, new Tree(this, x));
        Tree son = this.son.get(x);
        son.weight = son.weight + weight;
        return this.son.get(x);
    }
    ArrayList<Integer> collectAncestor(){
        return Util.collect(this.fa);
    }
}
