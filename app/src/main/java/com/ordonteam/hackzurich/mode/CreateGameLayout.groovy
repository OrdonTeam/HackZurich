package com.ordonteam.hackzurich.mode
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.WaitingActivity
import groovy.transform.CompileStatic

@CompileStatic
class CreateGameLayout extends CenteredLayout{
    CreateGameLayout(Activity activity) {
        super(activity)
        setBackgroundColor(Color.argb(255,0,157,71))
        setPadding(20)

        TextView userIpInfo = new TextView(activity)
        userIpInfo.setText('Your IP address:')
        addView(userIpInfo)

        EditText userIpAddress = new EditText(activity)
        userIpAddress.setEnabled(false)
        userIpAddress.setText('192.168.1.11')
        addView(userIpAddress)

        Button createGameButton = new Button(activity)
        createGameButton.setText('Create game')
        createGameButton.setOnClickListener({
            Intent intent = new Intent(activity, WaitingActivity.class)
            activity.startActivity(intent)
        })
        addView(createGameButton)
    }
}
