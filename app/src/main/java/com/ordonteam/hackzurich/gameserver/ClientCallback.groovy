package com.ordonteam.hackzurich.gameserver

public interface ClientCallback {

    void onConnected()

    void onStarted()

    void onUpdated()

    void onWin()

    void onLoose()
}