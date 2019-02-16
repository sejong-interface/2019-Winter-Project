package kr.or.teamserver.coinserver.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String token;

    @Column(length = 100, nullable = false)
    private String date;

    public Device(){
        this.id = null;
        this.token = null;
        this.date = null;
    }

    public Device(Long id, String token, String date){
        this.id = id;
        this.token = token;
        this.date = date;
    }

    public Device(String token, String date){
        this(null,token,date);
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
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
