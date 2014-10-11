package com.ordonteam.shoutify.game.bars
import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import groovy.transform.CompileStatic

import static java.lang.Math.max
import static java.lang.Math.min

@CompileStatic
class ShoutifyProgressBar extends View {

    int progress = 0
    Paint paint
    Paint border
    Point size

    void setProgress(int progress) {
        this.progress = min(100,max(0,progress))
        postInvalidate()
    }

    void increaseProgress() {
        this.progress = min(100,max(0,progress+7))
        postInvalidate()
    }

    ShoutifyProgressBar(Context context) {
        super(context)
    }
}
