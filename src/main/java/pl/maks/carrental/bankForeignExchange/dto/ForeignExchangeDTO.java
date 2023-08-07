package pl.maks.carrental.bankForeignExchange.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ForeignExchangeDTO {

    private Integer r030;
    private String txt;
    private BigDecimal rate;
    private String cc;
    private Date exchangeDate;

    public ForeignExchangeDTO(Integer r030, String txt, BigDecimal rate, String cc, Date exchangeDate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangeDate = exchangeDate;
    }


    public Integer getR030() {
        return r030;
    }

    public void setR030(Integer r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}