package kr.or.teamserver.coinserver.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    // TODO of 네이밍 이유

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
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", registDate='" + registDate + '\'' +
                '}';
    }
}
