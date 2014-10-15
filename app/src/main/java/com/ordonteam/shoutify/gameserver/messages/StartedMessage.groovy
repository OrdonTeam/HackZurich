package com.ordonteam.shoutify.gameserver.messages

import com.ordonteam.shoutify.gameserver.ClientCallback
import groovy.transform.CompileStatic

@CompileStatic
class StartedMessage implements ServerMessage{
    String otherPlayerName

    StartedMessage(String otherPlayerName) {
        this.otherPlayerName = otherPlayerName
    }

    @Override
    void call(ClientCallback clientCallback) {
        clientCallback.onStarted(otherPlayerName)
    }
}
