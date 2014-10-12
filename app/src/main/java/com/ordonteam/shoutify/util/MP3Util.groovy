package com.ordonteam.shoutify.util
import android.app.Activity
import android.media.MediaPlayer
import com.ordonteam.hackzurich.R
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class MP3Util {
    static void playSword(Activity activity) {
        startThread{
            MediaPlayer mediaPlayer = MediaPlayer.create(activity, R.raw.sword);
            mediaPlayer.start()
            Thread.sleep(1500)
        }
    }
}
