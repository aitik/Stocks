package sample;

public class Person {

    private String name = null;
    private String shares = null;
    private int cash=0;

    public Person() {
    }

    public Person(String name, int cash, String shares) {
        this.name = name;
        this.cash = cash;
        this.shares = shares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }
}
