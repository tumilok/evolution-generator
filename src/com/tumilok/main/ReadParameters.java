package com.tumilok.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadParameters {

    private int width;
    private int height;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private double jungleRatio;

    public ReadParameters() {
        readParameters();
    }

    private void readParameters() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("parameters.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray mapList = (JSONArray) obj;

            //Iterate over map array
            mapList.forEach( map -> parseMapObject( (JSONObject) map ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseMapObject(JSONObject map) {

        // Get Object within list
        JSONObject mapObject = (JSONObject) map.get("map");

        //Get map width
        this.width = Integer.parseInt((String) mapObject.get("width"));

        //Get map height
        this.height = Integer.parseInt((String) mapObject.get("height"));

        //Get map startEnergy
        this.startEnergy = Integer.parseInt((String) mapObject.get("startEnergy"));

        //Get map moveEnergy
        this.moveEnergy = Integer.parseInt((String) mapObject.get("moveEnergy"));

        //Get map plantEnergy
        this.plantEnergy = Integer.parseInt((String) mapObject.get("plantEnergy"));

        //Get map jungleRatio
        this.jungleRatio = Double.parseDouble((String) mapObject.get("jungleRatio"));
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getStartEnergy() {
        return startEnergy;
    }
    public int getMoveEnergy() {
        return moveEnergy;
    }
    public int getPlantEnergy() {
        return plantEnergy;
    }
    public double getJungleRatio() {
        return jungleRatio;
    }
}
