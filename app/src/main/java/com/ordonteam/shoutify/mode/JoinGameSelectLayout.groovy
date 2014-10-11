package com.ordonteam.shoutify.mode

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ordonteam.shoutify.CenteredLayout
import com.ordonteam.shoutify.game.GameActivity
import com.ordonteam.shoutify.gameserver.ClientCallback
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import static android.widget.Toast.makeText
import static com.ordonteam.shoutify.gameserver.GameServerSocket.crateGameSocket
import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class JoinGameSelectLayout extends CenteredLayout implements ClientCallback {
    EditText hostIpAddress
    private Activity activity

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
        hostIpAddress.setText(cutIP())
        hostIpAddress.setTextSize(20)
        addView(hostIpAddress)

        Button joinGame = new Button(activity)
        joinGame.setText('Join game')
        joinGame.setTextSize(20)
        joinGame.setOnClickListener({
            String ipz = hostIpAddress.getText().toString();
            if (ipz ==~ ~/\d+.\d+.\d+.\d+/) {
                startThread { tryConnect(ipz) }
            } else {
                makeText(context, "WRONG IP, YOU MORON", Toast.LENGTH_SHORT).show()
            }
        })
        addView(joinGame)
    }

    void tryConnect(String ipAddress) {
        try {
            crateGameSocket(ipAddress, this)
        }
        catch (Ex) {
            post {
                makeText(context, "CONNECTION UNAVAILABLE, CHECK IP", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    void onConnected() {
        Intent intent = new Intent(activity, GameActivity.class)
        activity.startActivity(intent)
    }

    @Override
    void onStarted() {

    }

    @Override
    void onUpdated(int myStatus, int opponentStatus) {

    }

    @Override
    void onWin() {

    }

    @Override
    void onLoose() {

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

    private String cutIP() {
        String ip = showIps();
        String[] parts = ip.split("\\.")
        String ipPart = parts[0] + "." + parts[1] + "." + parts[2] + "."
        return ipPart
    }

    private Boolean checkIP(String ipz) {
        try {
            Inet4Address.getByName(ipz);
            return true;
        }
        catch (Ex) {
            return false;
        }
    }
}