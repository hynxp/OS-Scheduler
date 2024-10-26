import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FIFOScheduler {
    private Queue<Process> readyQueue;
    private Queue<Process> waitQueue;

    public FIFOScheduler(List<Process> processes) {
        //도착 시간순으로 정렬
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        readyQueue = new LinkedList<>();
        waitQueue = new LinkedList<>(processes);
    }

    public void run() {
        int currentTime = 0;
        Process currentProcess = null;
        List<Process> allProcesses = new LinkedList<>(waitQueue); //실행중이지 않은 프로세스들의 상태값 기록을 위한 변수

        while (!readyQueue.isEmpty() || !waitQueue.isEmpty() || (currentProcess != null && currentProcess.getRemainingTime() > 0)) {
            //waitQueue에서 readyQueue로 이동
            moveToReadyQueue(currentTime);

            //시간 순으로 정렬돼있는 대로 다음 프로세스 실행
            if (currentProcess == null || currentProcess.getRemainingTime() == 0) {
                currentProcess = getNextProcess();
            }

            //프로세스 상태 기록
            updateProcessStates(allProcesses, currentProcess);

            currentTime++;
            if (currentProcess != null) {
                currentProcess.decrementRemainingTime();
            }
        }

        //최종 상태 출력
        printFinalOutput(allProcesses);
    }

    private void moveToReadyQueue(int currentTime) {
        while (!waitQueue.isEmpty() && waitQueue.peek().getArrivalTime() <= currentTime) {
            Process arrivedProcess = waitQueue.poll();
            readyQueue.add(arrivedProcess);
        }
    }

    private Process getNextProcess() {
        return readyQueue.poll();
    }

    private void updateProcessStates(List<Process> allProcesses, Process runningProcess) {
        for (Process process : allProcesses) {
            if (process == runningProcess) {
                process.recordStatus("■");
            } else {
                process.recordStatus("□");
            }
        }
    }

    private void printFinalOutput(List<Process> allProcesses) {
        for (Process process : allProcesses) {
            System.out.print(process.getPid() + " | ");
            for (String status : process.getTimelines()) {
                System.out.print(status + " ");
            }
            System.out.println();
        }
    }
}
