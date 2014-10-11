package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.LinearLayout
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.game.GameActivity
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorLayout extends LinearLayout {
    ModeSelectorLayout(Activity activity) {
        super(activity)
        setOrientation(LinearLayout.VERTICAL)
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1)

        CreateGameSelectLayout createGameLayout = new CreateGameSelectLayout(activity)
        createGameLayout.setLayoutParams(layoutParams)
        addView(createGameLayout)

        JoinGameSelectLayout joinGameLayout = new JoinGameSelectLayout(activity)
        joinGameLayout.setLayoutParams(layoutParams)
        addView(joinGameLayout)
    }
}
