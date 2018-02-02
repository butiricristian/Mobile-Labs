package ro.ubb.cristian.examskeletonimproved.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by crist on 01-Feb-18.
 */
@Entity
public class MyItem {
    @PrimaryKey
    private Long id;
    private Integer quantity;
    private String name;
    private String type;

    public MyItem(Long id, Integer quantity, String name, String type) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.type = type;
    }

    @Ignore
    public MyItem(){}

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

    @Override
    public String toString() {
        return "MyItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
