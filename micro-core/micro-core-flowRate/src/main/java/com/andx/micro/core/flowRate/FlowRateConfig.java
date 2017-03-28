package com.andx.micro.core.flowRate;

/**
 * Created by andongxu on 16-12-20.
 */
public class FlowRateConfig {
    /**
     * 流速总开关
     */
    private boolean flowRateSwitch = false;

    /**
     * 并发控制子开关
     */
    private boolean ccSwitch = false;

    /**
     * 最大并发数
     */
    private int maxConcurrentCount = 20;

    /**
     * QPS子开关
     */
    private boolean qpsSwitch = false;
    /**
     * 最大QPS数
     */
    private int maxQpsCount = 20;

    public boolean isFlowRateSwitch() {
        return flowRateSwitch;
    }

    public void setFlowRateSwitch(boolean flowRateSwitch) {
        this.flowRateSwitch = flowRateSwitch;
    }

    public boolean isCcSwitch() {
        return ccSwitch;
    }

    public void setCcSwitch(boolean ccSwitch) {
        this.ccSwitch = ccSwitch;
    }

    public int getMaxConcurrentCount() {
        return maxConcurrentCount;
    }

    public void setMaxConcurrentCount(int maxConcurrentCount) {
        this.maxConcurrentCount = maxConcurrentCount;
    }

    public boolean isQpsSwitch() {
        return qpsSwitch;
    }

    public void setQpsSwitch(boolean qpsSwitch) {
        this.qpsSwitch = qpsSwitch;
    }

    public int getMaxQpsCount() {
        return maxQpsCount;
    }

    public void setMaxQpsCount(int maxQpsCount) {
        this.maxQpsCount = maxQpsCount;
    }
}
