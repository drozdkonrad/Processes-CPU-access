import java.util.ArrayList;

public class FCFS {

    double averageWaitingTime;

    public FCFS(MyProcess[] processes) {
        int currentTime = 0;
        double totalWaitingTime = 0;

        ArrayList<MyProcess> queue = new ArrayList<>();

        for(int i = 0 ;i < processes.length || !queue.isEmpty(); currentTime++) {
            while(i < processes.length && processes[i].arrivalTime == currentTime) {
                queue.add(processes[i]);
                i++;
            }

            if (!queue.isEmpty()) {
                MyProcess process = queue.remove(0);
                process.durationTime--;

                if(process.durationTime == 0) {
                    process.waitingTime = currentTime - process.durationTime - process.arrivalTime;
                    totalWaitingTime += process.waitingTime;
                }else
                    queue.add(0, process);

            }
        }
        averageWaitingTime = totalWaitingTime/processes.length;
    }

}
