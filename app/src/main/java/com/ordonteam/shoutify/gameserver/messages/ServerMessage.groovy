package com.ordonteam.shoutify.gameserver.messages

import com.ordonteam.shoutify.gameserver.ClientCallback

public interface ServerMessage extends Message {
    void call(ClientCallback clientCallback)
}