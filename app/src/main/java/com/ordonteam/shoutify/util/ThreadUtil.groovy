package com.ordonteam.shoutify.util

import groovy.transform.CompileStatic

import static java.lang.Thread.sleep

@CompileStatic
class ThreadUtil {
    static Thread startThread(Runnable runnable){
        Thread thread = new Thread(runnable)
        thread.start()
        return thread
    }

    static void delay(int delay, Runnable runnable) {
        new Thread({
            sleep(delay)
            runnable.run()
        }).start()
    }
}
