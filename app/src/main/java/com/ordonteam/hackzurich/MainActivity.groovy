package com.ordonteam.hackzurich
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.ordonteam.hackzurich.mode.ModeSelectorActivity
import com.ordonteam.hackzurich.util.ButtonTextLayout
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {
//implements DialogInterface.OnKeyListener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        ButtonTextLayout layout = new ButtonTextLayout(this)
        setContentView(layout)
    }
}