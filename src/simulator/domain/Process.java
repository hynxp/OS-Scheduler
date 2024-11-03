package simulator.domain;

import java.util.ArrayList;
import java.util.List;

public class Process {
    public static final String RUNNING_STATE = "■";
    public static final String NOT_RUNNING_STATE = "□";

    private String pid;
    private int arrivalTime;
    private int serviceTime;
    private int remainingTime;
    private List<String> timelines;

    public Process(String pid, int arrivalTime, int serviceTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.remainingTime = serviceTime;
        this.timelines = new ArrayList<>();
    }

    public String getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void decrementRemainingTime() {
        this.remainingTime--;
    }

    public List<String> getTimelines() {
        return timelines;
    }

    public void recordRunningStatus() {
        timelines.add(RUNNING_STATE);
    }

    public void recordNotRunningStatus() {
        timelines.add(NOT_RUNNING_STATE);
    }

    @Override
    public String toString() {
        return "simulator.domain.Process{" +
                "pid='" + pid + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                ", remainingTime=" + remainingTime +
                ", timelines=" + timelines +
                '}' + '\n';
    }
}
