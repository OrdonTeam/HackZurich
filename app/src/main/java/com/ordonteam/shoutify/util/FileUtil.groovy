package com.ordonteam.shoutify.util

import android.content.Context
import groovy.transform.CompileStatic

@CompileStatic
class FileUtil {

    static String readNick(Context context) {
        File file = new File(context.getFilesDir(), 'nick');
        String nick
        if (file.exists()) {
            file.withReader { Reader it ->
                nick = it.readLine()
            }
        } else {
            file.createNewFile()
            nick = 'Player'
        }
        return nick
    }

    static void saveNick(Context context, String nick) {
        File file = new File(context.getFilesDir(), 'nick');
        if (!file.exists())
            file.createNewFile()

        file.withWriter { Writer it ->
            it.write(nick + '\n')
        }
    }
}
