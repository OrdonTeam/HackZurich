package com.ordonteam.shoutify.gameserver

import android.util.Log
import com.ordonteam.shoutify.gameserver.messages.Message
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ThreadUtil.startThread

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
        try{
            return (Message) inputStream.readObject()
        }catch (StreamCorruptedException ex){
            return receiveMessage()
        }
    }
}
