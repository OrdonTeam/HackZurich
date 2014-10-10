package com.ordonteam.hackzurich.util

import groovy.transform.CompileStatic

@CompileStatic
class ThreadUtil {
    static Thread startThread(Runnable runnable){
        Thread thread = new Thread(runnable)
        thread.start()
        return thread
    }
}
