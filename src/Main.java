public class Main {

    public static void main(String[] args) {

        //TODO
        // 1 - Avviare con o senza ui a senconda di un paramentro
        // 2 - Vedi gli altri file
        // 3 - Quello che riesci in tryicon

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
