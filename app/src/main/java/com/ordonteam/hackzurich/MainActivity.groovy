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
import com.ordonteam.hackzurich.mode.CreateGameSelectLayout
import com.ordonteam.hackzurich.mode.JoinGameSelectLayout
import com.ordonteam.hackzurich.mode.ModeSelectorActivity
import com.ordonteam.hackzurich.util.ButtonTextLayout
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {
//implements DialogInterface.OnKeyListener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL)
   

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1)

        ButtonTextLayout content = new ButtonTextLayout(this)
        content.setLayoutParams(layoutParams)
        linearLayout.addView(content)

        CenteredLayout layout2 = new CenteredLayout(this)
        layout2.setLayoutParams(layoutParams)
        linearLayout.addView(layout2)

        setContentView(linearLayout)
    }
}