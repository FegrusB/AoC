package aoc2021;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Prob12 {

    static ArrayList<Cave> caves;


    public static void main(String[] args) {

        //build Arraylist of connections
        Scanner myScanner = GetScanner.get("2021-12.txt");
        caves = new ArrayList<>();

        //Create caves for each side of connection if they do not already exist and add connection to both caves.
        while (myScanner.hasNext()) {

            String[] entry = myScanner.nextLine().split("-");

            for (String cave : entry) {if (!(caves.contains(new Cave(cave)))) {caves.add(new Cave(cave));}}

            caves.get(caves.indexOf(new Cave(entry[0]))).addConnection(entry[1]);
            caves.get(caves.indexOf(new Cave(entry[1]))).addConnection(entry[0]);

        }

        ArrayList<String> paths = new ArrayList<>();
        getPaths("start ",caves.get(caves.indexOf(new Cave("start"))),paths,false);
        System.out.println(paths.size());
    }

    public static void getPaths(String pathSoFar, Cave currCave, ArrayList<String> pathList, boolean singleSmall) {
        //from a given cave, check each connection. If valid, start new search, with updated params. If at end add pathSoFar to pathList


        if (currCave.equals(new Cave("end"))) {
            pathList.add(pathSoFar);
        } else {

            ArrayList<String> connections = currCave.returnConnections();

            //check for valid connections. Path can gp through each small cave once, and a single small cave twice.
            for (String connection : connections) {

                if (connection.equals("start")){continue;}
                if (!pathSoFar.contains(connection)||StringUtils.isAllUpperCase(connection)) {
                    getPaths(pathSoFar + connection + " ", caves.get(caves.indexOf(new Cave(connection))),pathList,singleSmall);
                }else if(pathSoFar.contains(connection)&& !singleSmall){
                    getPaths(pathSoFar + connection + " ", caves.get(caves.indexOf(new Cave(connection))),pathList,true);
                }

            }


        }

    }

}

class Cave{
//simple class representing caves, with a name, and Arraylist of connections.
    private final String name;
    private final ArrayList<String> connections;
    public Cave(String name){

        this.name = name;
        connections = new ArrayList<>();

    }

    public void addConnection(String connection){connections.add(connection);}
    public ArrayList<String>  returnConnections(){return new ArrayList<>(connections);}

    @Override
    public int hashCode() {return 1;}

    @Override
    public boolean equals(Object obj){

        Cave check = (Cave) obj;
        return this.name.equals(check.name);

    }

}