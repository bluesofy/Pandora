package cn.byk.pandora.sample.data.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.NameInDb;
import io.objectbox.annotation.Transient;
import io.objectbox.relation.ToMany;

/**
 * Created by Byk on 2018/8/26.
 **/
@Entity
public class User {

    @Id
    private long id;

    @Index
    private int uid;

    @NameInDb("name")
    private String name;

    @Transient
    private String desc;

    private ToMany<Favourite> favos;

    public User(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ToMany<Favourite> getFavos() {
        return favos;
    }

    public void setFavos(ToMany<Favourite> favos) {
        this.favos = favos;
    }
}
