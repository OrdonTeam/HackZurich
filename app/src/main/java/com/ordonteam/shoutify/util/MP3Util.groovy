package com.ordonteam.shoutify.util

import android.app.Activity
import android.media.MediaPlayer
import com.ordonteam.hackzurich.R
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class MP3Util {
    static void playSword(Activity activity) {
        startThread {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(activity, R.raw.sword);
                mediaPlayer.start()
                Thread.sleep(1500)
            } catch (ex) {
                ex.printStackTrace()
            }
        }
    }

    static void play1(Activity activity) {
        startThread {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(activity, R.raw.sound1);
                mediaPlayer.start()
                Thread.sleep(1500)
            } catch (ex) {
                ex.printStackTrace()
            }
        }
    }

    static void play2(Activity activity) {
        startThread {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(activity, R.raw.sound2);
                mediaPlayer.start()
                Thread.sleep(1500)
            } catch (ex) {
                ex.printStackTrace()
            }
        }
    }

    static void play3(Activity activity) {
        startThread {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(activity, R.raw.sound3);
                mediaPlayer.start()
                Thread.sleep(1500)
            } catch (ex) {
                ex.printStackTrace()
            }
        }
    }
}
