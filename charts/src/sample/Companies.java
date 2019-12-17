package sample;

import com.healthmarketscience.jackcess.*;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Companies {
    private Connection connect = null;
    private Statement statement = null;
    ArrayList<String>companies=new ArrayList<>();
    ArrayList<Double>shares=new ArrayList<>();
    public void takecompanies(){
        try{
            File file = new File("src\\Database.accdb");
            Database db = DatabaseBuilder.open(file);
            com.healthmarketscience.jackcess.Table table = db.getTable("Companies");
            //Cursor cursor = CursorBuilder.createCursor(table);
            for(Row row : table) {
                companies.add(String.valueOf(row.get("Name")));
                shares.add(Double.valueOf((row.get("Price").toString())));
            }
            System.out.println(shares);
            System.out.println(companies);
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
    }
    public void addtodatabasecompany(String name, double share){
        try{
            File file = new File("src\\Database.accdb");
            Database db = DatabaseBuilder.open(file);
            com.healthmarketscience.jackcess.Table table = db.getTable("Companies");
            Cursor cursor = CursorBuilder.createCursor(table);
            boolean found = cursor.findFirstRow(Collections.singletonMap("Name","NONE"));
            table.deleteRow(cursor.getCurrentRow());
            table.addRow(Column.AUTO_NUMBER,name,share);
        }
        catch (IOException e){
            e.printStackTrace(System.out);
        }
    }
    public void changeshares(int index, double d){
        shares.set(index,shares.get(index)+d);
    }
    public ArrayList<String> getcompaniesnames(){
        return companies;
    }
    public ArrayList<Double> getcompaniesshares(){
        return shares;
    }
}
