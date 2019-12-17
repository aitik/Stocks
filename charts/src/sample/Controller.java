package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

//steps:
//0)PLAYERS SYSTEM ASAP
//1)changing amount of companies using txt file
//2)adding companies using https://financequotes-api.com/
public class Controller {

    Companies companies = new Companies();
    Players players = new Players();
    private Connection connect = null;
    private Statement statement = null;
    int sd1, dayint,ng,index,playerid;
    int amountofcompanies, amountofplayers;
    String daystr;
    String currentplayer="";
    Boolean checkstart =false;
    Image green = new Image("green.png");
    Image red = new Image("red.png");
    Image greenup = new Image("greenup.png");
    Image reddown = new Image("reddown.png");
    @FXML
    private TextField amountb;
    @FXML
    private TextField amounts;
    @FXML
    private TextField namelogin;
    @FXML
    private TabPane tabpane;
    @FXML
    private ChoiceBox cmpcb;
    @FXML
    private ChoiceBox cmpcb1;
    @FXML
    private TableView playersinfo;
    @FXML
    private javafx.scene.image.ImageView image1;
    @FXML
    private javafx.scene.image.ImageView image2;
    @FXML
    private javafx.scene.image.ImageView image3;
    @FXML
    private javafx.scene.image.ImageView image4;
    @FXML
    private javafx.scene.image.ImageView image5;
    @FXML
    private javafx.scene.image.ImageView image6;
    @FXML
    private javafx.scene.image.ImageView image7;
    @FXML
    private Label buyselllog;
    @FXML
    private Label daysl;
    @FXML
    private Label stocks1;
    @FXML
    private Label stocks2;
    @FXML
    private Label stocks3;
    @FXML
    private Label stocks4;
    @FXML
    private Label stocks5;
    @FXML
    private Label stocks6;
    @FXML
    private Label stocks7;
    @FXML
    private Label diff1;
    @FXML
    private Label diff2;
    @FXML
    private Label diff3;
    @FXML
    private Label diff4;
    @FXML
    private Label diff5;
    @FXML
    private Label diff6;
    @FXML
    private Label diff7;
    @FXML
    private Label loglogin;
    @FXML
    private Label loggedas;
    @FXML
    private CategoryAxis days;
    @FXML
    private Button start;
    @FXML
    private TextField addcompanyshare;
    @FXML
    private TextField addplayername;
    @FXML
    private TextField addcompanyname;
    @FXML
    private TitledPane titledpane1;
    @FXML
    private TitledPane titledpane2;
    @FXML
    private TitledPane titledpane3;
    @FXML
    private TitledPane titledpane4;
    @FXML
    private TitledPane titledpane5;
    @FXML
    private TitledPane titledpane6;
    @FXML
    private TitledPane titledpane7;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private LineChart<?, ?> chart1;
    @FXML
    private LineChart<?, ?> chart2;
    @FXML
    private LineChart<?, ?> chart3;
    @FXML
    private LineChart<?, ?> chart4;
    @FXML
    private LineChart<?, ?> chart5;
    @FXML
    private LineChart<?, ?> chart6;
    @FXML
    private Accordion accordion;
    @FXML
    private void showchart1(){
        chartsvisible(0);
    }
    @FXML
    private void showchart2(){
        chartsvisible(1);
    }
    @FXML
    private void showchart3(){
        chartsvisible(2);
    }
    @FXML
    private void showchart4(){
        chartsvisible(3);
    }
    @FXML
    private void showchart5(){
        chartsvisible(4);
    }
    @FXML
    private void showchart6(){
        chartsvisible(5);
    }
    @FXML
    private void showchart7(){
        chartsvisible(6);
    }

    ArrayList<String> company = new ArrayList<>();
    ArrayList<Double> shares = new ArrayList<>();
    ArrayList<Label> diffs = new ArrayList<>();
    ArrayList<Double> sharediff = new ArrayList<>();
    ArrayList<Label> stocks = new ArrayList<>();
    ArrayList<XYChart.Series> series = new ArrayList<>();
    ArrayList<LineChart> charts = new ArrayList<>();
    ArrayList<TitledPane> titledpanes = new ArrayList<>();
    ArrayList<javafx.scene.image.ImageView> images = new ArrayList<>();
    ArrayList<String> playersnames = new ArrayList<>();
    ArrayList<ArrayList<Integer>> share = new ArrayList<>();
    ArrayList<Integer> playerscash = new ArrayList<>();
    ObservableList<Table> data = FXCollections.observableArrayList();

