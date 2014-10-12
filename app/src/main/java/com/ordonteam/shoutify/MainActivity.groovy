package com.ordonteam.shoutify

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.ordonteam.hackzurich.R
import com.ordonteam.shoutify.util.ViewUtil
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        RelativeLayout global = new RelativeLayout(this);
        global.setBackgroundColor(Color.argb(255,255,123,6))

        CenteredLayout centeredLayout = new CenteredLayout(this);
        centeredLayout.setOrientation(LinearLayout.VERTICAL)
        centeredLayout.setBackgroundColor(Color.argb(255,255,123,6))

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1)
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewUtil.dpAsPixels(250,getResources()), ViewUtil.dpAsPixels(250,getResources()), 1)

        LinearLayout imageLayout = new LinearLayout(this)
        imageLayout.setOrientation(LinearLayout.VERTICAL)
        imageLayout.setLayoutParams(imageParams)
        Drawable img = getResources().getDrawable(R.drawable.mainlogo)
        ImageView imageView = new ImageView(this)
        imageView.setImageDrawable(img)
        imageLayout.addView(imageView)
        centeredLayout.addView(imageLayout)

        MainLayout content = new MainLayout(this)
        content.setLayoutParams(layoutParams)
        centeredLayout.addView(content)
        global.addView(centeredLayout)
        setContentView(global)
    }
}