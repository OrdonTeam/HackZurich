package com.ordonteam.hackzurich.gameserver

import android.util.Log
import com.ordonteam.hackzurich.gameserver.messages.*
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class GameServerSocket implements Runnable, Serializable {

    static GameServerSocket gameServerSocket

    static GameServerSocket crateGameSocket(String ipAddress, ClientCallback clientCallback) {
        GameServerSocket socket = new GameServerSocket(ipAddress, clientCallback)
        gameServerSocket = socket
        return socket
    }

    static GameServerSocket getGameSocket() {
        return gameServerSocket
    }

    private ObjectSocket objectSocket
    private transient ClientCallback clientCallback

    void setClientCallback(ClientCallback clientCallback) {
        this.clientCallback = clientCallback
    }

    private GameServerSocket(String ipAddress, ClientCallback clientCallback) {
        this.clientCallback = clientCallback
        startThread {
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
        while (true) {
            receiveMessage()
        }
    }

    void receiveMessage() {
        Message message = objectSocket.receiveMessage()
        Log.e('GameServerSocket', 'receiving messages not supported yet')
        startThread{
            clientCallback.onConnected()
        }
        //Try avoid switch here
    }
}
