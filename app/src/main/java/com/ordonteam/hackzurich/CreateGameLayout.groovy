package com.ordonteam.hackzurich

import android.content.Context
import android.widget.Button
import android.widget.TextView
import groovy.transform.CompileStatic

@CompileStatic
class CreateGameLayout extends CenteredLayout{
    CreateGameLayout(Context context) {
        super(context)
        TextView userIpInfo = new TextView(context)
        userIpInfo.setText("Your IP address:")
        TextView userIpAddress = new TextView(context)
        userIpAddress.setText("127.0.0.1")
        Button createGameButton = new Button(context)
        createGameButton.setText("Create game")
        addView(userIpInfo)
        addView(userIpAddress)
        addView(createGameButton)
    }
}
