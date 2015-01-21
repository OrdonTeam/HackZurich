package com.ordonteam.shoutify.waiting

import java.util.regex.Pattern

import static java.net.NetworkInterface.getNetworkInterfaces

class IpUtil {
    static Pattern ipAddressPattern = ~/\d+.\d+.\d+.\d+/

    static Closure<Boolean> isProperExternalIp = { String host ->
        host ==~ ipAddressPattern && host != '127.0.0.1'
    }

    static List<String> getExternalIps(){
        return networkInterfaces*.interfaceAddresses.flatten()*.address*.hostAddress.findAll(isProperExternalIp)
    }

    static String getIpAddress() {
        List<String> ips = externalIps
        return ips.isEmpty() ? 'xxx.xxx.xxx.xxx' : ips.get(0)
    }

    static String getPartialIp() {
        return "${ipAddress.split('\\.').take(3).join('.')}."
    }
}
