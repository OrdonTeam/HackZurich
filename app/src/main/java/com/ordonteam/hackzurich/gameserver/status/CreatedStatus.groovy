package com.ordonteam.hackzurich.gameserver.status
import com.ordonteam.hackzurich.gameserver.ObjectSocket
import com.ordonteam.hackzurich.gameserver.messages.ConnectedMessage
import com.ordonteam.hackzurich.gameserver.messages.Message
import groovy.transform.CompileStatic

@CompileStatic
class CreatedStatus implements ServerStatus {

    int i = 0

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        i++
        if (i == 2) {
            first.sendMessage(new ConnectedMessage())
            second.sendMessage(new ConnectedMessage())
        }
        return this
    }
}
