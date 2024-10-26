import java.util.ArrayList;
import java.util.List;

public class Process {
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

    public void recordStatus(String status) {
        timelines.add(status);
    }
}
