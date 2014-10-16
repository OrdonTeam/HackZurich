package com.ordonteam.shoutify.mode

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ordonteam.shoutify.CenteredLayout
import com.ordonteam.shoutify.game.GameActivity
import com.ordonteam.shoutify.gameserver.messages.ClientCallback
import com.ordonteam.shoutify.gameserver.messages.EmptyClientCallback
import com.ordonteam.shoutify.util.MP3Util
import com.ordonteam.shoutify.waiting.IpUtil
import groovy.transform.CompileStatic

import static android.widget.Toast.makeText
import static com.ordonteam.shoutify.gameserver.GameServerSocket.crateGameSocket
import static com.ordonteam.shoutify.util.ThreadUtil.startThread
import static com.ordonteam.shoutify.waiting.IpUtil.getPartialIp

@CompileStatic
class JoinGameSelectLayout extends CenteredLayout implements ClientCallback, View.OnClickListener {
    EditText hostIpAddress
    private Activity activity
    Button joinGame

    @Delegate
    ClientCallback delegate = new EmptyClientCallback()

    JoinGameSelectLayout(Activity activity) {
        super(activity)
        this.activity = activity
        setBackgroundColor(Color.argb(255, 69, 97, 157))
        setPadding(20)

        TextView hostIpInfo = new TextView(activity)
        hostIpInfo.setText('Please specify host IP:')
        hostIpInfo.setTextSize(20)
        addView(hostIpInfo)

        hostIpAddress = new EditText(activity)
        hostIpAddress.setInputType(InputType.TYPE_CLASS_PHONE)
        hostIpAddress.setText(partialIp)
        hostIpAddress.setTextSize(20)
        addView(hostIpAddress)

        joinGame = new Button(activity)
        joinGame.setText('Join game')
        joinGame.setTextSize(20)
        joinGame.setOnClickListener(this)
        addView(joinGame)
    }

    @Override
    void onConnected() {
        Intent intent = new Intent(activity, GameActivity.class)
        activity.startActivity(intent)
    }

    @Override
    void onDisconnect() {
        activity.finish()
    }

    @Override
    void onClick(View view) {
        MP3Util.playSword(activity)
        String ipz = hostIpAddress.getText().toString();
        if (IpUtil.isProperExternalIp.call(ipz)) {
            joinGame.setEnabled(false)
            startThread {
                tryConnect(ipz)
            }
        } else {
            makeText(context, "Check your IP and try again", Toast.LENGTH_SHORT).show()
        }
    }

    void tryConnect(String ipAddress) {
        try {
            crateGameSocket(ipAddress, this)
        }
        catch (Exception e) {
            post {
                makeText(context, "Connection unavailable, check your IP", Toast.LENGTH_SHORT).show();
                joinGame.setEnabled(true)
            }
        }
    }

    void onResume() {
        joinGame.setEnabled(true)
    }
}