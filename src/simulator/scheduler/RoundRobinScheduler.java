package simulator.scheduler;

import simulator.domain.Process;

import java.util.List;

public class RoundRobinScheduler extends Scheduler {

    private final int timeSlice;

    public RoundRobinScheduler(List<Process> processes, int timeSlice) {
        super(processes);
        this.timeSlice = timeSlice;
    }

    @Override
    public void run() {
        int currentTime = 0;

        moveToReadyQueue(currentTime);

        while (!readyQueue.isEmpty()) {
            //readyQueue에서 실행할 프로세스 가져오기
            Process currentProcess = getNextProcess();

            //ts만큼 실행하기
            for (int i = 0; i < timeSlice; i++) {
                if (currentProcess != null) {
                    currentProcess.decrementRemainingTime(); //현재 프로세스 실행 시간 차감
                    updateProcessStates(allProcesses, currentProcess); //전체 프로세스 타임라인 기록
                    currentTime++; // 현재 시간 증가
                    moveToReadyQueue(currentTime); //현재 시간에 들어온 프로세스 readyQueue로 이동

                    //실행 시간 0이 되면 현재 프로세스 실행 중지
                    if (currentProcess.getRemainingTime() == 0) {
                        break;
                    }
                }
            }

            //만약 timeslice만큼 실행하고도, 현재 프로세스의 service time이 남았다면 readyQueue의 tail로 이동
            if (currentProcess.getRemainingTime() > 0) {
                readyQueue.add(currentProcess);
            }
        }

        printFinalOutput(allProcesses);
    }

    private void moveToReadyQueue(int currentTime) {
        for (Process process : allProcesses) {
            if (process.getArrivalTime() == currentTime && !readyQueue.contains(process)) {
                readyQueue.add(process);
            }
        }
    }

    @Override
    protected String titleForFinalOutput() {
        return "RR: ts" + timeSlice;
    }
}
