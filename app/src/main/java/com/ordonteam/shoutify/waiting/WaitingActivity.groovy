package com.ordonteam.shoutify.waiting
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.ordonteam.shoutify.game.GameActivity
import com.ordonteam.shoutify.gameserver.GameServerSocket
import com.ordonteam.shoutify.gameserver.messages.ClientCallback
import com.ordonteam.shoutify.gameserver.messages.EmptyClientCallback
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.gameserver.GameServerSocket.crateGameSocket

@CompileStatic
class WaitingActivity extends Activity implements ClientCallback {

    @Delegate //Thanks to @Delegate we do not have to implement all methods from ClientCallback
    ClientCallback delegate = new EmptyClientCallback()
    private GameServerSocket gameServerSocket

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(new WaitingLayout(applicationContext))

        gameServerSocket = crateGameSocket('127.0.0.1', this)
    }

    @Override
    void onConnected() {
        Intent intent = new Intent(this, GameActivity.class)
        this.startActivityForResult(intent,1)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onDisconnect()
    }

    @Override
    void onDisconnect() {
        finish()
    }
}
