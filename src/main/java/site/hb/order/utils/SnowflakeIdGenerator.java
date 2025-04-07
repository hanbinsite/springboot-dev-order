package site.hb.order.utils;

import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SnowflakeIdGenerator {
    // 起始时间戳（2023-01-01 00:00:00 UTC）
    private static final long EPOCH = 1672531200000L;
    
    // 机器ID位数（5位最大31）
    private static final long WORKER_ID_BITS = 5L;
    // 数据中心ID位数（5位最大31）
    private static final long DATA_CENTER_ID_BITS = 5L;
    // 序列号位数（12位最大4095）
    private static final long SEQUENCE_BITS = 12L;

    // 最大机器ID（31）
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    // 最大数据中心ID（31）
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    // 偏移量计算
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    private final long workerId;
    private final long dataCenterId;
    private final AtomicLong sequence = new AtomicLong(0);
    private volatile long lastTimestamp = -1L;

    /**
     * 自动生成workerId和dataCenterId的构造函数
     * 基于主机名的hash code生成
     */
    public SnowflakeIdGenerator() {
        String hostIdentifier = System.getenv().getOrDefault("HOSTNAME", "default");
        long hash = hostIdentifier.hashCode();
        
        this.workerId = (hash & 0x1F) % (MAX_WORKER_ID + 1);
        this.dataCenterId = ((hash >> 5) & 0x1F) % (MAX_DATA_CENTER_ID + 1);
    }

    public synchronized long nextId() {
        long currentTimestamp = timeGen();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards");
        }

        if (currentTimestamp == lastTimestamp) {
            long sequenceValue = (sequence.incrementAndGet() & 0xFFF);
            if (sequenceValue == 0) {
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0);
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence.get();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return Instant.now().toEpochMilli();
    }

    // 新增字符串ID生成方法
    public String nextStringId() {
        return base36Encode(nextId());
    }

    private String base36Encode(long id) {
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        while (id != 0) {
            sb.append(chars[(int) (id % 36)]);
            id /= 36;
        }
        return sb.reverse().toString();
    }

    // 新增混合编码方法
    public String nextCompositeId() {
        long id = nextId();
        Instant instant = Instant.ofEpochMilli(EPOCH + (id >> 22));
        return String.format("%s%04d%03d%04d",
                instant.atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyMMddHHmmss")),
                dataCenterId,
                workerId,
                id & 0xFFF);
    }
}
