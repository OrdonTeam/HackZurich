package com.ordonteam.hackzurich.gameserver.messages

import com.ordonteam.hackzurich.gameserver.messages.Message
import groovy.transform.CompileStatic

@CompileStatic
class UpdatedMessage implements Message{
    final int myStatus
    final int opponentStatus

    UpdatedMessage(int opponentStatus, int myStatus) {
        this.opponentStatus = opponentStatus
        this.myStatus = myStatus
    }
}
