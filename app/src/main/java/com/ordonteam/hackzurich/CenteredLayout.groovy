package com.ordonteam.hackzurich;
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import groovy.transform.CompileStatic

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import static android.widget.LinearLayout.VERTICAL

@CompileStatic
public class CenteredLayout extends RelativeLayout {

    private LinearLayout centered;

    public CenteredLayout(Context context) {
        super(context);
        setLayoutParams(new RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        setVerticalGravity(CENTER_VERTICAL);
        centered = new LinearLayout(context);
        def layoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT, TRUE);
        centered.setLayoutParams(layoutParams);
        centered.setOrientation(VERTICAL);
        super.addView(centered);
    }

    @Override
    public void addView(View child) {
        centered.addView(child);
    }

    public void setOrientation(int orientation) {
        centered.setOrientation(orientation);
    }
}
