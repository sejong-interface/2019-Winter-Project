package kr.or.teamserver.coinserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String token;

    @Column(length = 100, nullable = false)
    private String date;

    public Device() {

    }

    public Device(String token, String date) {
        this.token = token;
        this.date = date;
    }

    @JsonCreator
    @PersistenceConstructor
    public Device(@JsonProperty("id") Long id, @JsonProperty("token") String token, @JsonProperty("date") String date){
        this.id = id;
        this.token = token;
        this.date = date;
    }

    public static Device of(String token, String date){
        return new Device(null, token, date);
    }

    public static Device of(Long id, String token, String date){
        return new Device(id, token, date);
    }

    public Long getId() {
        return id;
    }
    public String getToken() {
        return token;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) &&
                Objects.equals(token, device.token) &&
                Objects.equals(date, device.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, date);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
