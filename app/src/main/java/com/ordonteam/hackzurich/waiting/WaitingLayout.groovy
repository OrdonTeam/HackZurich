package com.ordonteam.hackzurich.waiting
import android.content.Context
import android.widget.TextView
import com.ordonteam.hackzurich.CenteredLayout
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class WaitingLayout extends CenteredLayout {

    WaitingLayout(Context context) {
        super(context)

        TextView titleView1 = new TextView(context)
        titleView1.setText('Waiting')
        addView(titleView1)

        TextView titleView2 = new TextView(context)
        titleView2.setText('for second player.')
        addView(titleView2)

        TextView titleView3 = new TextView(context)
        titleView3.setText('')
        addView(titleView3)

        TextView titleView4 = new TextView(context)
        titleView4.setText('Show to your friend:')
        addView(titleView4)

        TextView titleView5 = new TextView(context)
        titleView5.setText(showIps())
        addView(titleView5)
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    private String showIps() {
        List<String> flatten = NetworkInterface.getNetworkInterfaces().collect { NetworkInterface ni ->
            ni.inetAddresses.collect { InetAddress ia ->
                ia.getHostAddress()
            }
        }.flatten().findAll { String host ->
            host ==~ ~/\d+.\d+.\d+.\d+/ && host != '127.0.0.1'
        }

        return flatten.isEmpty() ? 'xxx.xxx.xxx.xxx' : flatten.get(0)
    }
}
