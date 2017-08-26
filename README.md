# Hadoop-Batch
Batch Framework using Hadoop

Build a batch framework on Hadoop, simplify batch job development on hadoop

1) Customize InputFileFormater to output <Key, Object>, Object will be the pojo class that represent a record, 
so that developer can use getter/setter methods to operate fields in mapper/reducer.

2) Standarize data format, example, needs pojo class name as the first field of VALUE so that mapper/reducer can identify the VALUE belongs to which pojo.

3) Standarized batch processing steps like join, match/unmatch(file 1 or file 2 only), enrich field, merge record.

4) Sort/Merge Utility base on Hadoop

5) Integrate Hadoop Batch Framework with Spring Batch to provide more flexibility of configuration.

