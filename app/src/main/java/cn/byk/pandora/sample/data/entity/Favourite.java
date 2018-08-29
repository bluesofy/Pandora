package cn.byk.pandora.sample.data.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Byk on 2018/8/27.
 **/
@Entity
public class Favourite {

    @Id
    private long id;

    private String name;

    private String info;

    public Favourite(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Favourite() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
