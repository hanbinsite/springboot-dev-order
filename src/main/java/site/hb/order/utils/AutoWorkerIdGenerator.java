package site.hb.order.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.springframework.stereotype.Component;


@Component
public class AutoWorkerIdGenerator {

    // ID 结构：时间戳(41位) + 自动节点ID(16位) + 序列号(7位)
    private static final long TIMESTAMP_BITS = 41L;
    private static final long NODE_ID_BITS = 16L;
    private static final long SEQUENCE_BITS = 7L;
    
    private static final long MAX_NODE_ID = ~(-1L << NODE_ID_BITS);
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    
    // 起始时间戳（2024-01-01）
    private static final long EPOCH = 1704067200000L;
    
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;
    private final long nodeId;

    public AutoWorkerIdGenerator() {
        this.nodeId = generateNodeId();
        if (nodeId > MAX_NODE_ID) {
            throw new IllegalStateException("Node ID 超出最大值：" + MAX_NODE_ID);
        }
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨异常，拒绝生成ID");
        }
        
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        
        lastTimestamp = timestamp;
        
        return ((timestamp - EPOCH) << (NODE_ID_BITS + SEQUENCE_BITS))
                | (nodeId << SEQUENCE_BITS)
                | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 自动生成节点ID（核心优化点）
     * 组合策略：IP地址哈希(24位) + PID(16位) + 随机数(8位)
     */
    private long generateNodeId() {
        try {
            // 1. 获取本机IP地址哈希（24位）
            int ipHash = getLocalIp().hashCode();
            
            // 2. 获取进程ID（16位）
            long pid = getPid() & 0xFFFF;
            
            // 3. 生成随机数（8位）
            int random = (int) (Math.random() * 0xFF);
            
            // 组合成48位，取低16位
            long combined = ((ipHash & 0xFFFFFFL) << 24) 
                          | (pid << 8) 
                          | random;
            
            return combined & MAX_NODE_ID;
        } catch (Exception e) {
            throw new RuntimeException("生成节点ID失败", e);
        }
    }

    /**
     * 获取本机有效IP地址
     */
    private String getLocalIp() throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isLoopback() || !iface.isUp()) continue;
            
            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                String ip = addr.getHostAddress();
                if (ip.contains(":")) continue; // 跳过IPv6
                if (ip.startsWith("127.")) continue;
                if (ip.startsWith("169.254")) continue;
                return ip;
            }
        }
        throw new RuntimeException("未找到有效IP地址");
    }

    /**
     * 获取当前进程PID（兼容不同JVM实现）
     */
    private long getPid() {
        try {
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            return Long.parseLong(pid);
        } catch (Exception e) {
            return Thread.currentThread().getId(); // 退化为线程ID
        }
    }

}
