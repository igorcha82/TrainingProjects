
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


public class MosMetro {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0").get();
        ArrayList<String> mosMetroArray = parsingClass(doc);
        fileCreated(mosMetroArray);
        parsingStation();
        parsingConection();
    }

    public static void fileCreated(ArrayList<String> mosMetroArray) throws IOException {
        JSONObject obj = new JSONObject();
        JSONArray req = new JSONArray();
        JSONArray lineNumber = new JSONArray();
        JSONArray connectionNumber = new JSONArray();
        HashMap<String, String> count = new HashMap<>();
        HashMap<String, String> getColor = new HashMap<>();

        for (int i = 0; i < mosMetroArray.size(); i++) {
            String arrayData = mosMetroArray.get(i);
            String[] arrayDate = arrayData.split(",");

            for (int j = 0; j < arrayDate.length; j++) {
                count.put(arrayDate[0], arrayDate[1]);
                getColor.put(arrayDate[0],arrayDate[2]);
            }
        }
        for (String name : count.keySet()) {
            JSONObject reqObj = new JSONObject();
            reqObj.accumulate("Number", name);
            reqObj.accumulate("Name", count.get(name));
            for(String item: getColor.keySet()) {
            if(item.equals(name)) {
            reqObj.accumulate("Color", getColor.get(item));}}
            req.put(reqObj);
        }
        obj.put("Line", req);

        for (String item : count.keySet()) {
            JSONObject stationJson = new JSONObject();
            JSONArray stationJs = new JSONArray();
            for (int i = 0; i < mosMetroArray.size(); i++) {
                String arrayData = mosMetroArray.get(i);
                String[] arrayDate = arrayData.split(",");

                if (item.equals(arrayDate[0])) {
                    stationJs.put(arrayDate[3]);
                }
            }
            stationJson.put(item, stationJs);
            lineNumber.put(stationJson);
        }

        obj.put("Station", lineNumber);



            for (int i = 0; i < mosMetroArray.size(); i++) {
                String arrayData = mosMetroArray.get(i);
                String[] arrayDate = arrayData.split(",");

                if ((arrayDate.length > 4)) {
                    JSONObject connection = new JSONObject();
                    if (arrayDate[4].matches("(Переход).+")) {

                        connection.put("Line", arrayDate[0]);
                        connection.put("Station", arrayDate[3]);
                    }
                    connectionNumber.put(connection);
                }

        }

        obj.put("connections", connectionNumber);






        try (FileWriter file = new FileWriter("mosMetro.json")) {
            file.write(obj.toString());
            file.flush();
        }
    }



    public static ArrayList<String> parsingClass(Document doc) throws ParseException, IOException {

        String trans = null;
        String color = null;
        String colorStr=null;
        String stationName = null;
        ArrayList<String> mosMetro = new ArrayList<>();
for (int i=3; i<5; i++) {
        Element element = doc.select("table").get(i);
        Elements elements = element.select("tbody > tr");

        for (Element itemL : elements) {

                Elements element1 = itemL.select("td");
                if (element1.size()!=0) {
                String colorString = element1.attr("style"); //строка цвет линии

                    if (!(colorString.matches(".+(;).+")) && (colorString!="")) {
                color = colorString.substring(colorString.indexOf("#"));
            } if (colorString.matches(".+(;).+"))
                    {
                        color = colorString.substring(colorString.indexOf("#"),colorString.indexOf(";"));
                    }
                    Color colorName = Color.decode(color);
                    colorList colorList =new colorList();

                    colorStr = colorList.setRGB(colorName.toString());

                trans = element1.select("td").get(3).children().attr("title");

                stationName = itemL.select("td").get(1).child(0).text();

                }
                // номера линий
                Element countLine = element1.select("span").first();
                if (!(countLine == null)) {
                    String count = countLine.text();
                    String nameLine = itemL.select("span[title]").select("img").attr("alt"); //получил список линий

                    String lineInfo = count + "," + nameLine + ","+ colorStr + "," + stationName + "," + trans;
                    trans = "";

                    mosMetro.add(lineInfo);
                }

        }
        }

      return mosMetro;

}

    public static void  parsingStation() throws Exception {

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonData = (org.json.simple.JSONObject) parser.parse(getJsonFile());
        org.json.simple.JSONArray linesArray = (org.json.simple.JSONArray) jsonData.get("Station");

        for (Object item : linesArray) {
            int startSubStr = item.toString().indexOf("\"");
            int finishSubStr = item.toString().indexOf(":");
            String lineNumber = item.toString().substring(startSubStr + 1, finishSubStr - 1);

            int coutStr = item.toString().indexOf("[");
            int countFin = item.toString().indexOf("]");
            String countStation = item.toString().substring(coutStr + 1, countFin);
            String[] count = countStation.split("\"");

            System.out.println("Линия номер: " + lineNumber + " Количество станций: " + count.length/2);

        }
    }

    public static void  parsingConection() throws Exception {
        int countConnection=0;
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonData = (org.json.simple.JSONObject) parser.parse(getJsonFile());
        org.json.simple.JSONArray linesArray = (org.json.simple.JSONArray) jsonData.get("connections");

        for (Object item: linesArray)
        {
            if (item.toString().matches(".+(Station).+"))
           {countConnection++;
           }

        }
        System.out.println("Количество переходов: " + countConnection);

    }

    private static String getJsonFile()
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("mosMetro.json"));
            lines.forEach(line -> builder.append(line));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}

