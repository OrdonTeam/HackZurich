package com.ordonteam.hackzurich.gameserver

import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.gameserver.ObjectSocket.PORT
import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class GameServer implements Runnable{
    private ServerSocket socket
    private ObjectSocket firstClient
    private ObjectSocket secondClient

    GameServer() {
        startThread{this}
    }

    @Override
    void run() {
        this.socket = new ServerSocket(PORT)
        firstClient = new ObjectSocket(socket.accept())
        secondClient = new ObjectSocket(socket.accept())
    }
}
