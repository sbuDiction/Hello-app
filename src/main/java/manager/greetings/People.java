package manager.greetings;

public class People {
    private String name;
    private int id;
    private int counter;
//    private String timeStamp;

    public People(int id, String name, int counter) {
        this.id = id;
        this.name = name;
        this.counter = counter;
//        this.timeStamp = timeStamp;
    }

    public int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public String getTimeStamp() {
////        return timeStamp;
//    }
}
