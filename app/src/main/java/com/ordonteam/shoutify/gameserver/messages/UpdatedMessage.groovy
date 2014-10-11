package com.ordonteam.shoutify.gameserver.messages

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
