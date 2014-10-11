package com.ordonteam.hackzurich.game.bars

import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import groovy.transform.CompileStatic

@CompileStatic
class ShoutifyProgressBar extends View {
    Point size
    Point position
    int progress
    Paint paint

    void setProgress(int progress) {
        this.progress = progress
        postInvalidate()
    }

    ShoutifyProgressBar(Context context) {
        super(context)
    }


}
