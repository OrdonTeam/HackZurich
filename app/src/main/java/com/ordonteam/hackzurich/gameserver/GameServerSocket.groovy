package com.ordonteam.hackzurich.gameserver

import android.util.Log
import com.ordonteam.hackzurich.gameserver.messages.AttackMessage
import com.ordonteam.hackzurich.gameserver.messages.ConnectMessage
import com.ordonteam.hackzurich.gameserver.messages.DisconnectMessage
import com.ordonteam.hackzurich.gameserver.messages.Message
import com.ordonteam.hackzurich.gameserver.messages.ReadyMessage
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class GameServerSocket implements Runnable, Serializable {

    private ObjectSocket objectSocket
    private ClientCallback clientCallback

    GameServerSocket (String ipAddress, ClientCallback clientCallback) {
        this.clientCallback = clientCallback
        startThread{
            objectSocket = new ObjectSocket(ipAddress)
            startThread(this)
            this.objectSocket.sendMessage(new ConnectMessage())
        }
    }

    void ready() {
        objectSocket.sendMessage(new ReadyMessage())
    }

    void attack() {
        objectSocket.sendMessage(new AttackMessage())
    }

    void disconnect() {
        objectSocket.sendMessage(new DisconnectMessage())
        new GameServer()
    }

    @Override
    void run() {
        while (true){
            receiveMessage()
        }
    }

    void receiveMessage() {
        Message message = objectSocket.receiveMessage()
        Log.e('GameServerSocket','receiving messages not supported yet')
        //Try avoid switch here
    }
}
