package com.yxycoding.demo.thread;

import org.apache.commons.collections.list.FixedSizeList;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by yangxya on 2017.6.9.
 */
public class CompletableFutureTest {
    public static void main(String[] args) {

        List<Integer> lstI = new ArrayList<>();
        for(int i=0;i<100000;i++){
            lstI.add(i);
        }
//        parallelStreamMapTest();

        foreachTest(lstI);

//        forTest(lstI);
    }

    /**
     *
     */
    public static void parallelStreamMapTest(){
    List<Integer> fixedSizeList = FixedSizeList.decorate(Arrays.asList(new Integer[10000]));

    System.out.println("start:::::::::::::" + System.currentTimeMillis());
    List<CompletableFuture<String>> priceFuture = fixedSizeList.stream().map(item ->
            CompletableFuture.supplyAsync(() -> {
                System.out.println("sssssssssss================"+item);
                return "11";
            })).collect(Collectors.toList());
    System.out.println("end:::::::::::::::"+System.currentTimeMillis());
    System.out.println("44444444444444");
    priceFuture.parallelStream().map(CompletableFuture::join).collect(toList());
    System.out.println("5555555555555555555555");
}


    public static void foreachTest(List<Integer> lstI) {
//        List<Integer> fixedSizeList = FixedSizeList.decorate(Arrays.asList(new Integer[100000]));
        System.out.println("start:::::::::::::" + System.currentTimeMillis());
//        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService = new ThreadPoolExecutor(100000, 100000,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        lstI.stream().forEach(item ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("sssssssssss================" + item);
                    return "11";
                },executorService));
        //        System.out.println(new Date());
        executorService.shutdown();
        System.out.println("end:::::::::::::::" + System.currentTimeMillis());
    }

    public static void forTest(List<Integer> lstI) {
//        List<Integer> fixedSizeList = FixedSizeList.decorate(Arrays.asList(new Integer[100000]));
        System.out.println("start:::::::::::::" + System.currentTimeMillis());
//        ExecutorService executorService1 = Executors.newCachedThreadPool();
//        ExecutorService executorService = new ThreadPoolExecutor(0, 100000,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());

        List<Integer> retCutList = new ArrayList<>();
        for (Integer integer : lstI) {
            CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sssssssssss================" + integer);
                retCutList.add(integer);
                return "11";
            });
        }

//        executorService.shutdown();
        System.out.println("end:::::::::::::::" + System.currentTimeMillis());
    }

}
