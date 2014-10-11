package com.ordonteam.shoutify.gameserver

public interface ClientCallback {

    void onConnected()

    void onStarted(String otherPlayerName)

    void onUpdated(int myStatus,int opponentStatus)

    void onWin()

    void onLoose()

    void onDisconnect()
}