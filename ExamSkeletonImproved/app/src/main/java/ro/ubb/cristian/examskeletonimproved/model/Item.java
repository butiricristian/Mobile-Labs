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
    private Integer intField1;
    private String stringField1;
    private String stringField2;
    private String stringField3;

    public Item(Long id, Integer intField1, String stringField1, String stringField2, String stringField3) {
        this.id = id;
        this.intField1 = intField1;
        this.stringField1 = stringField1;
        this.stringField2 = stringField2;
        this.stringField3 = stringField3;
    }

    @Ignore
    public Item(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIntField1() {
        return intField1;
    }

    public void setIntField1(Integer intField1) {
        this.intField1 = intField1;
    }

    public String getStringField1() {
        return stringField1;
    }

    public void setStringField1(String stringField1) {
        this.stringField1 = stringField1;
    }

    public String getStringField2() {
        return stringField2;
    }

    public void setStringField2(String stringField2) {
        this.stringField2 = stringField2;
    }

    public String getStringField3() {
        return stringField3;
    }

    public void setStringField3(String stringField3) {
        this.stringField3 = stringField3;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", intField1=" + intField1 +
                ", stringField1='" + stringField1 + '\'' +
                ", stringField2='" + stringField2 + '\'' +
                ", stringField3='" + stringField3 + '\'' +
                '}';
    }
}
