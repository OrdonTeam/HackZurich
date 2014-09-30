package com.ordonteam.hackzurich

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        def layout = new Button(this)
        layout.setText('Hello Zurich')
        setContentView(layout);
    }
}