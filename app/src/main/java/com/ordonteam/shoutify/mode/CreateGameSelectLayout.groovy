package com.ordonteam.shoutify.mode
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.shoutify.CenteredLayout
import com.ordonteam.shoutify.gameserver.GameServer
import com.ordonteam.shoutify.util.MP3Util
import com.ordonteam.shoutify.waiting.IpUtil
import com.ordonteam.shoutify.waiting.WaitingActivity
import groovy.transform.CompileStatic

@CompileStatic
class CreateGameSelectLayout extends CenteredLayout implements View.OnClickListener{

    Button createGameButton
    private Activity activity

    CreateGameSelectLayout (Activity activity) {
        super(activity)
        this.activity = activity
        setBackgroundColor(Color.argb(255,0,157,71))
        setPadding(20)

        TextView userIpInfo = new TextView(activity)
        userIpInfo.setText('Your IP address:')
        userIpInfo.setTextSize(20)
        addView(userIpInfo)

        EditText userIpAddress = new EditText(activity)
        userIpAddress.setEnabled(false)
        userIpAddress.setText(IpUtil.getIpAddress())
        userIpAddress.setTextSize(20)
        addView(userIpAddress)

        createGameButton = new Button(activity)
        createGameButton.setText('Create game')
        createGameButton.setTextSize(20)
        createGameButton.setOnClickListener(this)
        addView(createGameButton)
    }

    @Override
    void onClick(View view) {
        MP3Util.playSword(activity)
        createGameButton.setEnabled(false)
        GameServer.create()
        Intent intent = new Intent(activity, WaitingActivity.class)
        activity.startActivity(intent)
    }

    void onResume() {
        createGameButton.setEnabled(true)
    }
}
