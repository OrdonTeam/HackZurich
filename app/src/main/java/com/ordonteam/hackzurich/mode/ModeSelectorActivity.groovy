package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.os.Bundle
import com.ordonteam.hackzurich.mode.ModeSelectorLayout
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorActivity extends Activity {

    ModeSelectorLayout msl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        msl = new ModeSelectorLayout(this)
        setContentView(msl)
        Serializable nick = getIntent().getExtras().getSerializable('nick')
    }

    @Override
    public void onResume(){
        super.onResume()
        msl.createGameLayout.createGameButton.setEnabled(true)
    }
}
