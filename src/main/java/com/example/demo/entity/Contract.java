package com.example.demo.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/06/26 21:47
 */
public class Contract {
    int id;
    String title;
    String reason;
    int type;
    int status;
    Date createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contract contract = (Contract) o;
        return id == contract.id && type == contract.type && status == contract.status && Objects.equals(title, contract.title) && Objects.equals(reason, contract.reason) && Objects.equals(createTime, contract.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, reason, type, status, createTime);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", reason='" + reason + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public Contract setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Contract setTitle(String title) {
        this.title = title;
        return this;

    }

    public String getReason() {
        return reason;
    }

    public Contract setReason(String reason) {
        this.reason = reason;
        return this;

    }

    public int getType() {
        return type;
    }

    public Contract setType(int type) {
        this.type = type;
        return this;

    }

    public int getStatus() {
        return status;
    }

    public Contract setStatus(int status) {
        this.status = status;
        return this;

    }

    public Date getCreateTime() {
        return createTime;
    }

    public Contract setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;

    }
}
