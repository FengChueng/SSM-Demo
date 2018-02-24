package com.zyl.ssm.utils;
import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2018-01-19 23:4:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tick {

    private long id;
    private long ts;
    private BigDecimal close;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal amount;
    private BigDecimal count;
    private BigDecimal vol;
    private List<BigDecimal> ask;
    private List<BigDecimal> bid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public BigDecimal getVol() {
		return vol;
	}
	public void setVol(BigDecimal vol) {
		this.vol = vol;
	}
	public List<BigDecimal> getAsk() {
		return ask;
	}
	public void setAsk(List<BigDecimal> ask) {
		this.ask = ask;
	}
	public List<BigDecimal> getBid() {
		return bid;
	}
	public void setBid(List<BigDecimal> bid) {
		this.bid = bid;
	}
}