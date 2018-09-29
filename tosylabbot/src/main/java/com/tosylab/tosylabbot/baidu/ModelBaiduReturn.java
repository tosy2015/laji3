package com.tosylab.tosylabbot.baidu;

import java.util.List;

public class ModelBaiduReturn {
    public static class ModelBaiduData{
        String src;
        String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }

    String from;
    String to;
    List<ModelBaiduData> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<ModelBaiduData> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<ModelBaiduData> trans_result) {
        this.trans_result = trans_result;
    }
}
