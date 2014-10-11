package com.ordonteam.hackzurich
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.ordonteam.hackzurich.mode.ModeSelectorActivity
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {
//implements DialogInterface.OnKeyListener

    TextView titleView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)

        CenteredLayout centeredLayout = new CenteredLayout(this)
        centeredLayout.setOrientation(LinearLayout.VERTICAL);
        centeredLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT))

        titleView = new TextView(this)
        titleView.setText("Your name:")
        titleView.setGravity(Gravity.CENTER_HORIZONTAL)
        centeredLayout.addView(titleView)

        button = new Button(this)
        button.setText('Next')
        button.setOnClickListener({
            Intent intent = new Intent(this, ModeSelectorActivity.class)
            intent.putExtra("nick", editText.text);
            startActivity(intent)
        })
        button.setEnabled(false);

        editText = new EditText(this)
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (200*scale + 0.5f);
        editText.setLayoutParams(new LinearLayout.LayoutParams(dpAsPixels, LinearLayout.LayoutParams.WRAP_CONTENT))
        centeredLayout.addView(editText)
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



        centeredLayout.addView(button)

        setContentView(centeredLayout)
    }
}