    @FXML
    private void handleKeyPress(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER){
            nd();
        }
        else if(event.getCode()==KeyCode.DIGIT1){
            if(company.get(0).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(0));
            chartsvisible(0);
        }
        else if(event.getCode()==KeyCode.DIGIT2){
            if(company.get(1).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(1));
            chartsvisible(1);
        }
        else if(event.getCode()==KeyCode.DIGIT3){
            if(company.get(2).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(2));
            chartsvisible(2);
        }
        else if(event.getCode()==KeyCode.DIGIT4){
            if(company.get(3).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(3));
            chartsvisible(3);
        }
        else if(event.getCode()==KeyCode.DIGIT5){
            if(company.get(4).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(4));
            chartsvisible(4);
        }
        else if(event.getCode()==KeyCode.DIGIT6){
            if(company.get(5).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(5));
            chartsvisible(5);
        }
        else if(event.getCode()==KeyCode.DIGIT7){
            if(company.get(6).equals("NONE")){
                return;
            }
            accordion.setExpandedPane(titledpanes.get(6));
            chartsvisible(6);
        }
    }
    @FXML
    private void newgame(){
        start();
    }
    @FXML
    private void nd(){
        nextDay();
    }
    public void start(){
        checkstart=true;
        players.getnames();
        players.getmoney();
        players.getshares();
        playerscash = players.getplayerscash();
        companies.takecompanies();
        company=companies.getcompaniesnames();
        amountofcompanies=company.size();
        shares=companies.getcompaniesshares();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();
        XYChart.Series series5 = new XYChart.Series();
        XYChart.Series series6 = new XYChart.Series();
        XYChart.Series series7 = new XYChart.Series();
        titledpanesnames();
        chartsadd();
//        stocks1.setVisible(true);
//        stocks2.setVisible(true);
        daystr="1";
        dayint=1;
        table();
        addimages();
        for(int i=0;i<company.size();i++){
            sharediff.add(0.00);
        }
        stocksadd();
        stocks1.setText(company.get(0)+": 0.00");
        stocks2.setText(company.get(1)+": 0.00");
        stocks3.setText(company.get(2)+": 0.00");
        stocks4.setText(company.get(3)+": 0.00");
        stocks5.setText(company.get(4)+": 0.00");
        stocks6.setText(company.get(5)+": 0.00");
        stocks7.setText(company.get(6)+": 0.00");
        diffsadd();
        start.setDisable(true);
        series1.setName(company.get(0));
        series1.getData().add(new XYChart.Data<>(daystr,shares.get(0)));
        series2.setName(company.get(1));
        series2.getData().add(new XYChart.Data<>(daystr,shares.get(1)));
        series3.setName(company.get(2));
        series3.getData().add(new XYChart.Data<>(daystr,shares.get(2)));
        series4.setName(company.get(3));
        series4.getData().add(new XYChart.Data<>(daystr,shares.get(3)));
        series5.setName(company.get(4));
        series5.getData().add(new XYChart.Data<>(daystr,shares.get(4)));
        series6.setName(company.get(5));
        series6.getData().add(new XYChart.Data<>(daystr,shares.get(5)));
        series7.setName(company.get(6));
        series7.getData().add(new XYChart.Data<>(daystr,shares.get(6)));
        for(int i=0;i<company.size();i++){
            if(company.get(i).equalsIgnoreCase("none")){
                continue;
            }
            cmpcb.getItems().add(company.get(i));
            cmpcb1.getItems().add(company.get(i));
        }
        scale();
        series.add(series1);
        series.add(series2);
        series.add(series3);
        series.add(series4);
        series.add(series5);
        series.add(series6);
        series.add(series7);
        for(int i =0; i <company.size();i++){
            charts.get(i).getData().add(series.get(i));
            charts.get(i).setAnimated(false);
        }
        for( int i=0;i<company.size();i++){
            if(company.get(i).equalsIgnoreCase("none")){
                charts.get(i).setVisible(false);
                titledpanes.get(i).setVisible(false);
            }
        }
        for(int i=0;i<playersnames.size();i++){
            share.add(new ArrayList<>());
            for(int j=0; j<company.size();j++){
                share.get(i).add(0);
            }
        }
        System.out.println(share);
        amountb.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    amountb.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        amounts.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    amounts.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        nextDay();
    }
    public void nextDay(){
        if(checkstart){
            dayint+=1;
            daystr=String.valueOf(dayint);
            days.setLabel("Day "+daystr);
            companieschange();
            daysl.setText("Day "+daystr);
        }

    }
    @FXML
    private void buy(){
        if(currentplayer.equals("")){
            buyselllog.setText("Please, sign in");
        }
        else{
            if(Integer.parseInt(amountb.getText())>1000){
                buyselllog.setText("Please, write correct number");
            }
            else{
                for(int i=0;i<company.size();i++){
                    if(cmpcb.getValue().equals(company.get(i))){
                        index=i;
                        break;
                    }
                }
                for(int i=0;i<playersnames.size();i++){
                    if(currentplayer.equalsIgnoreCase(playersnames.get(i))){
                        playerid=i;
                        break;
                    }
                }
                if(playerscash.get(playerid)-(int)(Integer.parseInt(amountb.getText())*shares.get(index))<0){
                    buyselllog.setText("Not enough money");
                    return;
                }
                share.get(playerid).set(index,share.get(playerid).get(index)+(Integer.parseInt(amountb.getText())));
                playerscash.set(playerid,playerscash.get(playerid)-(int)(Integer.parseInt(amountb.getText())*shares.get(index)));
                buyselllog.setText(currentplayer +" spent "+((int)(Integer.parseInt(amountb.getText())*shares.get(index))+" for buying "+company.get(index)+" shares"));
                playersinfo.getItems().clear();
                for(int i=0;i<players.getplayersnames().size();i++){
                    StringBuilder sb = new StringBuilder();
                    for (int s : share.get(i))
                    {
                        sb.append(String.valueOf(s));
                        sb.append("\t");
                    }
                    playersinfo.getItems().add(new Person(playersnames.get(i),playerscash.get(i), sb.toString()));
                }
            }

        }

    }
    @FXML
    private void sell(){
        if(currentplayer.equals("")){
            buyselllog.setText("Please, sign in");
        }
        else{
            if(Integer.parseInt(amounts.getText())>1000){
                buyselllog.setText("Please, write correct number");
            }
            else{
                for(int i=0;i<company.size();i++) {
                    if (cmpcb1.getValue().equals(company.get(i))) {
                        index = i;
                        break;
                    }
                }
                    for(int j=0;j<playersnames.size();j++){
                        if(currentplayer.equalsIgnoreCase(playersnames.get(j))){
                            playerid=j;
                            break;
                        }
                    }
                    if((share.get(playerid).get(index)-(Integer.parseInt(amounts.getText())))<0){
                        buyselllog.setText("Not enough shares. You have only "+share.get(playerid).get(index)+" shares of "+company.get(index));
                        return;
                    }
                    share.get(playerid).set(index,share.get(playerid).get(index)-(Integer.parseInt(amounts.getText())));
                    playerscash.set(playerid,playerscash.get(playerid)+(int)(Integer.parseInt(amounts.getText())*shares.get(index)));
                    buyselllog.setText(currentplayer +" earned "+((int)(Integer.parseInt(amounts.getText())*shares.get(index))+" for selling "+company.get(index)+" shares"));
                    playersinfo.getItems().clear();
                    for(int k=0;k<players.getplayersnames().size();k++){
                        StringBuilder sb = new StringBuilder();
                        for (int s : share.get(k))
                        {
                            sb.append(String.valueOf(s));
                            sb.append("\t");
                        }
                        playersinfo.getItems().add(new Person(playersnames.get(k),playerscash.get(k), sb.toString()));
                    }
                }
            }

        }
    public void companieschange(){
        for(int i=0; i<company.size(); i++){
            sd1=(int)(Math.random()*100)+1;
            if(sd1>40){//probability 60% changing more less 2%
                sharediff.set(i,Math.random()*2);
                ng=(int)(Math.random()*2+1);
                if(ng==1){
                    sharediff.set(i,sharediff.get(i)*-1);
                    if(shares.get(i)+sharediff.get(i)<=0){
                        sharediff.set(i,sharediff.get(i)*-1);
                        images.get(i).setImage(green);
                    }
                    else{
                        images.get(i).setImage(red);
                    }
                }
                else{
                    images.get(i).setImage(green);
                }
                shares.set(i,shares.get(i)+sharediff.get(i));
                series.get(i).getData().add(new XYChart.Data<>(daystr,shares.get(i)));
                stocks.get(i).setText(company.get(i)+": "+Math.round(shares.get(i)*1000.0)/1000.0);
                diffs.get(i).setText(String.valueOf((Math.round(sharediff.get(i)*1000.0)/1000.0)));
            }
            else if(sd1>20 && sd1<=40){//probability 20% changing more less 3%
                sharediff.set(i,Math.random()*3);
                ng=(int)(Math.random()*2+1);
                if(ng==1){
                    sharediff.set(i,sharediff.get(i)*(-1));
                    if(shares.get(i)+sharediff.get(i)<=0){
                        sharediff.set(i,sharediff.get(i)*(-1));
                        images.get(i).setImage(green);
                    }
                    else{
                        images.get(i).setImage(red);
                    }
                }
                else{
                    images.get(i).setImage(green);
                }
                shares.set(i,shares.get(i)+sharediff.get(i));
                series.get(i).getData().add(new XYChart.Data<>(daystr,shares.get(i)));
                stocks.get(i).setText(company.get(i)+": "+Math.round(shares.get(i)*1000.0)/1000.0);
                diffs.get(i).setText(String.valueOf((Math.round(sharediff.get(i)*1000.0)/1000.0)));
            }
            else if(sd1>5 && sd1<=20){//probability 15% changing more less 5%
                sharediff.set(i,Math.random()*5);
                ng=(int)(Math.random()*2+1);
                if(ng==1){
                    sharediff.set(i,sharediff.get(i)*(-1));
                    if(shares.get(i)+sharediff.get(i)<=0){
                        sharediff.set(i,sharediff.get(i)*(-1));
                        images.get(i).setImage(green);
                        if(sharediff.get(i)>3.00){
                            images.get(i).setImage(greenup);
                        }
                    }
                    else{
                        images.get(i).setImage(red);
                        if(sharediff.get(i)<-3.00){
                            images.get(i).setImage(reddown);
                        }
                    }
                }
                else{
                    images.get(i).setImage(green);
                    if(sharediff.get(i)>3.00){
                        images.get(i).setImage(greenup);
                    }
                }
                shares.set(i,shares.get(i)+sharediff.get(i));
                series.get(i).getData().add(new XYChart.Data<>(daystr,shares.get(i)));
                stocks.get(i).setText(company.get(i)+": "+Math.round(shares.get(i)*1000.0)/1000.0);
                diffs.get(i).setText(String.valueOf((Math.round(sharediff.get(i)*1000.0)/1000.0)));
            }
            else if(sd1<=5){//probability 5% changing more less 10%
                sharediff.set(i,Math.random()*10);
                ng=(int)(Math.random()*2+1);
                if(ng==1){
                    sharediff.set(i,sharediff.get(i)*(-1));
                    if(shares.get(i)+sharediff.get(i)<=0){
                        sharediff.set(i,sharediff.get(i)*(-1));
                        images.get(i).setImage(green);
                        if(sharediff.get(i)>3.00){
                            images.get(i).setImage(greenup);
                        }
                    }
                    else{
                        images.get(i).setImage(red);
                        if(sharediff.get(i)<-3.00){
                            images.get(i).setImage(reddown);
                        }
                    }
                }
                else{
                    images.get(i).setImage(green);
                    if(sharediff.get(i)>3.00){
                        images.get(i).setImage(greenup);
                    }
                }
                shares.set(i,shares.get(i)+sharediff.get(i));
                series.get(i).getData().add(new XYChart.Data<>(daystr,shares.get(i)));
                stocks.get(i).setText(company.get(i)+": "+Math.round(shares.get(i)*1000.0)/1000.0);
                diffs.get(i).setText(String.valueOf((Math.round(sharediff.get(i)*1000.0)/1000.0)));
            }
        }

    }
    public void scale(){
        final double SCALE_DELTA = 1.1;
        chart.setOnScroll(new EventHandler<ScrollEvent>() {
            public void handle(ScrollEvent event) {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

                chart.setScaleX(chart.getScaleX() * scaleFactor);
                chart.setScaleY(chart.getScaleY() * scaleFactor);
            }
        });
        chart.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    chart.setScaleX(1.0);
                    chart.setScaleY(1.0);
                }
            }
        });
    }
    public void chartsadd(){
        charts.add(chart);
        charts.add(chart1);
        charts.add(chart2);
        charts.add(chart3);
        charts.add(chart4);
        charts.add(chart5);
        charts.add(chart6);
    }
    public void chartsvisible(int i){
        for (int j =0; j<company.size();j++){
            charts.get(j).setVisible(false);
        }
        if(i>=charts.size()){
            return;
        }
        charts.get(i).setVisible(true);
    }
    public void titledpanesnames(){
        titledpanes.add(titledpane1);
        titledpanes.add(titledpane2);
        titledpanes.add(titledpane3);
        titledpanes.add(titledpane4);
        titledpanes.add(titledpane5);
        titledpanes.add(titledpane6);
        titledpanes.add(titledpane7);
        for(int j =0;j<titledpanes.size();j++){
            titledpanes.get(j).setVisible(false);
        }
        for( int i =0; i < amountofcompanies;i++){
            titledpanes.get(i).setText(company.get(i));
            titledpanes.get(i).setVisible(true);
        }
    }
    public void diffsadd(){
        diffs.add(diff1);
        diffs.add(diff2);
        diffs.add(diff3);
        diffs.add(diff4);
        diffs.add(diff5);
        diffs.add(diff6);
        diffs.add(diff7);
    }
    public void stocksadd(){
        stocks.add(stocks1);
        stocks.add(stocks2);
        stocks.add(stocks3);
        stocks.add(stocks4);
        stocks.add(stocks5);
        stocks.add(stocks6);
        stocks.add(stocks7);
    }
    public void table(){
        TableColumn<String, Person> column1 = new TableColumn<>("Name");
        TableColumn<Integer, Person> column2 = new TableColumn<>("Cash");
        TableColumn<String, Person> column3 = new TableColumn<>("Shares");
        column1.setCellValueFactory((new PropertyValueFactory<>("name")));
        column2.setCellValueFactory((new PropertyValueFactory<>("cash")));
        column3.setCellValueFactory((new PropertyValueFactory<>("shares")));
        column1.setMinWidth(150);
        column2.setMinWidth(100);
        column3.setMinWidth(400);
        playersinfo.getColumns().add(column1);
        playersinfo.getColumns().add(column2);
        playersinfo.getColumns().add(column3);
        for(int i=0;i<players.getplayersnames().size();i++){
            playersinfo.getItems().add(new Person(players.getplayersnames().get(i),players.getplayerscash().get(i),players.getplayersshares().get(i)));
            System.out.println("adding "+i+" ...");
            playersnames.add(players.getplayersnames().get(i));
        }
    }
