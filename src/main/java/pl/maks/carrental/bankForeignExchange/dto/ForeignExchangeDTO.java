package pl.maks.carrental.bankForeignExchange.dto;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;


public class ForeignExchangeDTO{

    private Integer r030;
    private String txt;
    private BigDecimal rate;
    private String cc;
    private Data exchangeDate;

    public ForeignExchangeDTO(String cc, Integer r030, Date exchangeDate, BigDecimal rate, String txt) {

    }

    public ForeignExchangeDTO(String cc, int r030, String s, String s1, String currencyNotFound) {

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

    public Data getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Data exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}