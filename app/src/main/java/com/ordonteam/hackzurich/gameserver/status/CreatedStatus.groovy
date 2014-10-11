package com.ordonteam.hackzurich.gameserver.status

import android.util.Log
import com.ordonteam.hackzurich.gameserver.ObjectSocket
import com.ordonteam.hackzurich.gameserver.messages.ConnectMessage
import com.ordonteam.hackzurich.gameserver.messages.ConnectedMessage
import com.ordonteam.hackzurich.gameserver.messages.Message
import groovy.transform.CompileStatic

@CompileStatic
class CreatedStatus implements ServerStatus {

    int i = 0

    CreatedStatus() {
        Log.e('CreatedStatus','Game Created')
    }

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        Log.e('CreatedStatus',"receiveMessage ${message.getClass()}")
        if(message instanceof ConnectMessage) {
            i++
            if (i == 2) {
                first.sendMessage(new ConnectedMessage())
                second.sendMessage(new ConnectedMessage())
                return new ConnectedStatus()
            } else {
                return this
            }
        } else {
            throw new RuntimeException("Server received unexpected message ${message.getClass()}")
        }
    }
}
