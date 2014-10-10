package com.ordonteam.hackzurich.waiting

import android.content.Context
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import groovy.transform.CompileStatic

@CompileStatic
class WaitingLayout extends CenteredLayout {

    WaitingLayout(Context context) {
        super(context)

        TextView titleView1 = new TextView(context)
        titleView1.setText("Waiting")
        addView(titleView1)

        TextView titleView2 = new TextView(context)
        titleView2.setText("for other")
        addView(titleView2)

        TextView titleView3 = new TextView(context)
        titleView3.setText("player")
        addView(titleView3)

        NetworkInterface.getNetworkInterfaces()

        TextView titleView4 = new TextView(context)
        titleView4.setText("jakis ip")
        addView(titleView4)
    }
}
