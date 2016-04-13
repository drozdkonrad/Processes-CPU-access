import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Test {

    static int seriesNumber = 50;
    static int processesNumber = 50;
    static int maxDurationTime = 200;
    static int minDurationTime = 1;
    static int maxArrivalTime = 30;
    static int minArrivalTime = 1;
    static int timeQuantum = 300;


    public static void main(String[] args) {
        
        PrintWriter cout = new PrintWriter(System.out, true);
        double FCFS_TotalTime = 0;
        double SJF_TotalTime = 0;
        double SJFzW_TotalTime = 0;
        double ROT_TotalTime = 0;
        
        inputCheck();
        for (int i = 0; i < seriesNumber; i++) {
            MyProcess[] array = makeArray();

            FCFS fcfs = new FCFS(copy(array));
            FCFS_TotalTime += fcfs.averageWaitingTime;

            SJF sjf = new SJF(copy(array));
            SJF_TotalTime += sjf.averageWaitingTime;

            SJFzW sjfzw = new SJFzW(copy(array));
            SJFzW_TotalTime += sjfzw.averageWaitingTime;

            ROT rot = new ROT(copy(array), timeQuantum);
            ROT_TotalTime += rot.averageWaitingTime;

            //writeArray(copy(array));
        }
        
        cout.println("Liczba serii: " + seriesNumber + "\nLiczba procesów: " + processesNumber + "\n");
        cout.println("Sredni czas oczekiwania: ");
        cout.printf("%-6s %-4.2f\n", "FCFS:", FCFS_TotalTime/seriesNumber);
        cout.printf("%-6s %-4.2f\n", "SJF:", SJF_TotalTime/seriesNumber);
        cout.printf("%-6s %-4.2f\n", "SJFzW:", SJFzW_TotalTime/seriesNumber);
        cout.printf("%-6s %-4.2f\n", "ROT:", ROT_TotalTime/seriesNumber);

    }

    static MyProcess[] copy(MyProcess[] array){
        MyProcess[] result = new MyProcess[array.length];
        for (int i = 0; i < result.length; i++){
            result[i] = array[i].clone();
        }
        return result;
    }

    static MyProcess[] makeArray() {
        MyProcess[] array = new MyProcess[processesNumber];
        Random rand = new Random();
        for(int i = 0; i < array.length; i++) {
            array[i] = new MyProcess(i, rand.nextInt(maxDurationTime-minDurationTime)+minDurationTime, rand.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime);
        }
        Arrays.sort(array, MyProcess.ArrivalTimeComp);
        return array;
    }

    static void writeArray(MyProcess[] array) {
        PrintWriter cout = new PrintWriter(System.out, true);
        cout.printf("%-10s %-10s %-10s\n", "ID", "cz. wejscia", "cz. trwania");
        for(MyProcess p: array) {
            cout.println(p);
        }

    }

    static void inputCheck() {
        if(seriesNumber <= 0) throw new IllegalArgumentException("seriesNumber must not be less or equal 0");
        if(processesNumber <= 0) throw new IllegalArgumentException("processesNumber must not be less or equal 0");
        if(maxDurationTime - minDurationTime <= 0) throw new IllegalArgumentException("DurationTime must not be less than 0");
        if(minArrivalTime < 0) throw new IllegalArgumentException("arrivalTime must not be less than 0");
        if(timeQuantum < 0) throw new IllegalArgumentException("timeQuantum must not be less than 0");
    }

}
