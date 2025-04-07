package site.hb.order.utils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import io.netty.util.internal.ThreadLocalRandom;

public class MachineUtils {

    public static int generateWorkerId() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            byte[] mac = ni.getHardwareAddress();
            if (mac != null) 
                return (mac[4] & 0xFF) | ((mac[5] & 0xFF) << 8); // 取MAC地址后两字节
        }
        return ThreadLocalRandom.current().nextInt(0, 8192); // 降级方案
    }

}
