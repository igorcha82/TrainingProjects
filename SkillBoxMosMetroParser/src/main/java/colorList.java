import java.util.HashMap;

public class colorList {

        String sumString;
        String colorName;
        HashMap<String, String> colorArray = new HashMap<>();

        public HashMap<String, String> setColorSelect() {
            colorArray.put("black", "0,0,0");
            colorArray.put("red", "239,22,30");
            colorArray.put("green", "45,190,44");
            colorArray.put("blue", "0,120,190");
            colorArray.put("cian", "0,191,255");
            colorArray.put("pery", "141,91,45");
            colorArray.put("greenish-yellow", "237,145,33");
            colorArray.put("violet", "128,0,128");
            colorArray.put("yellow", "255,215,2");
            colorArray.put("lime", "153,153,153");
            colorArray.put("light green", "153,204,0");
            colorArray.put("light cian", "130,192,192");
            colorArray.put("pink", "255,215,2");
            colorArray.put("light blue", "161,179,212");
            colorArray.put("mauve", "222,100,161");
            colorArray.put("blue-gray", "153,153,255");
            return this.colorArray;
    }


        public String setRGB (String color){

            setColorSelect();
            String r = color.substring(color.indexOf("=")+1, color.indexOf(","));
            String g = color.substring(color.indexOf("g=")+2, color.indexOf(",b"));
            String b = color.substring(color.lastIndexOf("=")+1, color.lastIndexOf("]"));
            this.sumString = r + "," + g + "," + b;

            for (String key : colorArray.keySet()) {

                if (sumString.equals(colorArray.get(key))) {
                    this.colorName = key;

                }}

            return this.colorName;

        }
    }
