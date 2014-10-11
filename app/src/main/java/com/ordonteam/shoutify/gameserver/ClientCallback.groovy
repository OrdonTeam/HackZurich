package com.ordonteam.shoutify.gameserver

public interface ClientCallback {

    void onConnected()

    void onStarted()

    void onUpdated(int myStatus,int opponentStatus)

    void onWin()

    void onLoose()

    void onDisconnect()
}