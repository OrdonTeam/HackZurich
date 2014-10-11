package com.ordonteam.shoutify.game

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import groovy.transform.CompileStatic

@CompileStatic
class GameEndedDialog extends DialogFragment implements DialogInterface.OnClickListener {
    Activity activity
    String message
    GameEndedDialog(Activity activity, String message){
        this.activity = activity
        this.message = message
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
        builder.setMessage(message)
        builder.setPositiveButton("OK",this)
        return builder.create()
    }

    @Override
    void onClick(DialogInterface dialogInterface, int i) {
        activity.finish()
    }
}
