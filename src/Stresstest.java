public class Stresstest {
    /**
     * Starts the Load Generation
     *
     * @param args Command line arguments, ignored
     */

    private int numCore;
    private int numThreadsPerCore;
    private double load;
    private long duration;

    public Stresstest() {
        numCore           = 1;
        numThreadsPerCore = 2;
        load              = 0.5;
        duration          = 60;
    }

    public void setStress(int numCore, int numThreadsPerCore, double load, long duration){
        this.numCore           = numCore;
        this.numThreadsPerCore = numThreadsPerCore;
        this.load              = load;
        this.duration          = duration;
    }

    public void setStressMaxCore(long duration){

        // get the runtime object associated with the current Java application
        Runtime runtime = Runtime.getRuntime();
        // get the number of processors available to the Java virtual machine
        int numberOfProcessors = runtime.availableProcessors();

        this.numCore           = numberOfProcessors;
        this.numThreadsPerCore = numberOfProcessors;
        this.load              = 1.0;
        this.duration          = duration;
    }

    public void startStress(){
        System.out.println("Stress: cpu: "+ numCore+ " ThreadPerCore: "+ numThreadsPerCore+ " Duration: " + duration);
        for (int thread = 0; thread < numCore * numThreadsPerCore; thread++) {
            new BusyThread("Thread" + thread, load, duration).start();
        }
    }
}


