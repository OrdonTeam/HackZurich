package com.ordonteam.shoutify.mode
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ordonteam.shoutify.CenteredLayout
import com.ordonteam.shoutify.WaitingActivity
import com.ordonteam.shoutify.gameserver.GameServer
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class CreateGameSelectLayout extends CenteredLayout {

    Button createGameButton

    CreateGameSelectLayout (Activity activity) {
        super(activity)
        setBackgroundColor(Color.argb(255,0,157,71))
        setPadding(20)

        TextView userIpInfo = new TextView(activity)
        userIpInfo.setText('Your IP address:')
        userIpInfo.setTextSize(20)
        addView(userIpInfo)

        EditText userIpAddress = new EditText(activity)
        userIpAddress.setEnabled(false)
        userIpAddress.setText(showIps())
        userIpAddress.setTextSize(20)
        addView(userIpAddress)

        createGameButton = new Button(activity)
        createGameButton.setText('Create game')
        createGameButton.setTextSize(20)
        createGameButton.setOnClickListener({
            setEnabled(false)
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
