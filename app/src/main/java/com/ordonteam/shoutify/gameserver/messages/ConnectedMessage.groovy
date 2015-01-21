package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class ConnectedMessage implements ServerMessage {
    @Override
    void call(ClientCallback clientCallback) {
        clientCallback.onConnected()
    }
}
