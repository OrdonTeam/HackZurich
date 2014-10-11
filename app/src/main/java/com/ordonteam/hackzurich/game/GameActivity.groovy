package com.ordonteam.hackzurich.game
import android.app.Activity
import android.os.Bundle
import groovy.transform.CompileStatic

@CompileStatic
class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new GameLayout(this))
    }
}
