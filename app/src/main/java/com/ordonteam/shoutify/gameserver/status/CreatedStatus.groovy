package com.ordonteam.shoutify.gameserver.status
import android.util.Log
import com.ordonteam.shoutify.gameserver.ObjectSocket
import com.ordonteam.shoutify.gameserver.messages.ConnectMessage
import com.ordonteam.shoutify.gameserver.messages.ConnectedMessage
import com.ordonteam.shoutify.gameserver.messages.Message
import groovy.transform.CompileStatic

@CompileStatic
class CreatedStatus implements ServerStatus {

    int connectedClients = 0

    CreatedStatus() {
        Log.d('CreatedStatus','Game Created')
    }

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        Log.d('CreatedStatus',"receiveMessage ${message.getClass()}")
        if(message instanceof ConnectMessage) {
            return connectClient(first, second)
        } else {
            throw new RuntimeException("Server received unexpected message ${message.getClass()}")
        }
    }

    private ServerStatus connectClient(ObjectSocket first, ObjectSocket second) {
        connectedClients++
        if (connectedClients == 2) {
            first.sendMessage(new ConnectedMessage())
            second.sendMessage(new ConnectedMessage())
            return new ConnectedStatus()
        } else {
            return this
        }
    }
}
