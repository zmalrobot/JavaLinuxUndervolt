public class Main {

    public static void main(String[] args) {

        //TODO
        // 1: When i save over another save it will double the file extension
        // 2: Clean the code
        // 3: Create a better installer
        // 4: Create a better launcher


        //Anti aliasing for the font
        System.setProperty("awt.useSystemAAFontSettings","on");

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
