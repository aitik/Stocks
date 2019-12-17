package sample;

public class Table {
    private String name;
    private int cash;
    private String shares;
    public Table(){

    }
    public Table(String name, int cash, String shares)
    {
        this.name = name;
        this.cash = cash;
        this.shares = shares;
    }

    public String getName()
    {
        return name;
    }
    public int getCash()
    {
        return cash;
    }
    public String getShares()
    {
        return shares;
    }
}
