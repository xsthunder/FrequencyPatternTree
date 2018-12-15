# FrequencyPatternTree

java implement for Frequency Pattern Tree

## Usage

download `fptree.jar` from release, java >= 10.0.1

```bash
java -jar fptree.jar <support_threshold> < inputfile # read from stdin
```

## input

each item should be Interger type

### pattern
```
<set1>
<set2>
...
<setn>
```

### example

```
1 2 5
2 4
2 3
1 2 4
1 3
2 3
1 3
1 2 3 5
1 2 3
8
9
10
```

## Output

### example

```bash
$ java -jar fptree.jar 2 <..\..\..\tinyInput    
2 supports, alpha [5], 1                        
2 supports, alpha [5], 2                        
2 supports, alpha [5], 1 2                      
2 supports, alpha [4], 2                        
4 support, alpha [3], 2                         
2 support, alpha [2, 3], 1                      
4 support, alpha [3], 1                         
4 support, alpha [1], 2     
```                    
