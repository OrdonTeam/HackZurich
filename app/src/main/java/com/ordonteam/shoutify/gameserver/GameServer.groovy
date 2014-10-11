package com.ordonteam.shoutify.gameserver

import com.ordonteam.shoutify.gameserver.messages.Message
import com.ordonteam.shoutify.gameserver.status.CreatedStatus
import com.ordonteam.shoutify.gameserver.status.ServerStatus
import groovy.transform.CompileStatic

import static ObjectSocket.PORT
import static com.ordonteam.shoutify.util.ThreadUtil.startThread

@CompileStatic
class GameServer implements Runnable {
    static private GameServer server

    static GameServer getGameServer() {
        return server
    }

    static GameServer create() {
        if (server) {
            server.shutdown()
        }
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
        try {
            this.socket = new ServerSocket(PORT)
            firstClient = new ObjectSocket(socket.accept())
            startThread(new ReceiveMessages(firstClient))
            secondClient = new ObjectSocket(socket.accept())
            startThread(new ReceiveMessages(secondClient))
        } catch (ex) {
            ex.printStackTrace()
        }
    }

    synchronized void receiveMessage(Message message, ObjectSocket client) {
        currentStatus = currentStatus.receiveMessage(message, firstClient == client, firstClient, secondClient)
    }

    void shutdown() {
        socket.close()
        firstClient?.close()
        secondClient?.close()
    }

    class ReceiveMessages implements Runnable {
        ObjectSocket client

        ReceiveMessages(ObjectSocket client) {
            this.client = client
        }

        @Override
        void run() {
            try {
                while (!socket.isClosed()) {
                    receiveMessage(client.receiveMessage(), client)
                }
            } catch (ex) {
                ex.printStackTrace()
            }
        }
    }
}
