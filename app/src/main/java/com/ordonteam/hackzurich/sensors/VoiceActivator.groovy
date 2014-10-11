package com.ordonteam.hackzurich.sensors

import android.media.MediaRecorder
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class VoiceActivator implements Runnable {
    private MediaRecorder mRecorder = null;
    ActiveAble activeAble
    volatile boolean stopped = false

    VoiceActivator(ActiveAble activeAble) {
        this.activeAble = activeAble
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile("/dev/null");
        mRecorder.prepare();
        mRecorder.start();
        startThread(this)
    }

    public void stop() {
        stopped = true;
    }

    private double getAmplitude() {
        if (mRecorder != null)
            return mRecorder.getMaxAmplitude();
        else
            return 0;

    }

    @Override
    void run() {
        while (!stopped) {
            if (getAmplitude() > 30000)
                activeAble.activate()
            Thread.sleep(300)
        }
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}
