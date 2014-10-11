package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class ReadyMessage implements Message {
    final String nick

    ReadyMessage(String nick) {
        this.nick = nick
    }
}
