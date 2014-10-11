package com.ordonteam.hackzurich.mode
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.WaitingActivity
import com.ordonteam.hackzurich.gameserver.GameServer
import com.ordonteam.hackzurich.gameserver.GameServerSocket
import com.ordonteam.hackzurich.gameserver.ObjectSocket
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class CreateGameSelectLayout extends CenteredLayout {

    CreateGameSelectLayout (Activity activity) {
        super(activity)
        setBackgroundColor(Color.argb(255,0,157,71))
        setPadding(20)

        TextView userIpInfo = new TextView(activity)
        userIpInfo.setText('Your IP address:')
        addView(userIpInfo)

        EditText userIpAddress = new EditText(activity)
        userIpAddress.setEnabled(false)
        userIpAddress.setText(showIps())
        addView(userIpAddress)

        Button createGameButton = new Button(activity)
        createGameButton.setText('Create game')
        createGameButton.setOnClickListener({
            GameServer.create()
            Intent intent = new Intent(activity, WaitingActivity.class)
            intent.putExtra('nick','value')
            activity.startActivity(intent)
        })
        addView(createGameButton)
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    private String showIps() {
        List<String> flatten = NetworkInterface.getNetworkInterfaces().collect { NetworkInterface ni ->
            ni.inetAddresses.collect { InetAddress ia ->
                ia.getHostAddress()
            }
        }.flatten().findAll { String host ->
            host ==~ ~/\d+.\d+.\d+.\d+/ && host != '127.0.0.1'
        }

        return (flatten.isEmpty() ? 'xxx.xxx.xxx.xxx' : flatten.get(0)).toString()
    }
}