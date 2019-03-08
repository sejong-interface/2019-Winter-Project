package kr.or.teamserver.coinserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "washers")
public class Washer {
    @Id

    @Column
    private Long id;

    @Column
    private long electricPower;

    private LocalDateTime measuredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Washer() {

    }

    public Washer(Long id, long electricPower, LocalDateTime measuredAt, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
        this.id = id;
        this.electricPower = electricPower;
        this.measuredAt = measuredAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Washer of(Long id, long electricPower) {
        LocalDateTime now = LocalDateTime.now();
        return new Washer(id, electricPower, now, now, now);
    }

    public Long getId() {
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
