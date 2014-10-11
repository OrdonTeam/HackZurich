package com.ordonteam.hackzurich.game
import android.app.Activity
import android.os.Bundle
import com.ordonteam.hackzurich.gameserver.ClientCallback
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import groovy.transform.CompileStatic

@CompileStatic
class GameActivity extends Activity implements ClientCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new GameLayout(this))
        GameServerSocket.getGameServerSocket().setClientCallback(this)
    }

    @Override
    void onConnected() {

    }

    @Override
    void onStarted() {

    }

    @Override
    void onUpdated() {

    }

    @Override
    void onWin() {

    }

    @Override
    void onLoose() {

    }
}
