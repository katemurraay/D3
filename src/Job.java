public class Job {
    private double arrivalTime;
    private double startTime;
    private double finishTime;
    private long jobId;
    private double jobSize;
    private static long currentId;

    public void print(){
        System.out.println("jobId:"+jobId);
        System.out.println("jobSize:"+jobSize);
        System.out.println("arrivalTime:"+arrivalTime);
        System.out.println("startTime:"+startTime);
        System.out.println("finishTime:"+finishTime);
    }

    private long assignId() {
        long toReturn = Job.currentId;
        Job.currentId++;
        return toReturn;
    }

    //constructor
    public Job(final double theJobSize) {
        this.jobSize = theJobSize;
        this.jobId = assignId();
    }

    public final void markArrival(final double time) {
        this.arrivalTime = time;
    }

    public final void markStart(final double time) {
        this.startTime = time;
    }

    public final void markFinish(final double time) {
        this.finishTime = time;
    }

    public final double getFinishTime() {
        return this.finishTime;
    }

    public final double getSize() {
        return this.jobSize;
    }

    public final double getJobId() {return this.jobId;}
}
