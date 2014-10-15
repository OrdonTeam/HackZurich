package com.ordonteam.shoutify.gameserver

import com.ordonteam.shoutify.gameserver.messages.*
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class GameServerSocket implements Runnable, Serializable {

    static GameServerSocket gameServerSocket

    static GameServerSocket crateGameSocket(String ipAddress, ClientCallback clientCallback) throws java.net.ConnectException {
        gameServerSocket = new GameServerSocket(ipAddress, clientCallback)
        return gameServerSocket
    }

    private ObjectSocket objectSocket
    private transient ClientCallback clientCallback
    private String ipAddress

    void setClientCallback(ClientCallback clientCallback) {
        this.clientCallback = clientCallback
    }

    private GameServerSocket(String ipAddress, ClientCallback clientCallback) {
        this.ipAddress = ipAddress
        this.clientCallback = clientCallback
        objectSocket = new ObjectSocket(ipAddress)
        startThread(this)
        this.objectSocket.sendMessage(new ConnectMessage())
    }

    void ready(String nick) {
        objectSocket.sendMessage(new ReadyMessage(nick))
    }

    void attack(int chargeStatus) {
        objectSocket.sendMessage(new AttackMessage(chargeStatus))
    }

    void disconnect() {
        objectSocket.sendMessage(new DisconnectMessage())
    }

    @Override
    void run() {
        try {
            while (true) {
                ServerMessage message = (ServerMessage)objectSocket.receiveMessage()
                message.call(clientCallback)
            }
        } catch (EOFException e) {
            clientCallback.onDisconnect()
        } catch (Exception e) {
            e.printStackTrace()
            clientCallback.onDisconnect()
        }
    }

}
