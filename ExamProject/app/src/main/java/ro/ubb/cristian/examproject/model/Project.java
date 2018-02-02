package ro.ubb.cristian.examproject.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by crist on 28-Jan-18.
 */
@Entity
public class Project implements Serializable{
    @PrimaryKey
    private Long id;
    private Integer budget;
    private String name;
    private String type;
    private String status;

    public Project(Long id, Integer budget, String name, String type, String status) {
        this.id = id;
        this.budget = budget;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    @Ignore
    public Project(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", budget=" + budget +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
