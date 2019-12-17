package sample;
import com.healthmarketscience.jackcess.*;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.Database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class Players{
    ArrayList<String>names = new ArrayList<>();
    ArrayList<Integer>cash = new ArrayList<>();
    ArrayList<String>shares = new ArrayList<>();
    public void getnames(){
        try{
            File file = new File("src\\Database.accdb");
            Database db = DatabaseBuilder.open(file);
            com.healthmarketscience.jackcess.Table table = db.getTable("Players");
            //Cursor cursor = CursorBuilder.createCursor(table);
            for(Row row : table) {
                names.add(String.valueOf(row.get("Name")));
            }
            System.out.println(names);
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
    }
    public void getmoney(){
        try{
            File file = new File("src\\Database.accdb");
            Database db = DatabaseBuilder.open(file);
            com.healthmarketscience.jackcess.Table table = db.getTable("Players");
            //Cursor cursor = CursorBuilder.createCursor(table);
            for(Row row : table) {
                cash.add((int)(row.get("Cash")));
            }
            System.out.println(cash);
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
    }
    public void getshares(){
        try{
            File file = new File("src\\Database.accdb");
            Database db = DatabaseBuilder.open(file);
            com.healthmarketscience.jackcess.Table table = db.getTable("Players");
            //Cursor cursor = CursorBuilder.createCursor(table);
            for(Row row : table) {
                if(row.get("Shares")==null){
                    shares.add("");
                }
                else{
                    shares.add(String.valueOf(row.get("Shares")));
                }
            }
            System.out.println(shares);
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
    }
    public void addtodatabasename(String name){
        try{
            File file = new File("src\\Database.accdb");
            Database db = DatabaseBuilder.open(file);
            com.healthmarketscience.jackcess.Table table = db.getTable("Players");
            table.addRow(Column.AUTO_NUMBER,name,25000,null);
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
    }
    public void changecash(int index, int m){
        cash.set(index,cash.get(index)+m);
    }
    public ArrayList<String> getplayersnames(){return names; }
    public ArrayList<Integer> getplayerscash(){return cash; }
    public ArrayList<String> getplayersshares(){return shares; }
}
