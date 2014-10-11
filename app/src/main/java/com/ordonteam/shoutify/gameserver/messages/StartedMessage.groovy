package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class StartedMessage implements Message{
    String otherPlayerName

    StartedMessage(String otherPlayerName) {
        this.otherPlayerName = otherPlayerName
    }
}
