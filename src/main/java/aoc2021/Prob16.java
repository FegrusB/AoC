package aoc2021;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prob16 {

    public static void main(String[] args) {
        Scanner myScanner = GetScanner.get("2021-16a.txt");
        String inString = "";
        while (myScanner.hasNext()){inString = inString.concat(myScanner.nextLine());}

        String binString = hexToBin(inString);
        System.out.println(binString);
        System.out.println();

        ArrayList<String> packets = new ArrayList<>();

        decode(packets,binString,0);

        int versionCount = 0;
        for(String packet:packets){

            versionCount += Integer.parseInt(packet.substring(0,3),2);

        }

        System.out.println(versionCount);

    }
    public static String decodeLiteral(String inString){

        boolean finished = false;
        String outString = "";
        int packetLength = 0;

        while (! finished){

            if(inString.charAt(0) == '0'){finished = true;}
            packetLength++;
            outString = outString.concat(inString.substring(1,5));
            inString = inString.substring(5);

        }

        return packetLength + outString;

    }
    public static String decode(ArrayList<String> packets, String inString,int numLoops){

        boolean boundLoop = false;
        int x = 0;
        Pattern p = Pattern.compile("^[0]+$");
        Matcher m = p.matcher(inString);

        while ( (!m.matches() && !(inString.length() == 0 ))&& !boundLoop) {

            if (numLoops != 0){ boundLoop = x < numLoops - 1; }

            String packetVersion = inString.substring(0, 3);
            String packetTypeID = inString.substring(3, 6);
            inString = inString.substring(6);
            String outString = packetVersion + "," + packetTypeID + ",";


            if (packetTypeID.equals("100")) {
                String body = "";
                body = decodeLiteral(inString);
                int bytes = Character.getNumericValue(body.charAt(0));
                body = body.substring(1);
                packets.add(outString + body);

                int cutLength = (bytes * 5) + 6;

                cutLength = (cutLength + 3)/ 4 * 4;

                inString = inString.substring(cutLength - 6);
                System.out.println(outString + body);

            } else {
                int length = 0;
                if (inString.charAt(0) == '0') {
                    String test = Integer.parseInt(inString.substring(1, 16), 2) + "   " + inString.substring(1, 16);
                    length = Integer.parseInt(inString.substring(1, 16), 2);
                    String body = inString.substring(16,length+16);

                    packets.add(outString + inString.charAt(0) + "," + length + "," + body);

                    decode(packets,body,0);

                    int cutLength = length+16;
                    cutLength = (cutLength + 3)/ 4 * 4;

                    inString = inString.substring(cutLength-1);

                } else {

                    String test = inString.substring(1,12);
                    length = Integer.parseInt(inString.substring(1,12),2);
                    packets.add(outString + inString.charAt(0) + "," + length);
                    inString = decode(packets,inString.substring(12),length);

                }

            }

            m = p.matcher(inString);

        }

        return inString;

    }

    public static String hexToBin(String inString){

        String outString = "";

        for(int x = 0; x < inString.length();x++){

            String appendString = "";

            switch (inString.charAt(x)){
                case ('0'): appendString = "0000";break;
                case ('1'): appendString = "0001";break;
                case ('2'): appendString = "0010";break;
                case ('3'): appendString = "0011";break;
                case ('4'): appendString = "0100";break;
                case ('5'): appendString = "0101";break;
                case ('6'): appendString = "0110";break;
                case ('7'): appendString = "0111";break;
                case ('8'): appendString = "1000";break;
                case ('9'): appendString = "1001";break;
                case ('A'): appendString = "1010";break;
                case ('B'): appendString = "1011";break;
                case ('C'): appendString = "1100";break;
                case ('D'): appendString = "1101";break;
                case ('E'): appendString = "1110";break;
                case ('F'): appendString = "1111";break;
            }
            outString = outString.concat(appendString);
        }

        return outString;

    }

}
