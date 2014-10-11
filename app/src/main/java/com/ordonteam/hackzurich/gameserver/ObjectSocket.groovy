package com.ordonteam.hackzurich.gameserver

import android.util.Log
import com.ordonteam.hackzurich.gameserver.messages.Message
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.util.ThreadUtil.startThread

@CompileStatic
class ObjectSocket{

    public static final int PORT = 9999
    private ObjectOutputStream outputStream
    private ObjectInputStream inputStream

    ObjectSocket(String ipAddress) {
        this(new Socket(ipAddress, PORT))
    }

    ObjectSocket(Socket socket) {
        outputStream = new ObjectOutputStream(socket.getOutputStream())
        outputStream.flush()
        inputStream = new ObjectInputStream(socket.getInputStream())
    }

    void sendMessage(Message message) {
        Log.e("ObjectSocket","Sending ${message.getClass()}")
        startThread{
            outputStream.writeObject(message)
            outputStream.flush()
        }
    }

    Message receiveMessage() {
        return (Message) inputStream.readObject()
    }
}
