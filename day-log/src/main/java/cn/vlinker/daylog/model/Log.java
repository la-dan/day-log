package cn.vlinker.daylog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "log", schema = "public", catalog = "day_log")
@IdClass(LogPK.class)
public class Log {
    private LocalDate day;
    private String name;
    private String project;
    private String work;

    @Id
    @Column(name = "day")
    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "project")
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Basic
    @Column(name = "work")
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(day, log.day) &&
                Objects.equals(name, log.name) &&
                Objects.equals(project, log.project) &&
                Objects.equals(work, log.work);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, name, project, work);
    }
}
