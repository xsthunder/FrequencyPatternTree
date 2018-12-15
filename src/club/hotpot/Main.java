package club.hotpot;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if(args.length!=1){
            System.err.println("one argument as threshold is needed");
            System.exit(1);
        }
        Util.threshold = Integer.parseInt(args[0]);
            List<String> result = new Scanner(System.in)
                    .useDelimiter("\n")
                    .tokens()
                    .collect(Collectors.toList());
            ArrayList<Transaction> transactions = new ArrayList<>();
            for(String line:result){
                List<String> items= new Scanner(line)
                        .useDelimiter(" ")
                        .tokens()
                        .collect(Collectors.toList());
                if( !items.isEmpty()){
                    ArrayList<Integer> tmp = new ArrayList<>();
                    for(String x : items){
                        tmp.add(Integer.parseInt(x));
                    }
                    transactions.add(new Transaction(tmp, 1));
                }
            }
            if(!transactions.isEmpty()){
                FrequencyPatternTree fpt = new FrequencyPatternTree(transactions, new HashSet<>());
                fpt.run();
            }
    }
}
