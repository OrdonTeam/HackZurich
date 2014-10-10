package com.ordonteam.hackzurich

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import groovy.transform.CompileStatic

@CompileStatic
class ModelSelectorLayout extends LinearLayout {
    ModelSelectorLayout(Context context) {
        super(context)
        setOrientation(LinearLayout.VERTICAL)
        addView(new CreateGameLayout(context))
    }
}
