package com.ordonteam.hackzurich.game

import android.app.Activity
import android.widget.RelativeLayout
import com.ordonteam.hackzurich.game.bars.OpponentHealthBar
import com.ordonteam.hackzurich.game.bars.UserChargingProgressbar
import com.ordonteam.hackzurich.game.bars.UserHealthBar
import groovy.transform.CompileStatic

@CompileStatic
class GameLayout extends RelativeLayout {
    GameLayout(Activity activity) {
        super(activity)
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)

        UserHealthBar userHealthBar = new UserHealthBar(activity)
        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) userHealthBar.getLayoutParams()
        lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        userHealthBar.setLayoutParams(lp1)
        addView(userHealthBar)

        UserChargingProgressbar userChargingProgressbar = new UserChargingProgressbar(activity)
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) userChargingProgressbar.getLayoutParams()
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        userChargingProgressbar.setLayoutParams(lp2)
        addView(userChargingProgressbar)

        OpponentHealthBar opponentHealthBar = new OpponentHealthBar(activity)
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) opponentHealthBar.getLayoutParams()
        lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        opponentHealthBar.setLayoutParams(lp3)
        addView(opponentHealthBar)
    }
}
