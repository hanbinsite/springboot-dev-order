package site.hb.order.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;
import site.hb.order.common.exception.base.CustomException;

@Slf4j
public class OrderIdGeneratorUtils {

    // 每毫秒内序列号位数
    private static final int SEQUENCE_BITS = 12;
    // 机器ID位数（支持4096个节点）
    private static final int WORKER_ID_BITS = 12;
    // 时间戳位数
    private static final int TIMESTAMP_BITS = 40;
    
    // 序列掩码
    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
    // 机器ID左移位数
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    // 时间戳左移位数
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    
    private final long workerId;
    private final AtomicLong sequence = new AtomicLong(0);
    private final ReentrantLock lock = new ReentrantLock();
    private long lastTimestamp = -1L;
 
    // 自定义Base32编码表（去除了容易混淆的字符）
    private static final char[] BASE32_CHARS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
 
    public OrderIdGeneratorUtils(long workerId) {
        if (workerId > (1 << WORKER_ID_BITS) - 1) {
            throw new IllegalArgumentException("Worker ID exceeds maximum value");
        }
        this.workerId = workerId;
    }
 
    public String generateOrderId() {
        long timestamp = System.currentTimeMillis();
        
        lock.lock();
        try {
            if (timestamp < lastTimestamp) {
                throw new CustomException(500, "Clock moved backwards");
            }
            
            if (timestamp == lastTimestamp) {
                sequence.compareAndSet(SEQUENCE_MASK, 0);
            } else {
                sequence.set(0);
            }
            
            lastTimestamp = timestamp;
            
            long orderIdNum = (timestamp << TIMESTAMP_SHIFT) |
                             (workerId << WORKER_ID_SHIFT) |
                             sequence.getAndIncrement();
            
            return base32Encode(orderIdNum);
        } finally {
            lock.unlock();
        }
    }
 
    private String base32Encode(long num) {
        char[] buffer = new char[32];
        int index = buffer.length;
        
        while (num > 0 && index > 0) {
            buffer[--index] = BASE32_CHARS[(int)(num % 32)];
            num /= 32;
        }
        
        // 填充前导字符保证32位长度
        while (index > 0) {
            buffer[--index] = BASE32_CHARS[0];
        }
        
        return new String(buffer);
    }
}
