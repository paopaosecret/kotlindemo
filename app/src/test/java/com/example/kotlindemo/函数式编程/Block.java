package com.example.kotlindemo.函数式编程;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Block {

    public static void main(String[] args) {
        String result = fetchDataWithCallback();
        System.out.println("Result: " + result);
    }

    public static String fetchDataWithCallback() {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] result = new String[1];

        fetchData(new Callback() {
            @Override
            public void onSuccess(String data) {
                result[0] = data;
                latch.countDown();
            }

            @Override
            public void onError(Exception e) {
                result[0] = "Error: " + e.getMessage();
                latch.countDown();
            }
        });

        try {
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    public static void fetchData(Callback callback) {
        // 模拟异步操作，如网络请求
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                callback.onSuccess("Hello, World!");
            } catch (InterruptedException e) {
                callback.onError(e);
            }
        }).start();
    }

    interface Callback {
        void onSuccess(String data);
        void onError(Exception e);
    }
}