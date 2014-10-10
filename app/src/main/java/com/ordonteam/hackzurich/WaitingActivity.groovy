package com.ordonteam.hackzurich

import android.app.Activity
import android.os.Bundle
import com.ordonteam.hackzurich.waiting.WaitingLayout
import groovy.transform.CompileStatic

@CompileStatic
class WaitingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(new WaitingLayout(applicationContext))
    }
}
