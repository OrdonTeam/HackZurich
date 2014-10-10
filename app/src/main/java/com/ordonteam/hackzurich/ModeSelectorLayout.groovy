package com.ordonteam.hackzurich

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorLayout extends LinearLayout {
    ModeSelectorLayout(Activity activity) {
        super(activity)
        setOrientation(LinearLayout.VERTICAL)
        addView(new CreateGameLayout(activity))
        addView(new JoinGameLayout(activity))
    }
}
