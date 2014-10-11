package com.ordonteam.hackzurich.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import com.ordonteam.hackzurich.mode.ModeSelectorActivity
import groovy.transform.CompileStatic

@CompileStatic
class ButtonTextLayout extends CenteredLayout {

    TextView titleView;
    EditText editText;
    Button button;

    ButtonTextLayout(Activity activity) {
        super(activity)
        titleView = new TextView(activity)
        titleView.setText("Your name:")
        titleView.setGravity(Gravity.CENTER_HORIZONTAL)

        button = new Button(activity)
        button.setText('Next')
        button.setOnClickListener({
            Intent intent = new Intent(activity, ModeSelectorActivity.class)
            intent.putExtra("nick", editText.text);
            activity.startActivity(intent)
        })
        button.setEnabled(false);

        editText = new EditText(activity)
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (200*scale + 0.5f);
        editText.setLayoutParams(new LinearLayout.LayoutParams(dpAsPixels, LinearLayout.LayoutParams.WRAP_CONTENT))
        editText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                if (editText.text.toString()?.length() == 0){
                    button.setEnabled(false);
                }
                else{
                    button.setEnabled(true);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        addView(titleView)
        addView(editText)
        addView(button)
    }
}
