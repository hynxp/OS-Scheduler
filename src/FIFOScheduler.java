import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FIFOScheduler extends Scheduler {
    private final Queue<Process> waitQueue;

    public FIFOScheduler(List<Process> processes) {
        super(processes);
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
            if (currentProcess != null) {
                currentProcess.decrementRemainingTime();
            }
            updateProcessStates(allProcesses, currentProcess);

            currentTime++;
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

    @Override
    protected String titleForFinalOutput() {
        return "FIFO:";
    }
}
