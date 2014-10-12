package com.ordonteam.shoutify.mode

import android.app.Activity
import android.os.Bundle
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorActivity extends Activity {

    ModeSelectorLayout msl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        msl = new ModeSelectorLayout(this)
        setContentView(msl)
    }

    @Override
    public void onResume(){
        super.onResume()
        msl.createGameLayout.createGameButton.setEnabled(true)
        msl.joinGameLayout.joinGame.setEnabled(true)
    }
}
