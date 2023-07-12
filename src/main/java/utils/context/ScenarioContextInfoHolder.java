package utils.context;

import java.util.HashMap;

public class ScenarioContextInfoHolder {
    private HashMap<String, Object> data = new HashMap<>();

    public ScenarioContextInfoHolder() {
    }

    public <T> T getScenarioContext(String key) {
        return (T) data.get(key);
    }

    public void setScenarioContext(String key, Object data) {
        this.data.put(key, data);
    }
}
