package com.ordonteam.hackzurich.gameserver.status

import com.ordonteam.hackzurich.gameserver.ObjectSocket
import com.ordonteam.hackzurich.gameserver.messages.Message

interface ServerStatus {

    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second)
}