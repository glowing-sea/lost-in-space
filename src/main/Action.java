package src.main;

// A class that manages all key inputs
public class Action {
    private State st;
    private String input;

    public Action(State st, String input) {
        this.st = st;
        this.input = input;
    }

    public State getSt() {
        return st;
    }

    public String getInput() {
        return input;
    }

    public void setSt(State st) {
        this.st = st;
    }

    public void setInput(String input) {
        this.input = input;
    }


}
