package com.dungnguyen.utils;



import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class SequenceGenerator {
    private static final int TOTAL_BITS = 64;
    private static final int EPOCH_BITS = 42;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;
    private static final int maxNodeId = (int)(Math.pow(2.0D, 10.0D) - 1.0D);
    private static final int maxSequence = (int)(Math.pow(2.0D, 12.0D) - 1.0D);
    private static final long CUSTOM_EPOCH = 1420070400000L;
    private final int nodeId;
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    public SequenceGenerator(int nodeId) {
        if (nodeId >= 0 && nodeId <= maxNodeId) {
            this.nodeId = nodeId;
        } else {
            throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, maxNodeId));
        }
    }

    public SequenceGenerator() {
        this.nodeId = this.createNodeId();
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();
        if (currentTimestamp < this.lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        } else {
            if (currentTimestamp == this.lastTimestamp) {
                this.sequence = this.sequence + 1L & (long)maxSequence;
                if (this.sequence == 0L) {
                    currentTimestamp = this.waitNextMillis(currentTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = currentTimestamp;
            long id = currentTimestamp << 22;
            id |= ((long) this.nodeId << 12);
            id |= this.sequence;
            String _id = String.valueOf(id).substring(3);
            return Long.parseLong(_id);
        }
    }

    private static long timestamp() {
        return Instant.now().toEpochMilli() - 1420070400000L;
    }

    private long waitNextMillis(long currentTimestamp) {
        while(currentTimestamp == this.lastTimestamp) {
            currentTimestamp = timestamp();
        }

        return currentTimestamp;
    }

    private int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            label29:
            while(true) {
                byte[] mac;
                do {
                    if (!networkInterfaces.hasMoreElements()) {
                        nodeId = sb.toString().hashCode();
                        break label29;
                    }

                    NetworkInterface networkInterface = (NetworkInterface)networkInterfaces.nextElement();
                    mac = networkInterface.getHardwareAddress();
                } while(mac == null);

                for (byte b : mac) {
                    sb.append(String.format("%02X", b));
                }
            }
        } catch (Exception var7) {
            nodeId = (new SecureRandom()).nextInt();
        }

        nodeId &= maxNodeId;
        return nodeId;
    }
}

