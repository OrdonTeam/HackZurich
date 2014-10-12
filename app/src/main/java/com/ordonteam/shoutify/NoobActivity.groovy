package com.ordonteam.shoutify
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import groovy.transform.CompileStatic

@CompileStatic
class NoobActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        RelativeLayout global = new RelativeLayout(this);
        global.setBackgroundColor(Color.argb(255,255,123,6))

        CenteredLayout centeredLayout = new CenteredLayout(this)

        TextView info = new TextView(this)
        info.setText("""Shout to your phone to charge energy (blue bar) and shake it to attack your opponent. Your health bar is displayed on the left (green bar), your opponent\'s on the right (red bar). First person to loose their health loses. GOOD LUCK!""")

        info.setTextSize(20)
        info.setTextColor(Color.BLACK)
        info.setGravity(Gravity.CENTER_HORIZONTAL);

        centeredLayout.addView(info)

        global.addView(centeredLayout)
        setContentView(global)
    }
}
