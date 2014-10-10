package com.ordonteam.hackzurich

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)

        CenteredLayout centeredLayout = new CenteredLayout(this)
        centeredLayout.setOrientation(LinearLayout.VERTICAL);
        centeredLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT))

        TextView titleView = new TextView(this)
        titleView.setText("Your name:")
        centeredLayout.addView(titleView)

        EditText editText = new EditText(this)
        centeredLayout.addView(editText)

        Button button = new Button(this)
        button.setText('Next')
        button.setOnClickListener({
            Intent intent = new Intent(this, ModeSelectorActivity.class)
            startActivity(intent)
        })

        centeredLayout.addView(button)

        setContentView(centeredLayout)
    }
}