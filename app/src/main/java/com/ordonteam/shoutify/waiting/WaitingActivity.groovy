package com.ordonteam.shoutify.waiting
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import com.ordonteam.shoutify.game.GameActivity
import com.ordonteam.shoutify.gameserver.ClientCallback
import com.ordonteam.shoutify.gameserver.GameServerSocket
import groovy.transform.CompileStatic

@CompileStatic
class WaitingActivity extends Activity implements ClientCallback {

    private GameServerSocket gameServerSocket

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        WaitingLayout look = new WaitingLayout(applicationContext)
        look.setPadding(20)
        look.setBackgroundColor(Color.argb(255,0,157,71))
        setContentView(look)

        Serializable nick = getIntent().getExtras().getSerializable('nick')
        gameServerSocket = GameServerSocket.crateGameSocket('127.0.0.1', this)
    }

    @Override
    void onConnected() {
        Intent intent = new Intent(this, GameActivity.class)
        this.startActivityForResult(intent,1)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish()
    }

    @Override
    void onStarted(String otherPlayerName) {

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

    @Override
    void onDisconnect() {
        finish()
    }
}
