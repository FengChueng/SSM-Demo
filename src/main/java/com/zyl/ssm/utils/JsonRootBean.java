package com.zyl.ssm.utils;
import java.util.List;

/**
 * Auto-generated: 2018-01-19 22:58:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String status;
    private String ch;
    private long ts;
    private List<Data> data;
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

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

}