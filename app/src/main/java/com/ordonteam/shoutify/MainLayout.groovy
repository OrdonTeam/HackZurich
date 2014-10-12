package com.ordonteam.shoutify
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
import com.ordonteam.shoutify.mode.ModeSelectorActivity
import com.ordonteam.shoutify.util.FileUtil
import com.ordonteam.shoutify.util.MP3Util
import com.ordonteam.shoutify.util.ViewUtil
import groovy.transform.CompileStatic

@CompileStatic
class MainLayout extends CenteredLayout implements TextWatcher {

    TextView titleView;
    EditText editText;
    Button button;
    Button button2;

    MainLayout(Activity activity) {
        super(activity)

        setBackgroundColor(Color.argb(255,255,123,6))
        setPadding(20)

        titleView = new TextView(activity)
        titleView.setText("Your name:")
        titleView.setGravity(Gravity.CENTER_HORIZONTAL)
        titleView.setTextSize(20)
        titleView.setTextColor(Color.BLACK)
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
            MP3Util.playSword(activity)
            Intent intent = new Intent(activity, ModeSelectorActivity.class)
            FileUtil.saveNick(activity,editText.getText().toString())
            activity.startActivity(intent)
        })
        addView(button)

        button2 = new Button(activity)
        button2.setText('Help')
        button2.setTextSize(20)
        button2.setOnClickListener({
            Intent intent = new Intent(activity, NoobActivity.class)
            activity.startActivity(intent)
        })
        addView(button2)

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
