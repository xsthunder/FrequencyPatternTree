package club.hotpot;

import java.util.ArrayList;

public class Transaction {
    ArrayList<Integer> detail;
    Integer weight;
    Transaction(ArrayList<Integer> detail, Integer weight){
        this.detail = detail; this.weight = weight;
    }
}
