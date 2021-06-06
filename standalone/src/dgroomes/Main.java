package dgroomes;

public class Main {

    public static void main(String[] args) {
        var joined = String.join(", ", args);
        var msg = String.format("Program was invoked with args: %s", joined);
        System.out.println(msg);
    }
}
