package cn.byk.pandora.libs.base.bean;

import okhttp3.MediaType;

/**
 * Created by Byk on 2018/8/14.
 **/
public class FileBean {

    private String key;

    private String path;

    private String fileName;

    private MediaType mediaType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
