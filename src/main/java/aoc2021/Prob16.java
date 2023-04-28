package aoc2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Prob16 {

    public static void main(String[] args) {

        String inString = "";

        if( args.length == 0 ) {
            Scanner myScanner = GetScanner.get("2021-16.txt");
            while (myScanner.hasNext()) {
                inString = inString.concat(myScanner.nextLine());
            }
        } else {
            inString = args[0];
        }

        String binString = hexToBin(inString);
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

        return packetLength + ","+ outString;

    }
    public static String decode(ArrayList<String> packets, String inString,int numLoops){

        boolean boundLoop = false;
        int x = 0;

        while ( !(inString.length() < 11 )&& !boundLoop) {
            if (numLoops != 0){ boundLoop = x < numLoops - 1; }

            //decode and remove version + id
            String packetVersion = inString.substring(0, 3);
            String packetTypeID = inString.substring(3, 6);
            inString = inString.substring(6);
            String outString = packetVersion + "," + packetTypeID + ",";

            if (packetTypeID.equals("100")) {
                //decode literal and add to packets
                String body = "";
                body = decodeLiteral(inString);
                String[] bodySplit = body.split(",");
                int bytes = Integer.parseInt(bodySplit[0]);
                packets.add(outString + bodySplit[1]);

                //cut packet from string
                int cutLength = (bytes * 5);
                inString = inString.substring(cutLength);

            } else {
                int length = 0;
                if (inString.charAt(0) == '0') {
                    //parse length from bin as dec, substring based on result
                    length = Integer.parseInt(inString.substring(1, 16), 2);
                    String body = inString.substring(16,length+16);

                    packets.add(outString + inString.charAt(0) + "," + length);

                    decode(packets,body,0);

                    int cutLength = length+16;
                    inString = inString.substring(cutLength);

                } else {
                    length = Integer.parseInt(inString.substring(1,12),2);
                    packets.add(outString + inString.charAt(0) + "," + length);
                    inString = decode(packets,inString.substring(12),length);
                }
            }
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
