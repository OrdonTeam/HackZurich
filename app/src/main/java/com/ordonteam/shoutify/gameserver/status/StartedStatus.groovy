package com.ordonteam.shoutify.gameserver.status
import android.util.Log
import com.ordonteam.shoutify.gameserver.ObjectSocket
import com.ordonteam.shoutify.gameserver.messages.AttackMessage
import com.ordonteam.shoutify.gameserver.messages.LooseMessage
import com.ordonteam.shoutify.gameserver.messages.Message
import com.ordonteam.shoutify.gameserver.messages.UpdatedMessage
import com.ordonteam.shoutify.gameserver.messages.WinMessage
import groovy.transform.CompileStatic

@CompileStatic
class StartedStatus implements ServerStatus {
    double player1 = 100
    double player2 = 100

    StartedStatus() {
        Log.d('StartedStatus','Game Started')
    }

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        Log.d('StartedStatus',"receiveMessage ${message.getClass()}")
        if (message instanceof AttackMessage) {
            return playerAttacked(message, isFirst, first, second)
        } else {
            throw new RuntimeException("Server received unexpected message ${message.getClass()}")
        }
    }

    private ServerStatus playerAttacked(AttackMessage attackMessage, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        dealDamage(attackMessage, isFirst)
        first.sendMessage(new UpdatedMessage((int) player1, (int) player2))
        second.sendMessage(new UpdatedMessage((int) player2, (int) player1))
        return checkGameStatus(first, second)
    }

    private void dealDamage(AttackMessage attackMessage, boolean isFirst) {
        int dmg = attackMessage.chargeStatus
        double dmgDealt = dmg * dmg * dmg * 1.7 / 100000
        if (isFirst) {
            player2 -= dmgDealt
        } else {
            player1 -= dmgDealt
        }
    }

    private ServerStatus checkGameStatus(ObjectSocket first, ObjectSocket second) {
        if (player1 <= 0) {
            first.sendMessage(new LooseMessage())
            second.sendMessage(new WinMessage())
            return new EndStatus()
        } else if (player2 <= 0) {
            first.sendMessage(new WinMessage())
            second.sendMessage(new LooseMessage())
            return new EndStatus()
        } else {
            return this
        }
    }
}
