package dev.skyrat.ccsecurity.peripheral.entitydetector;

public final class CCSEntity {
    public final double distance;
    public final boolean isPlayer;
    public final boolean isLiving;

    public CCSEntity(double dist, boolean isPly, boolean isLiving) {
        this.distance = dist;
        this.isPlayer = isPly;
        this.isLiving = isLiving;
    }
}
