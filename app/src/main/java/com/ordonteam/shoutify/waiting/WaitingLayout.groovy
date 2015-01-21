package com.ordonteam.shoutify.waiting
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import com.ordonteam.shoutify.CenteredLayout
import groovy.transform.CompileStatic

@CompileStatic
class WaitingLayout extends CenteredLayout {

    WaitingLayout (Context context) {
        super(context)
        setPadding(20)
        setBackgroundColor(Color.argb(255,0,157,71))

        TextView titleView1 = new TextView(context)
        titleView1.setText('Waiting')
        titleView1.setTextSize(20)
        titleView1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        addView(titleView1)

        TextView titleView2 = new TextView(context)
        titleView2.setText('for second player.')
        titleView2.setTextSize(20)
        titleView2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        addView(titleView2)

        TextView titleView3 = new TextView(context)
        titleView3.setText('')
        titleView3.setTextSize(20)
        titleView3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        addView(titleView3)

        TextView titleView4 = new TextView(context)
        titleView4.setText('Show to your friend:')
        titleView4.setTextSize(20)
        titleView4.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        addView(titleView4)

        TextView titleView5 = new TextView(context)
        titleView5.setText(IpUtil.getIpAddress())
        titleView5.setTextSize(20)
        titleView5.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        addView(titleView5)
    }
}
