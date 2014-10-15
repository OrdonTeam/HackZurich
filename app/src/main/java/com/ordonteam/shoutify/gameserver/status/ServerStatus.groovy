package com.ordonteam.shoutify.gameserver.status
import com.ordonteam.shoutify.gameserver.ObjectSocket
import com.ordonteam.shoutify.gameserver.messages.Message

interface ServerStatus {

    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second)
}