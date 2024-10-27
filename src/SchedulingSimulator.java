import java.util.ArrayList;
import java.util.List;

public class SchedulingSimulator {

    private static final String[] processNames = {"A", "B", "C", "D", "E"};

    public static void main(String[] args) {
        // FIFO 스케줄러 실행
        SchedulingAlgorithm fifo = new FIFOScheduler(createProcesses());
        SchedulingAlgorithm rr1 = new RoundRobinScheduler(createProcesses(), 1);
        SchedulingAlgorithm rr4 = new RoundRobinScheduler(createProcesses(), 4);
        runScheduler(fifo);
        runScheduler(rr1);
        runScheduler(rr4);

//        Scanner sc = new Scanner(System.in);
//        System.out.println("5개 프로세스의 arrival time, service time을 입력해 주세요. ex) 0,3");
//        for (int i = 0; i < 5; i++) {
//            String[] split = sc.nextLine().split(",");
//            int arrivalTime = Integer.parseInt(split[0]);
//            int serviceTime = Integer.parseInt(split[1]);
//
//            Process process = new Process(processNames[i], arrivalTime, serviceTime);
//            processes.add(process);
//        }
    }

    private static List<Process> createProcesses() {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("A", 0, 3));
        processes.add(new Process("B", 2, 6));
        processes.add(new Process("C", 4, 4));
        processes.add(new Process("D", 6, 5));
        processes.add(new Process("E", 8, 2));
        return processes;
    }

    public static void runScheduler(SchedulingAlgorithm scheduler) {
        scheduler.run();  // 어떤 스케줄러든 동일한 방식으로 실행 가능
    }
}
