package com.ordonteam.shoutify.game
import android.app.Activity
import android.graphics.Color
import android.widget.RelativeLayout
import com.ordonteam.shoutify.game.bars.HealthBar
import com.ordonteam.shoutify.game.bars.UserChargingProgressbar
import groovy.transform.CompileStatic

@CompileStatic
class GameLayout extends RelativeLayout {

    int i=100
    HealthBar userHealthBar
    UserChargingProgressbar userChargingProgressbar
    HealthBar opponentHealthBar

    GameLayout(Activity activity) {
        super(activity)

        userHealthBar = new HealthBar(activity, Color.GREEN)
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

        opponentHealthBar = new HealthBar(activity, Color.RED)
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
