package com.ordonteam.hackzurich.util

import android.app.Activity
import android.content.Intent
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
class MainLayout extends CenteredLayout implements TextWatcher {

    TextView titleView;
    EditText editText;
    Button button;

    MainLayout(Activity activity) {
        super(activity)

        titleView = new TextView(activity)
        titleView.setText("Your name:")
        titleView.setGravity(Gravity.CENTER_HORIZONTAL)
        addView(titleView)

        editText = new EditText(activity)
        editText.setLayoutParams(new LinearLayout.LayoutParams(ViewUtil.dpAsPixels(200, getResources()), LinearLayout.LayoutParams.WRAP_CONTENT))
        editText.addTextChangedListener(this);
        addView(editText)

        button = new Button(activity)
        button.setText('Next')
        button.setOnClickListener({
            Intent intent = new Intent(activity, ModeSelectorActivity.class)
            intent.putExtra("nick", editText.getText().toString());
            activity.startActivity(intent)
        })
        button.setEnabled(false);
        addView(button)
    }

    public void afterTextChanged(Editable s) {
        if (editText.text.toString()?.length() == 0) {
            button.setEnabled(false);
        } else {
            button.setEnabled(true);
        }
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
