package com.ordonteam.hackzurich.gameserver.messages

import groovy.transform.CompileStatic

@CompileStatic
class AttackMessage implements Message {

    int chargeStatus

    AttackMessage(int chargeStatus) {
        this.chargeStatus = chargeStatus
    }
}
