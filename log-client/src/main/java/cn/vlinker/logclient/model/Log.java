package cn.vlinker.logclient.model;

import java.time.LocalDate;
import java.util.Objects;

public class Log {
    private LocalDate day;
    private String name;
    private String project;
    private String work;
    
    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

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
