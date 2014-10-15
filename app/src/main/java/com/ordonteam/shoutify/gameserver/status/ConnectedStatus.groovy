package com.ordonteam.shoutify.gameserver.status
import android.util.Log
import com.ordonteam.shoutify.gameserver.ObjectSocket
import com.ordonteam.shoutify.gameserver.messages.Message
import com.ordonteam.shoutify.gameserver.messages.ReadyMessage
import com.ordonteam.shoutify.gameserver.messages.StartedMessage
import groovy.transform.CompileStatic

@CompileStatic
class ConnectedStatus implements ServerStatus {
    int readyClients = 0
    String firstName
    String secondName

    ConnectedStatus() {
        Log.d('ConnectedStatus','Players Connected')
    }

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        Log.d('ConnectedStatus',"receiveMessage ${message.getClass()}")
        if(message instanceof ReadyMessage){
            return readyClient(message, isFirst, first, second)
        }else{
            throw new RuntimeException("Server received unexpected message ${message.getClass()}")
        }
    }

    private ServerStatus readyClient(ReadyMessage readyMessage, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        applyPlayerName(isFirst, readyMessage)

        readyClients++
        if (readyClients == 2) {
            first.sendMessage(new StartedMessage(secondName))
            second.sendMessage(new StartedMessage(firstName))
            return new StartedStatus()
        } else {
            return this
        }
    }

    private void applyPlayerName(boolean isFirst, ReadyMessage readyMessage) {
        if (isFirst) {
            firstName = readyMessage.nick
        } else {
            secondName = readyMessage.nick
        }
    }
}
