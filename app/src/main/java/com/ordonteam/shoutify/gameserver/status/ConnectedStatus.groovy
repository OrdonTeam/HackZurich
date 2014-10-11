package com.ordonteam.shoutify.gameserver.status

import android.util.Log
import com.ordonteam.shoutify.gameserver.ObjectSocket
import com.ordonteam.shoutify.gameserver.messages.Message
import com.ordonteam.shoutify.gameserver.messages.ReadyMessage
import com.ordonteam.shoutify.gameserver.messages.StartedMessage
import groovy.transform.CompileStatic

@CompileStatic
class ConnectedStatus implements ServerStatus {
    int i = 0

    ConnectedStatus() {
        Log.e('ConnectedStatus','Players Connected')
    }

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        Log.e('ConnectedStatus',"receiveMessage ${message.getClass()}")
        if(message instanceof ReadyMessage){
            i++
            if (i == 2) {
                first.sendMessage(new StartedMessage())
                second.sendMessage(new StartedMessage())
                return new StartedStatus()
            } else {
                return this
            }
        }else{
            throw new RuntimeException("Server received unexpected message ${message.getClass()}")
        }
    }
}
