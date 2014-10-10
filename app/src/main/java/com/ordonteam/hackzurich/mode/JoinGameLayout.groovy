package com.ordonteam.hackzurich.mode
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.MainActivity
import groovy.transform.CompileStatic

@CompileStatic
class JoinGameLayout extends CenteredLayout{
    EditText hostIpAddress
    JoinGameLayout(Activity activity) {
        super(activity)

        TextView hostIpInfo = new TextView(activity)
        hostIpInfo.setText('Please specify host IP:')
        addView(hostIpInfo)

        hostIpAddress = new EditText(activity)
        hostIpAddress.setText('192.168.1.')
        addView(hostIpAddress)

        Button joinGame = new Button(activity)
        joinGame.setText('Join game')
        joinGame.setOnClickListener({
            Intent intent = new Intent(activity, MainActivity.class)
            activity.startActivity(intent)
        })
        addView(joinGame)
    }
}
