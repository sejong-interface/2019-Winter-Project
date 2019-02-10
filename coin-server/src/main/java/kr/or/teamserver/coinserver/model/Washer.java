package kr.or.teamserver.coinserver.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Washer {
    private final long id;
    private final long electricPower;
    private final LocalDateTime measuredAt;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Washer(long id, long electricPower, LocalDateTime measuredAt, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
        this.id = id;
        this.electricPower = electricPower;
        this.measuredAt = measuredAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Washer of(long id, long electricPower) {
        LocalDateTime now = LocalDateTime.now();
        return new Washer(id, electricPower, now, now, now);
    }

    public long getId() {
        return id;
    }

    public long getElectricPower() {
        return electricPower;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Washer washer = (Washer) o;
        return id == washer.id &&
                electricPower == washer.electricPower &&
                Objects.equals(measuredAt, washer.measuredAt) &&
                Objects.equals(createdAt, washer.createdAt) &&
                Objects.equals(updatedAt, washer.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, electricPower, measuredAt, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Washer{" +
                "id=" + id +
                ", electricPower=" + electricPower +
                ", measuredAt=" + measuredAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}";
    }
}
