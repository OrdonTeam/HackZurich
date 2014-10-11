package com.ordonteam.hackzurich.gameserver.status

import android.util.Log
import com.ordonteam.hackzurich.gameserver.ObjectSocket
import com.ordonteam.hackzurich.gameserver.messages.*
import groovy.transform.CompileStatic

@CompileStatic
class StartedStatus implements ServerStatus{
    int player1 = 100
    int player2 = 100

    @Override
    ServerStatus receiveMessage(Message message, boolean isFirst, ObjectSocket first, ObjectSocket second) {
        if(message instanceof AttackMessage){
            Log.e('StartedStatus','attack')
            if(isFirst){
                player2-10
            }else{
                player1-10
            }
            first.sendMessage(new UpdatedMessage(player1,player2))
            second.sendMessage(new UpdatedMessage(player2,player1))
        }
        if(player1 <= 0){
            first.sendMessage(new LooseMessage())
            second.sendMessage(new WinMessage())
        }else if(player2 <= 0){
            first.sendMessage(new WinMessage())
            second.sendMessage(new LooseMessage())
        }
        return this
    }
}