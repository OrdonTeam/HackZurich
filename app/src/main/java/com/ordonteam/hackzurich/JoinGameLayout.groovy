package com.ordonteam.hackzurich

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import groovy.transform.CompileStatic

@CompileStatic
class JoinGameLayout extends CenteredLayout{
    EditText hostIpAddress
    JoinGameLayout(Activity activity) {
        super(activity)
        TextView hostIpInfo = new TextView(activity)
        hostIpInfo.setText("Please specify host IP:")
        hostIpAddress = new EditText(activity)
        Button joinGame = new Button(activity)
        joinGame.setText("Join game")
        joinGame.setOnClickListener({
            Intent intent = new Intent(activity, MainActivity.class)
            activity.startActivity(intent)
        })
        addView(hostIpInfo)
        addView(hostIpAddress)
        addView(joinGame)
    }
}
