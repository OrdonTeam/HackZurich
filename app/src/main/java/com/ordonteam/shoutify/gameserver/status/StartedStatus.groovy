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
    int player1 = 100
    int player2 = 100

    StartedStatus() {
        Log.e('StartedStatus','Game Started')
    }

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        Log.e('StartedStatus',"receiveMessage ${message.getClass()}")
        if (message instanceof AttackMessage) {
            if (isFirst) {
                player2 -= 10
            } else {
                player1 -= 10
            }
            first.sendMessage(new UpdatedMessage(player1, player2))
            second.sendMessage(new UpdatedMessage(player2, player1))
            if (player1 <= 0) {
                first.sendMessage(new LooseMessage())
                second.sendMessage(new WinMessage())
                return new EndStatus()
            } else if (player2 <= 0) {
                first.sendMessage(new WinMessage())
                second.sendMessage(new LooseMessage())
                return new EndStatus()
            }
            return this
        } else {
            throw new RuntimeException("Server received unexpected message ${message.getClass()}")
        }
    }
}
