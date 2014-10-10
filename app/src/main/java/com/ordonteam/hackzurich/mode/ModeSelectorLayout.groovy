package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.widget.LinearLayout
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorLayout extends LinearLayout {
    ModeSelectorLayout(Activity activity) {
        super(activity)
        setOrientation(LinearLayout.VERTICAL)
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1)
        CreateGameLayout createGameLayout = new CreateGameLayout(activity)
        createGameLayout.setLayoutParams(layoutParams)
        addView(createGameLayout)
        JoinGameLayout joinGameLayout = new JoinGameLayout(activity)
        joinGameLayout.setLayoutParams(layoutParams)
        addView(joinGameLayout)
    }
}