//    @FXML
//    public void clickItem(MouseEvent event)
//    {
//        Table t = new Table(null,0,null);
//        if (event.getClickCount() == 2)
//        {
//            Person person = new Person();
////            System.out.println(playersinfo.getSelectionModel().getSelectedItem().getName());
////            System.out.println(playersinfo.getSelectionModel().getSelectedItem().getCash());
//            System.out.println(playersinfo.getSelectionModel().getSelectedItem().);
//            amountofplayers++;
//        }
//    }
//    public void adddata(){
//        for(int i=0;i<players.getplayersnames().size();i++){
//            data.add(new Table(players.getplayersnames().get(i),players.getplayerscash().get(i),players.getplayersshares().get(i)));
//        }
//
//    }
    @FXML
    private void addingplayer(){
        playersinfo.getItems().add(new Person(String.valueOf(addplayername.getText()),25000,null));
        players.addtodatabasename(String.valueOf(addplayername.getText()));
    }
    @FXML
    private void addingcompany(){
        String s =String.valueOf(addcompanyname.getText().toUpperCase());
        double d = Double.valueOf(addcompanyshare.getText());
        companies.addtodatabasecompany(s,d);
        for(int i=0;i<company.size();i++){
            if(company.get(i).equalsIgnoreCase("none")){
                charts.get(i).getData().clear();
                charts.get(i).getData().add(new XYChart.Data<>(daystr,d));
                charts.get(i).setVisible(true);
                titledpanes.get(i).setVisible(true);
                titledpanes.get(i).setText(s);
                company.set(i,s);
            }
        }
    }
    public void addimages(){
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        images.add(image6);
        images.add(image7);
    }
    @FXML
    private void signin(){
        String s=namelogin.getText();
        for(String n : playersnames){
            if(s.equalsIgnoreCase(n)){
                currentplayer=s;
                loggedas.setText("Logged as: "+n);
                loglogin.setText("Welcome, " + n+ "!");
                return;
            }
            else{
                loglogin.setText("Not found");
            }
        }
    }
    @FXML
    private void signup() {
        String s=namelogin.getText();
        if (s.equals("")) {
            loglogin.setText("Please, write your name");
        } else {
            for(String n: playersnames){
                if(s.equalsIgnoreCase(n)){
                    loglogin.setText("That player already exist");
                    return;
                }
            }
            currentplayer = s;
            playersinfo.getItems().add(new Person(currentplayer, 25000, null));
            players.addtodatabasename(currentplayer);
            loggedas.setText("Logged as: " + currentplayer);
            loglogin.setText("Signed up. Welcome, " + currentplayer + "!");
        }
    }
    }