package com.ordonteam.hackzurich

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import com.ordonteam.hackzurich.gameserver.ClientCallback
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import com.ordonteam.hackzurich.game.GameActivity
import com.ordonteam.hackzurich.waiting.WaitingLayout
import groovy.transform.CompileStatic

@CompileStatic
class WaitingActivity extends Activity implements ClientCallback {

    private GameServerSocket gameServerSocket

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        WaitingLayout look = new WaitingLayout(applicationContext)
        look.setPadding(20)
        look.setBackgroundColor(Color.argb(255,69,97,157))
        setContentView(look)

        Serializable nick = getIntent().getExtras().getSerializable('nick')
        gameServerSocket = GameServerSocket.crateGameSocket('127.0.0.1', this)

    }

    @Override
    void onConnected() {
        Intent intent = new Intent(this, GameActivity.class)
        this.startActivity(intent)
    }

    @Override
    void onStarted() {

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
