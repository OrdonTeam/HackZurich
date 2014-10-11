package com.ordonteam.hackzurich.gameserver.status
import com.ordonteam.hackzurich.gameserver.ObjectSocket
import com.ordonteam.hackzurich.gameserver.messages.Message
import com.ordonteam.hackzurich.gameserver.messages.ReadyMessage
import groovy.transform.CompileStatic

@CompileStatic
class ConnectedStatus implements ServerStatus {
    int i = 0

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        i++
        if (i == 2) {
            first.sendMessage(new ReadyMessage())
            second.sendMessage(new ReadyMessage())
            return new StartedStatus()
        } else {
            return this
        }
    }
}
