package com.ordonteam.hackzurich.gameserver

import com.ordonteam.hackzurich.gameserver.messages.Message
import com.ordonteam.hackzurich.gameserver.status.CreatedStatus
import com.ordonteam.hackzurich.gameserver.status.ServerStatus
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.gameserver.ObjectSocket.PORT
import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class GameServer implements Runnable{
    static private GameServer server

    static GameServer getGameServer(){
        return server
    }
    static GameServer create() {
        server = new GameServer()
        return server
    }

    private ServerSocket socket
    private ObjectSocket firstClient
    private ObjectSocket secondClient

    private ServerStatus currentStatus = new CreatedStatus();

    GameServer() {
        startThread(this)
    }

    @Override
    void run() {
        this.socket = new ServerSocket(PORT)
        firstClient = new ObjectSocket(socket.accept())
        startThread (new ReceiveMessages(firstClient))
        secondClient = new ObjectSocket(socket.accept())
        startThread (new ReceiveMessages(secondClient))
    }

    void receiveMessage(Message message, ObjectSocket client) {
        currentStatus = currentStatus.receiveMessage(message, firstClient == client, firstClient,secondClient)
    }

    class ReceiveMessages implements Runnable {
        ObjectSocket client
        ReceiveMessages(ObjectSocket client) {
            this.client = client
        }

        @Override
        void run() {
            receiveMessage(client.receiveMessage(), client)
        }
    }
}
