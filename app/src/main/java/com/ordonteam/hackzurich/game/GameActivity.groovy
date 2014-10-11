package com.ordonteam.hackzurich.game
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.ordonteam.hackzurich.gameserver.ClientCallback
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import com.ordonteam.hackzurich.sensors.AccelerometerActivator
import com.ordonteam.hackzurich.sensors.VoiceActivator
import groovy.transform.CompileStatic


@CompileStatic
class GameActivity extends Activity implements ClientCallback{

    GameLayout gameLayout
    RelativeLayout gameLayoutWrapper
    RelativeLayout curtain

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        gameLayoutWrapper = new RelativeLayout(this)
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
        gameLayoutWrapper.setLayoutParams(lp1)

        gameLayout = new GameLayout(this)
        gameLayout.setLayoutParams(lp1)
        gameLayoutWrapper.addView(gameLayout)

        curtain = new RelativeLayout(this)
        curtain.setLayoutParams(lp1)
        curtain.setBackgroundColor(Color.argb(190,0,0,0))
        gameLayoutWrapper.addView(curtain)

        TextView waitInfo = new TextView(this)
        waitInfo.setText('Please wait for your opponent')
        waitInfo.setTextColor(Color.WHITE)
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        lp2.addRule(RelativeLayout.CENTER_IN_PARENT)
        waitInfo.setLayoutParams(lp2)
        curtain.addView(waitInfo)

        setContentView(gameLayoutWrapper)
        GameServerSocket.getGameServerSocket().setClientCallback(this)
        GameServerSocket.getGameServerSocket().ready()
        new VoiceActivator({
            gameLayout.charge()
        })
        new AccelerometerActivator(this, {
            GameServerSocket.getGameServerSocket().attack(gameLayout.userChargingProgressbar.progress)
            gameLayout.resetChargeProgress()
        })
    }

    @Override
    void onConnected() {

    }

    @Override
    void onStarted() {
        Log.e('GameActivity','onStarted')
        GameServerSocket.getGameServerSocket().ready()
        gameLayoutWrapper.post({
            gameLayoutWrapper.removeView(curtain)
        })
    }

    @Override
    void onUpdated(int myStatus,int opponentStatus) {
        gameLayout.updateHealthStatus(myStatus, opponentStatus)
    }

    @Override
    void onWin() {

    }

    @Override
    void onLoose() {

    }
}
