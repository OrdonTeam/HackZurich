package com.ordonteam.hackzurich

import android.app.Activity
import android.os.Bundle
import groovy.transform.CompileStatic

@CompileStatic
class ModeSelectorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new ModeSelectorLayout(this))
    }
}
