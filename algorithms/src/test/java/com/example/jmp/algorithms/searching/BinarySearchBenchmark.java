package com.example.jmp.algorithms.searching;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@Warmup(iterations = 1)
@Measurement(iterations = 5)
@Fork(3)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BinarySearchBenchmark {

    @Param({"100", "1000000", "1000000000"})
    private int size;
    private int[] sortedArray;

    @Setup
    public void setup() {
        sortedArray = new int[size];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = i;
        }
    }

    @Benchmark
    public void measureIterativeSearch() {
        BinarySearch.iterativeSearch(sortedArray, new Random().nextInt(0, sortedArray.length));
    }

    @Benchmark
    public void measureRecursiveSearch() {
        BinarySearch.recursiveSearch(sortedArray, 0, sortedArray.length - 1,
            new Random().nextInt(0, sortedArray.length));
    }
}