public class BusyThread extends Thread{
    private double load;
    private long duration;

    /**
     * Constructor which creates the thread
     * @param name Name of this thread
     * @param load Load % that this thread should generate
     * @param duration Duration that this thread should generate the load for
     */
    public BusyThread(String name, double load, long duration) {
        super(name);
        this.load = load;
        this.duration = duration;
    }

    /**
     * Generates the load when run
     */
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            // Loop for the given duration
            while (System.currentTimeMillis() - startTime < duration) {
                // Every 100ms, sleep for the percentage of unladen time
                if (System.currentTimeMillis() % 100 == 0) {
                    Thread.sleep((long) Math.floor((1 - load) * 100));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

