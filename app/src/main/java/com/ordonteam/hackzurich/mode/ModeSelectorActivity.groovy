package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.os.Bundle
import com.ordonteam.hackzurich.mode.ModeSelectorLayout
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new ModeSelectorLayout(this))
        Serializable nick = getIntent().getExtras().getSerializable('nick')
    }
}
