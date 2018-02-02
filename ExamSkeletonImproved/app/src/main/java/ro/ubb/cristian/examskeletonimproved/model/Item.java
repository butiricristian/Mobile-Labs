package ro.ubb.cristian.examskeletonimproved.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by crist on 28-Jan-18.
 */
@Entity
public class Item implements Serializable{
    @PrimaryKey
    private Long id;
    private Integer quantity;
    private String name;
    private String type;
    private String status;

    public Item(Long id, Integer quantity, String name, String type, String status) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    @Ignore
    public Item(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        return "Item{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
