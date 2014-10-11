package com.ordonteam.hackzurich

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.ordonteam.hackzurich.gameserver.ClientCallback
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import com.ordonteam.hackzurich.waiting.WaitingLayout
import groovy.transform.CompileStatic

@CompileStatic
class WaitingActivity extends Activity implements ClientCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new WaitingLayout(applicationContext))
        Serializable nick = getIntent().getExtras().getSerializable('nick')
        GameServerSocket gameServerSocket = new GameServerSocket('127.0.0.1', this)
    }

    @Override
    void onConnected() {
        Intent intent = new Intent(this, MainActivity.class)
        startActivity(intent)
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
