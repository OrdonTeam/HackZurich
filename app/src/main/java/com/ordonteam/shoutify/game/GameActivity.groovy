package com.ordonteam.shoutify.game
import android.app.Activity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.WindowManager
import android.widget.RelativeLayout
import com.ordonteam.shoutify.gameserver.messages.ClientCallback
import com.ordonteam.shoutify.gameserver.GameServer
import com.ordonteam.shoutify.gameserver.GameServerSocket
import com.ordonteam.shoutify.sensors.AccelerometerActivator
import com.ordonteam.shoutify.sensors.VoiceActivator
import com.ordonteam.shoutify.util.FileUtil
import com.ordonteam.shoutify.util.MP3Util
import com.ordonteam.shoutify.util.ThreadUtil
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ViewUtil.getNewRelativeMatchParent

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
        gameLayoutWrapper.setLayoutParams(newRelativeMatchParent)

        gameLayout = new GameLayout(this)
        gameLayoutWrapper.addView(gameLayout)

        curtain = new CurtainLayout(this)
        gameLayoutWrapper.addView(curtain)

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
            else if(gameLayout.userChargingProgressbar.progress>60)
                MP3Util.play1(this)
            else if(gameLayout.userChargingProgressbar.progress>90)
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
