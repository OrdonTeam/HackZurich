package com.ordonteam.hackzurich.game

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.ordonteam.hackzurich.gameserver.ClientCallback
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import com.ordonteam.hackzurich.sensors.AccelerometerActivator
import com.ordonteam.hackzurich.sensors.VoiceActivator
import groovy.transform.CompileStatic

@CompileStatic
class GameActivity extends Activity implements ClientCallback{

    GameServerSocket gameServerSocket
    GameLayout gameLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        gameLayout = new GameLayout(this)
        setContentView(gameLayout)
        GameServerSocket.getGameServerSocket().setClientCallback(this)
        new VoiceActivator({
            gameLayout.charge()
        })
        new AccelerometerActivator(this, {
            getGameServerSocket().attack()
        })
    }

    @Override
    void onConnected() {

    }

    @Override
    void onStarted() {
        gameServerSocket.ready()
    }

    @Override
    void onUpdated(int myStatus,int opponentStatus) {

    }

    @Override
    void onWin() {

    }

    @Override
    void onLoose() {

    }
}
