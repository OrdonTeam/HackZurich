package com.ordonteam.shoutify.game
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import groovy.transform.CompileStatic

@CompileStatic
class GameEndedDialog implements DialogInterface.OnClickListener {
    Activity activity
    String message

    GameEndedDialog(Activity activity) {
        this.activity = activity
    }

    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
        builder.setMessage(message)
        builder.setPositiveButton("OK", this)
        builder.create().show()
    }

    @Override
    void onClick(DialogInterface dialogInterface, int i) {
        activity.finish()
    }
}
