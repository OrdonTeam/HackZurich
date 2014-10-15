package com.ordonteam.shoutify.gameserver.messages

import com.ordonteam.shoutify.gameserver.ClientCallback
import groovy.transform.CompileStatic

@CompileStatic
class LooseMessage implements ServerMessage{
    @Override
    void call(ClientCallback clientCallback) {
        clientCallback.onLoose()
    }
}
