package com.ordonteam.shoutify.gameserver.messages

public interface ServerMessage extends Message {
    void call(ClientCallback clientCallback)
}