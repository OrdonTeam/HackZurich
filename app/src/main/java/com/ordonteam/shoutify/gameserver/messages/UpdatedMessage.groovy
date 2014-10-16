package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class UpdatedMessage implements ServerMessage{
    final int myStatus
    final int opponentStatus

    UpdatedMessage(int myStatus, int opponentStatus) {
        this.opponentStatus = opponentStatus
        this.myStatus = myStatus
    }

    @Override
    void call(ClientCallback clientCallback) {
        clientCallback.onUpdated(myStatus,opponentStatus)
    }
}
