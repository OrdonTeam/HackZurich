package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.MainActivity
import groovy.transform.CompileStatic

@CompileStatic
class CreateGameLayout extends CenteredLayout{
    CreateGameLayout(Activity activity) {
        super(activity)
        TextView userIpInfo = new TextView(activity)
        userIpInfo.setText("Your IP address:")
        TextView userIpAddress = new TextView(activity)
        userIpAddress.setText("127.0.0.1")
        Button createGameButton = new Button(activity)
        createGameButton.setText("Create game")
        createGameButton.setOnClickListener({
            Intent intent = new Intent(activity, MainActivity.class)
            activity.startActivity(intent)
        })
        addView(userIpInfo)
        addView(userIpAddress)
        addView(createGameButton)
    }
}
