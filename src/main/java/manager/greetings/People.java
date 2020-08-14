package manager.greetings;

public class People {
    private String name = "";
    private int id = 0;
    private int counter = 0;
    private int rowsCount = 0;

    public People(int id, String name, int counter,int rowsCount) {
        this.id = id;
        this.name = name;
        this.counter = counter;
        this.rowsCount = rowsCount;
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

    public int getRowsCount() {
        return rowsCount;
    }
}
