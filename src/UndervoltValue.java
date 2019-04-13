import java.util.HashMap;

public class UndervoltValue {

    public UndervoltValue(){ }

    public HashMap<String, Integer> getValue(){
        HashMap<String, Integer> returnValue = new HashMap<String, Integer>();

        returnValue.put("cpu", 0);
        returnValue.put("cache", 0);
        returnValue.put("gpu", 0);
        returnValue.put("uncore", 0);
        returnValue.put("analogio", 150);

        return returnValue;
    }

    public boolean setValue(int core, int gpu, int cache, int uncore, int analogio){

        return false;
    }


}
