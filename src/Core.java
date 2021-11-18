public class Core {
    private Job job;
    private enum PowerState {
        /** The core is actively processing. */
        ACTIVE,
        /** Transitioning to park. */
        TRANSITIONINGG_TO_LOW_POWER_IDLE,
        /** Transitioning to active from park. */
        TRANSITIONINGG_TO_ACTIVE,
        /** The core is in the "halt" mode (idle). */
        HALT,
        /** The core is in park mode.*/
        LOW_POWER_IDLE
    };

    public static enum CorePowerPolicy {
        NO_MANAGEMENT,
        CORE_PARKING
    };

    private PowerState powerState;
    private CorePowerPolicy powerPolicy;
    private double speed;
    private Experiment experiment;
    private Socket socket;

    private double dynamicPower;
    private double parkPower;
    private double idlePower;

    public Core() {
        this.job = null;
        this.powerState = PowerState.HALT;
        this.powerPolicy = CorePowerPolicy.NO_MANAGEMENT;

        this.speed = 1.0;

        dynamicPower = 40.0 * (4.0 / 5.0) / 2;
        parkPower = 0;
        idlePower = dynamicPower / 5.0;
    }

    public void print(){
        if(this.job!=null) {
            this.job.print();
        } else{
            System.out.println("No job in this core!");
        }
    }

    public void assignJob(final double time,Job onejob){
        if (this.job != null) {
            System.out.println("Already has a job!");
        }else{
            this.job=onejob;
            double startTime=time;
            job.markStart(startTime);
        }
    }

    public void removeJob(final double time, final Job oneJob){
        if (this.job != null) {
            System.out.println("Error!No job in this core!");
        }else{
            double finishTime=time+this.job.getSize()/this.speed;
            job.markFinish(finishTime);
            this.job=null;
        }
    }

    public void process(double time){
        if (this.job != null){
            int currentProcess=0;
            while(currentProcess<job.getSize()){
                currentProcess+=this.speed;
            }
            if(currentProcess==job.getSize()){
                this.job.markFinish(time+currentProcess*this.speed);
                this.job.print();
                System.out.println("Job "+this.job.getJobId()+" is finished.");
                this.job=null;
            }
        }
    }

    public Job getJob(){
        return this.job;
    }


    public static void main(String[] args) {
        Core core= new Core();
        Job job=new Job(5);
        long time=System.currentTimeMillis()/1000;
        core.assignJob(time,job);
        core.process(time);
    }

}
