package com.ordonteam.hackzurich.mode
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.MainActivity
import groovy.transform.CompileStatic

@CompileStatic
class JoinGameSelectLayout extends CenteredLayout{
    EditText hostIpAddress

    JoinGameSelectLayout(Activity activity) {
        super(activity)
        setBackgroundColor(Color.argb(255,69,97,157))
        setPadding(20)

        TextView hostIpInfo = new TextView(activity)
        hostIpInfo.setText('Please specify host IP:')
        addView(hostIpInfo)

        hostIpAddress = new EditText(activity)
        hostIpAddress.setText('192.168.1.')
        addView(hostIpAddress)

        Button joinGame = new Button(activity)
        joinGame.setText('Join game')
        joinGame.setOnClickListener({
            Intent intent = new Intent(activity, JoinGameActivity.class)
            activity.startActivity(intent)
        })
        addView(joinGame)
    }
}
