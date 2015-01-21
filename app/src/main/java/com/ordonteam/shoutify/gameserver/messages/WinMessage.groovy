package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class WinMessage implements ServerMessage {

    @Override
    void call(ClientCallback clientCallback) {
        clientCallback.onWin()
    }
}
