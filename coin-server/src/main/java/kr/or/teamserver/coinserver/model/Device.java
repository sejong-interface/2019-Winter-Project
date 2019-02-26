package kr.or.teamserver.coinserver.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String token;

    @Column(length = 100, nullable = false)
    private LocalDateTime registDate;

    public Device(){

    }

    @PersistenceConstructor
    public Device(Long id, String token, LocalDateTime registDate){
        this.id = id;
        this.token = token;
        this.registDate = registDate;
    }

    public static Device of(String token){
        return new Device(null, token, LocalDateTime.now());
    }

    public static Device of(Long id, String token){
        return new Device(id, token, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getRegistDate() {
        return registDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) &&
                Objects.equals(token, device.token) &&
                Objects.equals(registDate, device.registDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, registDate);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", registDate='" + registDate + '\'' +
                '}';
    }
}
