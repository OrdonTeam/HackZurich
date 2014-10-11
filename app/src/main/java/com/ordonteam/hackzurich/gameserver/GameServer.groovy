package com.ordonteam.hackzurich.gameserver

import com.ordonteam.hackzurich.gameserver.messages.Message
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.gameserver.ObjectSocket.PORT
import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class GameServer implements Runnable{
    private ServerSocket socket
    private ObjectSocket firstClient
    private ObjectSocket secondClient

    GameServer() {
        startThread(this)
    }

    @Override
    void run() {
        this.socket = new ServerSocket(PORT)
        firstClient = new ObjectSocket(socket.accept())
        startThread {new ReceiveMessages(firstClient)}
        secondClient = new ObjectSocket(socket.accept())
        startThread {new ReceiveMessages(secondClient)}
    }

    void receiveMessage(Message message, ObjectSocket client) {
        
    }

    class ReceiveMessages implements Runnable {
        ObjectSocket client
        ReceiveMessages(ObjectSocket client) {
            this.client = client
        }

        @Override
        void run() {
            client.receiveMessage()
        }
    }
}
