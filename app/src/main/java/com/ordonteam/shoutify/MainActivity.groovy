package com.ordonteam.shoutify

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.ordonteam.hackzurich.R
import com.ordonteam.shoutify.util.MainLayout
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL)
        linearLayout.setBackgroundColor(Color.argb(255,255,123,6))

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1)

        LinearLayout imageLayout = new LinearLayout(this)
        imageLayout.setOrientation(LinearLayout.VERTICAL)
        imageLayout.setLayoutParams(layoutParams)
        Drawable img = getResources().getDrawable(R.drawable.mainlogo)
        ImageView imageView = new ImageView(this)
        imageView.setImageDrawable(img)
        imageLayout.addView(imageView)
        linearLayout.addView(imageLayout)

        MainLayout content = new MainLayout(this)
        content.setLayoutParams(layoutParams)
        linearLayout.addView(content)

        setContentView(linearLayout)
    }
}