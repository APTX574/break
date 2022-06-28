package com.example.demo.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/06/26 21:47
 */
public class Contract {
    int id;
    String userName;
    String outLevel;
    String reason;
    String seriousness;
    String beiZhu;
    String firmPeople;
    int status;
    Date createTime;

    @Override
    public String

    toString() {
        return "Contract{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", outLevel='" + outLevel + '\'' +
                ", reason='" + reason + '\'' +
                ", seriousness='" + seriousness + '\'' +
                ", beiZhu='" + beiZhu + '\'' +
                ", firmPeople='" + firmPeople + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id && status == contract.status && Objects.equals(userName, contract.userName) && Objects.equals(outLevel, contract.outLevel) && Objects.equals(reason, contract.reason) && Objects.equals(seriousness, contract.seriousness) && Objects.equals(beiZhu, contract.beiZhu) && Objects.equals(firmPeople, contract.firmPeople) && Objects.equals(createTime, contract.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, outLevel, reason, seriousness, beiZhu, firmPeople, status, createTime);
    }

    public int getId() {
        return id;
    }

    public Contract setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Contract setUserName(String userName) {
        this.userName = userName;
        return this;

    }

    public String getOutLevel() {
        return outLevel;
    }

    public Contract setOutLevel(String outLevel) {
        this.outLevel = outLevel;
        return this;

    }

    public String getReason() {
        return reason;
    }

    public Contract setReason(String reason) {
        this.reason = reason;
        return this;

    }

    public String getSeriousness() {
        return seriousness;
    }

    public Contract setSeriousness(String seriousness) {
        this.seriousness = seriousness;
        return this;

    }

    public String getBeiZhu() {
        return beiZhu;
    }

    public Contract setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
        return this;

    }

    public String getFirmPeople() {
        return firmPeople;
    }

    public Contract setFirmPeople(String firmPeople) {
        this.firmPeople = firmPeople;
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
