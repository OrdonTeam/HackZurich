package com.ordonteam.hackzurich.gameserver

import com.ordonteam.hackzurich.gameserver.messages.ConnectMessage
import groovy.transform.CompileStatic

@CompileStatic
class GameServerSocket {

    public static final int PORT = 9999

    GameServerSocket(String ipAddress, ClientCallback clientCallback) {
        Socket socket = new Socket(ipAddress, PORT)
        //send Connect
    }

    void ready() {}

    void attack() {}

    void disconnect() {}
}
