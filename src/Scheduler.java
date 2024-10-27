import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Scheduler implements SchedulingAlgorithm {
    protected Queue<Process> readyQueue;
    protected List<Process> allProcesses;

    public Scheduler(List<Process> processes) {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        readyQueue = new LinkedList<>();
        this.allProcesses = processes;
    }

    protected Process getNextProcess() {
        return readyQueue.poll();
    }

    protected void updateProcessStates(List<Process> allProcesses, Process runningProcess) {
        for (Process process : allProcesses) {
            if (process == runningProcess) {
                process.recordRunningStatus();
            } else {
                process.recordNotRunningStatus();
            }
        }
    }

    protected abstract String titleForFinalOutput();

    protected void printFinalOutput(List<Process> allProcesses) {
        System.out.println(titleForFinalOutput());
        for (Process process : allProcesses) {
            System.out.print(process.getPid() + " | ");
            for (String status : process.getTimelines()) {
                System.out.print(status + " ");
            }
            System.out.println();
        }
    }
}
