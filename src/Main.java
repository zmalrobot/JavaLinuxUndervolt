public class Main {

    public static void main(String[] args) {


        //Run the ui thread
        Thread runUi = new Thread(new Runnable() {
            @Override
            public void run() {
                Interface ui = new Interface();
                ui.createUi();
            }
        });

        runUi.start();
    }
}
