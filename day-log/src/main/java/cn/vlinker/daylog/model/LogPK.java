package cn.vlinker.daylog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class LogPK implements Serializable {
    private LocalDate day;
    private String name;

    @Column(name = "day")
    @Id
    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    @Column(name = "name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogPK logPK = (LogPK) o;
        return Objects.equals(day, logPK.day) &&
                Objects.equals(name, logPK.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, name);
    }
}
