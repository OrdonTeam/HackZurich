package com.ordonteam.hackzurich.mode
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.game.GameActivity
import com.ordonteam.hackzurich.gameserver.ClientCallback
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import groovy.transform.CompileStatic

import java.util.regex.Pattern

@CompileStatic
class JoinGameSelectLayout extends CenteredLayout implements ClientCallback {
    EditText hostIpAddress
    private Activity activity

    JoinGameSelectLayout(Activity activity) {
        super(activity)
        this.activity = activity
        setBackgroundColor(Color.argb(255,69,97,157))
        setPadding(20)

        TextView hostIpInfo = new TextView(activity)
        hostIpInfo.setText('Please specify host IP:')
        addView(hostIpInfo)

        hostIpAddress = new EditText(activity)
        hostIpAddress.setInputType(InputType.TYPE_CLASS_PHONE)
        hostIpAddress.setText('192.168.1.')
        addView(hostIpAddress)

        Button joinGame = new Button(activity)
        joinGame.setText('Join game')
        joinGame.setOnClickListener({
            GameServerSocket.crateGameSocket(hostIpAddress.getText().toString(),this);
        })
        addView(joinGame)
    }

    @Override
    void onConnected() {
        Intent intent = new Intent(activity, GameActivity.class)
        activity.startActivity(intent)
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