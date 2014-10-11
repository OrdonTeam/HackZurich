package com.ordonteam.shoutify.game.bars
import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import groovy.transform.CompileStatic

@CompileStatic
class ShoutifyProgressBar extends View {

    int progress = 0
    Paint paint
    Paint border
    Point size

    void setProgress(int progress) {
        this.progress = progress
        postInvalidate()
    }

    void increaseProgress() {
        this.progress+=7
        postInvalidate()
    }

    ShoutifyProgressBar(Context context) {
        super(context)
    }
}
