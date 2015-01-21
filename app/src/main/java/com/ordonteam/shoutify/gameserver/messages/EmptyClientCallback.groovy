package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class EmptyClientCallback implements ClientCallback{
    @Override
    void onConnected() {

    }

    @Override
    void onStarted(String otherPlayerName) {

    }

    @Override
    void onUpdated(int myStatus, int opponentStatus) {

    }

    @Override
    void onWin() {

    }

    @Override
    void onLoose() {

    }

    @Override
    void onDisconnect() {

    }
}
