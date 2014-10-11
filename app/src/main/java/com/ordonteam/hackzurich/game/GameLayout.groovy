package com.ordonteam.hackzurich.game

import android.app.Activity
import android.widget.RelativeLayout
import com.ordonteam.hackzurich.game.bars.OpponentHealthBar
import com.ordonteam.hackzurich.game.bars.UserChargingProgressbar
import com.ordonteam.hackzurich.game.bars.UserHealthBar
import groovy.transform.CompileStatic

@CompileStatic
class GameLayout extends RelativeLayout {

    int i=100
    UserHealthBar userHealthBar
    UserChargingProgressbar userChargingProgressbar
    OpponentHealthBar opponentHealthBar

    GameLayout(Activity activity) {
        super(activity)

        userHealthBar = new UserHealthBar(activity)
        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) userHealthBar.getLayoutParams()
        lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        userHealthBar.setLayoutParams(lp1)
        addView(userHealthBar)

        userChargingProgressbar = new UserChargingProgressbar(activity)
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) userChargingProgressbar.getLayoutParams()
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        lp2.addRule(RelativeLayout.CENTER_HORIZONTAL)
        userChargingProgressbar.setLayoutParams(lp2)
        addView(userChargingProgressbar)

        opponentHealthBar = new OpponentHealthBar(activity)
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) opponentHealthBar.getLayoutParams()
        lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        opponentHealthBar.setLayoutParams(lp3)
        addView(opponentHealthBar)
    }

    void charge() {
        userChargingProgressbar.increaseProgress()
    }

    void resetChargeProgress() {
        userChargingProgressbar.setProgress(0)
    }

    void updateHealthStatus(int myStatus,int opponentStatus) {
        opponentHealthBar.setProgress(opponentStatus)
        userHealthBar.setProgress(myStatus)
    }
}
