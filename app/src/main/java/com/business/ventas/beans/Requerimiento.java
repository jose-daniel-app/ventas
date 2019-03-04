package com.business.ventas.beans;

import java.util.Date;

public class Requerimiento {

    private String name;
    private String status;
    private Date scheduleDate;
    private Date transactionDate;
    private String title;

    public Requerimiento() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    @Override
    public String toString() {
        return "Requerimiento{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", scheduleDate=" + scheduleDate +
                ", transactionDate=" + transactionDate +
                ", title='" + title + '\'' +
                '}';
    }
}
