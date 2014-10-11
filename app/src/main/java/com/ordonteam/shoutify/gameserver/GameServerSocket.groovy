package com.ordonteam.shoutify.gameserver

import android.util.Log
import com.ordonteam.shoutify.gameserver.messages.AttackMessage
import com.ordonteam.shoutify.gameserver.messages.ConnectMessage
import com.ordonteam.shoutify.gameserver.messages.ConnectedMessage
import com.ordonteam.shoutify.gameserver.messages.DisconnectMessage
import com.ordonteam.shoutify.gameserver.messages.LooseMessage
import com.ordonteam.shoutify.gameserver.messages.Message
import com.ordonteam.shoutify.gameserver.messages.ReadyMessage
import com.ordonteam.shoutify.gameserver.messages.StartedMessage
import com.ordonteam.shoutify.gameserver.messages.UpdatedMessage
import com.ordonteam.shoutify.gameserver.messages.WinMessage
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class GameServerSocket implements Runnable, Serializable {

    static GameServerSocket gameServerSocket

    static GameServerSocket crateGameSocket(String ipAddress, ClientCallback clientCallback) throws java.net.ConnectException {
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
        objectSocket = new ObjectSocket(ipAddress)
        startThread(this)
        this.objectSocket.sendMessage(new ConnectMessage())
    }

    void ready() {
        objectSocket.sendMessage(new ReadyMessage())
    }

    void attack(int chargeStatus) {
        objectSocket.sendMessage(new AttackMessage(chargeStatus))
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
        Log.e("GameServerSocket","Reciving ${message.getClass()}")
        if(message instanceof ConnectedMessage){
            clientCallback.onConnected()
        }
        if(message instanceof StartedMessage){
            clientCallback.onStarted()
        }
        if(message instanceof UpdatedMessage){
            UpdatedMessage updatedMessage = (UpdatedMessage) message
            clientCallback.onUpdated(updatedMessage.myStatus,updatedMessage.opponentStatus)
        }
        if(message instanceof WinMessage){
            clientCallback.onWin()
        }
        if(message instanceof LooseMessage){
            clientCallback.onLoose()
        }
    }
}
