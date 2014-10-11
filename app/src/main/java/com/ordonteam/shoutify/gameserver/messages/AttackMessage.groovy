package com.ordonteam.shoutify.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class AttackMessage implements Message {

    int chargeStatus

    AttackMessage(int chargeStatus) {
        this.chargeStatus = chargeStatus
    }
}
