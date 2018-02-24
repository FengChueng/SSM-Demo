package com.zyl.ssm.utils;

public class MergeData {

   private String status;
   private String ch;
   private long ts;
   private Tick tick;
   public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

   public void setCh(String ch) {
        this.ch = ch;
    }
    public String getCh() {
        return ch;
    }

   public void setTs(long ts) {
        this.ts = ts;
    }
    public long getTs() {
        return ts;
    }

   public void setTick(Tick tick) {
        this.tick = tick;
    }
    public Tick getTick() {
        return tick;
    }

}
