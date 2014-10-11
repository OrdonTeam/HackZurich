package com.ordonteam.hackzurich

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import com.ordonteam.hackzurich.util.MainLayout
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL)

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1)

        MainLayout content = new MainLayout(this)
        content.setLayoutParams(layoutParams)
        linearLayout.addView(content)

        CenteredLayout spacing = new CenteredLayout(this)
        spacing.setLayoutParams(layoutParams)
        linearLayout.addView(spacing)

        setContentView(linearLayout)
    }
}