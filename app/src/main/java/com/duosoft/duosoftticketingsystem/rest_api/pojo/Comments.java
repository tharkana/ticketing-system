package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tkodagoda on 7/3/17.
 */

public class Comments  {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("channel_from")
    @Expose
    private String channelFrom;
    @SerializedName("__v")
    @Expose
    private Integer v;

    @Override
    public String toString() {
        return "Comments{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", type='" + type + '\'' +
                ", channelFrom='" + channelFrom + '\'' +
                ", v=" + v +
                ", createdAt='" + createdAt + '\'' +
                ", channel='" + channel + '\'' +
                ", _public='" + _public + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", author=" + author +
                '}';
    }

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("public")
    @Expose
    private String _public;
    @SerializedName("body_type")
    @Expose
    private String bodyType;

    @SerializedName("author")
    @Expose
    private UserDetails author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannelFrom() {
        return channelFrom;
    }

    public void setChannelFrom(String channelFrom) {
        this.channelFrom = channelFrom;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPublic() {
        return _public;
    }

    public void setPublic(String _public) {
        this._public = _public;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }


    public UserDetails getAuthor() {
        return author;
    }

    public void setAuthor(UserDetails author) {
        this.author = author;
    }
}
