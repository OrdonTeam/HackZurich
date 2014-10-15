package com.ordonteam.shoutify.gameserver.status
import com.ordonteam.shoutify.gameserver.ObjectSocket
import com.ordonteam.shoutify.gameserver.messages.Message
import groovy.transform.CompileStatic

@CompileStatic
class EndStatus implements ServerStatus{
    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        //Ignoring all incoming messages
        return this
    }
}
