package cn.vlinker.daylog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auth_code", schema = "public", catalog = "day_log")
public class AuthCode {
    private String uuid;
    private String code;
    private String mac;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthCode authCode = (AuthCode) o;
        return Objects.equals(uuid, authCode.uuid) &&
                Objects.equals(code, authCode.code) &&
                Objects.equals(mac, authCode.mac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, code, mac);
    }

    @Override
    public String toString() {
        return "AuthCode{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
