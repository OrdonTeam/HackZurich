package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.os.Bundle
import groovy.transform.CompileStatic

@CompileStatic
class JoinGameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new GameLayout(this))
    }
}
