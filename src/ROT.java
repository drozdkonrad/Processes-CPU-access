import java.util.ArrayList;


public class ROT {

    int timeQuantum;
    double averageWaitingTime;

    public ROT(MyProcess[] processes, int timeQ) {
        this.timeQuantum = timeQ;
        int currentTimeQuantum = timeQuantum;
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
                currentTimeQuantum--;

                if(process.durationTime == 0) {
                    process.waitingTime = currentTime - process.durationTime - process.arrivalTime;
                    totalWaitingTime += process.waitingTime;
                    currentTimeQuantum = timeQuantum;
                }else {
                    if (currentTimeQuantum > 0)
                        queue.add(0, process);
                    else {
                        queue.add(process);
                        currentTimeQuantum = timeQuantum;
                    }
                }

            }
        }
        averageWaitingTime = totalWaitingTime/processes.length;
    }

}