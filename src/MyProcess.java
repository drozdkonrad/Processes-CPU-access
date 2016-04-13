import java.util.Comparator;

public class MyProcess  implements Cloneable{

    int id;
    int durationTime;
    int arrivalTime;
    int waitingTime;

    public MyProcess(int id, int durationTime, int arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.durationTime = durationTime;
        waitingTime = 0;
    }

    public static Comparator<MyProcess> DurationTimeComp = new Comparator<MyProcess>(){
        public int compare(MyProcess p1, MyProcess p2){
            return p1.durationTime < p2.durationTime ? -1 : p1.durationTime > p2.durationTime ? 1 : 0;
        }
    };

    public static Comparator<MyProcess> ArrivalTimeComp = new Comparator<MyProcess>(){
        public int compare(MyProcess p1, MyProcess p2){
            return p1.arrivalTime < p2.arrivalTime ? -1 : p1.arrivalTime > p2.arrivalTime ? 1 : 0;
        }
    };

    @Override
    public MyProcess clone(){
        try{
            return (MyProcess)super.clone();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%-10d %-10d %-10d", id, arrivalTime, durationTime);
    }
}
