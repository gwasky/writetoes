package org.cs;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Gibson on 1/28/20.
 */
public class Record {

    private String transactionDate;
    private String transactionNumber;
    private String trnType;
    private String memo = "";
    private String srcDeviceNumber;
    private String trgDeviceNumber;
    private String status;
    private Double grossAmount;
    private Double feeAmount;;
    private Double srcEndBalance;
    private Double tgtEndBalance;


    public Record(String transactionDate, String transactionNumber, String trnType, String memo, String srcDeviceNumber, String trgDeviceNumber, String status, Double grossAmount, Double feeAmount,Double srcEndBalance, Double tgtEndBalance) {
        this.transactionDate = transactionDate;
        this.transactionNumber = transactionNumber;
        this.trnType = trnType;
        if (memo != null ) {
            this.memo = memo;
        }
        this.srcDeviceNumber = srcDeviceNumber;
        this.trgDeviceNumber = trgDeviceNumber;
        this.status = status;
        this.grossAmount = grossAmount;
        this.feeAmount = feeAmount;
        this.srcEndBalance = srcEndBalance;
        this.tgtEndBalance = tgtEndBalance;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTrnType() {
        return trnType;
    }

    public void setTrnType(String trnType) {
        this.trnType = trnType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSrcDeviceNumber() {
        return srcDeviceNumber;
    }

    public void setSrcDeviceNumber(String srcDeviceNumber) {
        this.srcDeviceNumber = srcDeviceNumber;
    }

    public String getTrgDeviceNumber() {
        return trgDeviceNumber;
    }

    public void setTrgDeviceNumber(String trgDeviceNumber) {
        this.trgDeviceNumber = trgDeviceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Double getSrcEndBalance() {
        return srcEndBalance;
    }

    public void setSrcEndBalance(Double srcEndBalance) {
        this.srcEndBalance = srcEndBalance;
    }

    public Double getTgtEndBalance() {
        return tgtEndBalance;
    }

    public void setTgtEndBalance(Double tgtEndBalance) {
        this.tgtEndBalance = tgtEndBalance;
    }

    @Override
    public String toString() {
        return "Record{" +
                "transactionDate='" + transactionDate + '\'' +
                ", transactionNumber='" + transactionNumber + '\'' +
                ", trnType='" + trnType + '\'' +
                ", memo='" + memo + '\'' +
                ", srcDeviceNumber='" + srcDeviceNumber + '\'' +
                ", trgDeviceNumber='" + trgDeviceNumber + '\'' +
                ", status='" + status + '\'' +
                ", grossAmount='" + grossAmount + '\'' +
                ", feeAmount='" + feeAmount + '\'' +
                ", srcEndBalance='" + srcEndBalance + '\'' +
                ", tgtEndBalance='" + tgtEndBalance + '\'' +
                '}';
    }
}
