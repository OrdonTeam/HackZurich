package com.ordonteam.shoutify.game
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.ordonteam.shoutify.gameserver.ClientCallback
import com.ordonteam.shoutify.gameserver.GameServer
import com.ordonteam.shoutify.gameserver.GameServerSocket
import com.ordonteam.shoutify.sensors.AccelerometerActivator
import com.ordonteam.shoutify.sensors.VoiceActivator
import com.ordonteam.shoutify.util.FileUtil
import com.ordonteam.shoutify.util.MP3Util
import com.ordonteam.shoutify.util.ThreadUtil
import groovy.transform.CompileStatic

@CompileStatic
class GameActivity extends Activity implements ClientCallback{

    GameLayout gameLayout
    RelativeLayout gameLayoutWrapper
    RelativeLayout curtain
    private VoiceActivator voiceActivator
    private AccelerometerActivator accelerometerActivator
    private String myNick

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

        myNick = FileUtil.readNick(this)
        ThreadUtil.delay(1000,{GameServerSocket.getGameServerSocket().ready(this.myNick)})
    }

    @Override
    void onConnected() {

    }

    @Override
    void onStarted(String otherPlayerName) {
        gameLayout.setNames(myNick,otherPlayerName)
        Log.d('GameActivity','onStarted')
        gameLayoutWrapper.post({
            gameLayoutWrapper.removeView(curtain)
        })
        voiceActivator = new VoiceActivator({
            gameLayout.charge()
        })
        accelerometerActivator = new AccelerometerActivator(this, {
            GameServerSocket.getGameServerSocket().attack(gameLayout.userChargingProgressbar.progress)
            if(gameLayout.userChargingProgressbar.progress>30)
                MP3Util.play3(this)
            if(gameLayout.userChargingProgressbar.progress>60)
                MP3Util.play1(this)
            if(gameLayout.userChargingProgressbar.progress>90)
                MP3Util.playSword(this)
            gameLayout.resetChargeProgress()
        })
    }

    @Override
    void onUpdated(int myStatus,int opponentStatus) {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(500);
        Log.e('GameActivity',"onUpdated $myStatus $opponentStatus")
        gameLayout.updateHealthStatus(myStatus, opponentStatus)
    }

    @Override
    void onWin() {
        Log.d('GameActivity', 'onWin')
        gameLayout.showVictory()
    }

    @Override
    void onLoose() {
        Log.d('GameActivity', 'onLoose')
        gameLayout.showDefeated()
    }

    @Override
    void onDisconnect() {
        finish()
    }

    @Override
    void onPause(){
        super.onPause()
        GameServer.getGameServer()?.shutdown()
        voiceActivator?.stop()
        accelerometerActivator?.stop()
    }
}
