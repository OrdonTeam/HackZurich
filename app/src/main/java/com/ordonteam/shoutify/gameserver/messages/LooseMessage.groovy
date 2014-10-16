package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class LooseMessage implements ServerMessage{
    @Override
    void call(ClientCallback clientCallback) {
        clientCallback.onLoose()
    }
}
