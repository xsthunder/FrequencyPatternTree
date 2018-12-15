package club.hotpot;

import java.util.*;

class FrequencyPatternTree {
    private class IntegerSortByCounter implements Comparator<Integer>{
        @Override
        public int compare(Integer a, Integer b) {
            Integer tmp =  counter.get(b) - counter.get(a);
            if( tmp == 0)return a - b;
            else return tmp;
        }
    }
    private class ItemHead implements Comparable{
        HashSet<Tree> son;
        Integer x ;
        Integer weight;
        ItemHead(Integer x,HashSet<Tree> at){
            this.x = x;
            this.son = at;
            this.weight = counter.get(x);
        }
        @Override
        public int compareTo(Object o) {//small to big
            ItemHead ih = (ItemHead) o;
            Integer tmp = counter.get(this.x)- counter.get(ih.x) ;
            if( tmp == 0) return ih.x - this.x;
            else return tmp;
        }
    }
    private HashMap<Integer,Integer> counter = new HashMap<>();
    private HashMap<Integer,HashSet<Tree>> recorder= new HashMap<>();
    private ArrayList<ItemHead> L = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private Tree tree = new Tree(null, -1);
    private HashSet<Integer> alpha;
    private HashSet<Tree> leaf  = new HashSet<>();
    private void print(ArrayList<Integer> arr){
        ArrayList<Integer> tmp = new ArrayList<>();
        for( Integer x : arr){
            if( !alpha.contains(x))tmp.add(x);
        }
        for(long i = 1;i < ( 1 << tmp.size()) ; i++){
            int support = 0x7fffffff;
            for( Integer x : tmp)
                if( counter.get(x) < support )support = counter.get(x);
            System.out.print(String.format("%d supports, alpha %s, ", support, alpha.toString()));
            int k = 0;
            for(long j = 1; j <= i;j<<=1, k++ ){
                if( ( j  & i) > 0){
                    System.out.print(String.format("%d ",tmp.get(k)));
                }
            }
            System.out.println();
        }
    }
    private void print(Integer x){
        if(!alpha.isEmpty())System.out.print(String.format("%d support, alpha %s, %d\n", counter.get(x), alpha.toString() , x));
    }
    private void touchCounter(Integer x,int cnt){
        if(!counter.containsKey(x))counter.put(x,0);
        counter.put(x, counter.get(x) + cnt);
    }
    private void touchRecorder(Integer x,Tree t){//weight is in counter.get(x)
        if(!recorder.containsKey(x)) recorder.put(x, new HashSet<>());
        recorder.get(x).add(t);
    }
    private void buildTree(){
        for(Transaction transaction:this.transactions){
            Tree t = tree;
            for(Integer x : transaction.detail){
                t = t.addSon(x, transaction.weight);
                touchRecorder(x,t);
            }
            leaf.add(t);
        }
    }
    private void buildCounter(ArrayList<Transaction> transactions){
        for(Transaction transaction: transactions){
            for(Integer x: transaction.detail){
                touchCounter(x, transaction.weight);
            }
        }
    }
    private void buildTransaction(ArrayList<Transaction> transactions){//big to small
        for(Transaction transaction:transactions) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(Integer x:transaction.detail){
                if( counter.get(x) >= Util.threshold) tmp.add(x);
            }
            tmp.sort(new IntegerSortByCounter());
            this.transactions.add( new Transaction(tmp, transaction.weight)) ;
        }
    }
    private void buildL(){
        for(Map.Entry<Integer,HashSet<Tree>> entry : recorder.entrySet()){
            L.add(new ItemHead(entry.getKey(),entry.getValue()));
        }
        Collections.sort(L);
    }
    void run(){
        if( leaf.size() == 1){
            ArrayList<Integer>arr =  Util.collect(leaf.iterator().next());
            print(arr);
            return;
        }
        for(ItemHead itemHead: L){//small to big
            print(itemHead.x);
            ArrayList<Transaction> nextTransactions = new ArrayList<>();
            HashSet<Integer>nextAlpha= (HashSet<Integer>) alpha.clone();
            nextAlpha.add(itemHead.x);
            for( Tree t : itemHead.son){
                ArrayList<Integer> arr =  t.collectAncestor();
                nextTransactions.add(new Transaction(arr,t.weight));
            }
            FrequencyPatternTree fpt = new FrequencyPatternTree(nextTransactions,nextAlpha);
            fpt.run();
        }
    }
    FrequencyPatternTree(ArrayList<Transaction> transactions, HashSet<Integer> alpha){
        this.alpha = alpha;
        buildCounter(transactions);
        buildTransaction(transactions);
        buildTree();
        buildL();
    }
}
