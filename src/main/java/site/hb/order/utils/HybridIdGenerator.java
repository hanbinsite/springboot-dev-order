package site.hb.order.utils;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

public class HybridIdGenerator {
    private static final int TIMESTAMP_BITS = 41;
    private static final int NODE_BITS = 13;
    private static final int SEQUENCE_BITS = 10;
    
    private final int nodeId;
    private final SecureRandom secureRandom = new SecureRandom();
    private volatile long lastTimestamp = -1L;
    private volatile int sequence = 0;

    public HybridIdGenerator() throws Exception {
        this.nodeId = MachineUtils.generateWorkerId() & ((1 << NODE_BITS) - 1);
    }

    public synchronized String generate() {
        long timestamp = Instant.now().toEpochMilli();
        
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨异常");
        }
        
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & ((1 << SEQUENCE_BITS) - 1);
            if (sequence == 0) timestamp = tilNextMillis(lastTimestamp);
        } else {
            sequence = secureRandom.nextInt(1 << SEQUENCE_BITS);
        }
        
        lastTimestamp = timestamp;
        
        // 组合各部分数据
        long highBits = (timestamp << (NODE_BITS + SEQUENCE_BITS)) 
                      | (nodeId << SEQUENCE_BITS) 
                      | sequence;
        
        // 转换为Base62混合字符串
        return convertToBase62(highBits & 0x7FFFFFFFFFFFFFFFL);
    }

    private String convertToBase62(long value) {
        String base62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.insert(0, base62.charAt((int)(value % 62)));
            value = value / 62;
        }
        return String.format("%11s", sb).replace(' ', '0'); // 固定11位长度
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = Instant.now().toEpochMilli();
        while (timestamp <= lastTimestamp) {
            timestamp = Instant.now().toEpochMilli();
        }
        return timestamp;
    }

}
