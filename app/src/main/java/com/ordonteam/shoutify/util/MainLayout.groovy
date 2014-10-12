package com.ordonteam.shoutify.util

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.ordonteam.shoutify.CenteredLayout
import com.ordonteam.shoutify.mode.ModeSelectorActivity
import groovy.transform.CompileStatic

@CompileStatic
class MainLayout extends CenteredLayout implements TextWatcher {

    TextView titleView;
    EditText editText;
    Button button;

    MainLayout(Activity activity) {
        super(activity)

        setBackgroundColor(Color.argb(255,69,97,157))
        setPadding(20)

        titleView = new TextView(activity)
        titleView.setText("Your name:")
        titleView.setGravity(Gravity.CENTER_HORIZONTAL)
        titleView.setTextSize(20)
        addView(titleView)

        editText = new EditText(activity)
        editText.setLayoutParams(new LinearLayout.LayoutParams(ViewUtil.dpAsPixels(200, getResources()), LinearLayout.LayoutParams.WRAP_CONTENT))
        editText.setText(FileUtil.readNick(activity))
        editText.addTextChangedListener(this);
        addView(editText)

        button = new Button(activity)
        button.setText('Play')
        button.setTextSize(20)
        button.setOnClickListener({
            Intent intent = new Intent(activity, ModeSelectorActivity.class)
//            intent.putExtra("nick", editText.getText().toString());
            FileUtil.saveNick(activity,editText.getText().toString())
            activity.startActivity(intent)
        })
        //button.setLayoutParams(par)
        addView(button);
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
