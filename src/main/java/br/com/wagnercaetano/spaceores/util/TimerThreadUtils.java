package br.com.wagnercaetano.spaceores.util;

import java.util.function.Consumer;

public class TimerThreadUtils {

    public static <T> void startTimeThenExecute(T parameter, int time, Consumer<T> consumer) {
        new Thread(() -> {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumer.accept(parameter);
        }).start();
    }
}
