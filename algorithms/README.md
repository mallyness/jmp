### Benchmark Results

Common setup:
```
# Warmup: 1 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Benchmark mode: Average time, time/op
```

| Iterative binary search                                                              | Recursive binary search                                                              |                                                                       
|--------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| ![iterative_binary_search.png](src%2Ftest%2Fresources%2Fiterative_binary_search.png) | ![recursive_binary_search.png](src%2Ftest%2Fresources%2Frecursive_binary_search.png) |
| [iterative_binary_search.txt](src%2Ftest%2Fresources%2Fiterative_binary_search.txt)  | [recursive_binary_search.txt](src%2Ftest%2Fresources%2Frecursive_binary_search.txt)  |

#### Conclusion

With regard to _**time**_ complexity, recursive and iterative methods both will give **O(log n)** time complexity, with regard
to input size.

Focusing on _**space**_ complexity, the iterative approach is more efficient since we are allocating a constant amount **O(1)** of
space for the function call and constant space for variable allocations, while the recursive approach takes **O(log n)**
space.
