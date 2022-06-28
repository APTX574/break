package com.example.demo.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/06/29 00:54
 */
public class Opt 
{
    int id;
    int contractId;
    int optType;
    Date createTime;

    @Override
    public String toString() {
        return "Opt{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", optType=" + optType +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Opt opt = (Opt) o;
        return id == opt.id && contractId == opt.contractId && optType == opt.optType && Objects.equals(createTime, opt.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractId, optType, createTime);
    }

    public int getId() {
        return id;
    }

    public Opt setId(int id) {
        this.id = id;
        return this;
    }

    public int getContractId() {
        return contractId;
    }

    public Opt setContractId(int contractId) {
        this.contractId = contractId;
        return this;

    }

    public int getOptType() {
        return optType;
    }

    public Opt setOptType(int optType) {
        this.optType = optType;
        return this;

    }

    public Date getCreateTime() {
        return createTime;
    }

    public Opt setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;

    }
}
