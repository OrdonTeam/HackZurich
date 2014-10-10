package com.ordonteam.hackzurich.gameserver

import com.ordonteam.hackzurich.gameserver.messages.Message
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class ObjectSocket {

    public static final int PORT = 9999
    private ObjectOutputStream outputStream
    private ObjectInputStream inputStream

    ObjectSocket(String ipAddress) {
        Socket socket = new Socket(ipAddress, PORT)
        outputStream = new ObjectOutputStream(socket.getOutputStream())
        outputStream.flush()
        inputStream = new ObjectInputStream(socket.getInputStream())
    }

    void sendMessage(Message message) {
        startThread{
            outputStream.writeObject(message)
            outputStream.flush()
        }
    }

    Message receiveMessage() {
        return (Message) inputStream.readObject()
    }
}