package com.ordonteam.shoutify.gameserver
import android.util.Log
import com.ordonteam.shoutify.gameserver.messages.*
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class GameServerSocket implements Runnable, Serializable {

    static GameServerSocket gameServerSocket
    static GameServerSocket crateGameSocket(String ipAddress, ClientCallback clientCallback) throws java.net.ConnectException {
        GameServerSocket gameServerSocket = new GameServerSocket(ipAddress, clientCallback)
        gameServerSocket.start()
        return gameServerSocket
    }
    static GameServerSocket getGameSocket() {
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
    }

    void start(){
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
        new GameServer()
    }

    @Override
    void run() {
        try{
            while (true){
                receiveMessage()
            }
        }catch (EOFException e){
            clientCallback.onDisconnect()
        }catch (Exception e){
            e.printStackTrace()
            clientCallback.onDisconnect()
        }
    }

    void receiveMessage() {
        Message message = objectSocket.receiveMessage()
        Log.d("GameServerSocket","Reciving ${message.getClass()}")
        if(message instanceof ConnectedMessage){
            clientCallback.onConnected()
        }
        if(message instanceof StartedMessage){
            StartedMessage startedMessage = (StartedMessage) message
            clientCallback.onStarted(startedMessage.otherPlayerName)
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
