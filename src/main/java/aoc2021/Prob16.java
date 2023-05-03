package aoc2021;

import java.util.*;

public class Prob16 {

    public static void main(String[] args) {

        String inString = "";

        Scanner myScanner = GetScanner.get("2021-16.txt");
        while (myScanner.hasNext()) {
            inString = inString.concat(myScanner.nextLine());
        }


        String binString = hexToBin(inString);
        ArrayList<String> packets = new ArrayList<>();

        decode(packets,binString,0);

        int versionCount = 0;
        for(String packet:packets){
            versionCount += Integer.parseInt(packet.substring(0,3),2);
        }

        Collections.reverse(packets);
        System.out.println(versionCount);
        System.out.println(calculate(packets));
    }

    public static Long calculate(ArrayList<String> packets){

        Stack<Long> calcStack = new Stack<>();
        int i = 0;

        while(packets.size() > 0){

            String curr = packets.get(0);
            packets.remove(0);
            String[] currSplit = curr.split(",");
            if (!Objects.equals(currSplit[1], "100")){i = Integer.parseInt(currSplit[3]);};
            switch (currSplit[1]) {

                //type 0: sum
                case "000" -> {
                    long x = 0L;
                    while (i > 0) {
                        x += calcStack.pop();
                        i--;
                    }
                    calcStack.add(x);
                }
                //type 1: product
                case "001" -> {
                    long x = 1L;
                    while (i > 0) {
                        x = x * calcStack.pop();
                        i--;
                    }
                    calcStack.add(x);
                }
                //type 2: minimum
                case "010" -> {
                    ArrayList<Long> checks = new ArrayList<>();
                    while (i > 0) {
                        checks.add(calcStack.pop());
                        i--;
                    }
                    checks.sort(null);
                    calcStack.add(checks.get(0));
                }
                //type 3: maximum
                case "011" -> {
                    ArrayList<Long> checks = new ArrayList<>();
                    while (i > 0) {
                        checks.add(calcStack.pop());
                        i--;
                    }
                    checks.sort(null);
                    calcStack.add(checks.get(checks.size() - 1));
                }
                //type 4: literal
                case "100" -> {
                    calcStack.add(Long.parseLong(currSplit[2], 2));
                }
                //type 5: greater than
                case "101" -> {
                    long x = calcStack.pop();
                    long y = calcStack.pop();
                    calcStack.add((x > y) ? 1L : 0L);
                }
                //type 6: less than
                case "110" -> {
                    long x = calcStack.pop();
                    long y = calcStack.pop();
                    calcStack.add((x > y) ? 0L : 1L);
                }
                //type 6: equals
                case "111" -> {
                    long x = calcStack.pop();
                    long y = calcStack.pop();
                    calcStack.add((x == y) ? 1L : 0L);
                }
            }
        }

        return calcStack.pop();

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
        int i = 0;

        while ( !(inString.length() < 11 )&& !boundLoop) {

            i++;
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
                    outString = outString + inString.charAt(0);
                    inString = inString.substring(length+16);
                    packets.add(outString + ",");

                    length = Integer.parseInt(decode(packets,body,0));

                    boolean set = false;
                    int y = packets.size() - 1;
                    while(!set) {
                        String s = packets.get(y);
                        if(s.charAt(s.length() - 1 ) == ','){
                            packets.set(y, s + length); set = true; }
                        y--;
                    }

                } else {
                    length = Integer.parseInt(inString.substring(1,12),2);
                    packets.add(outString + inString.charAt(0) + "," + length);
                    inString = decode(packets,inString.substring(12),length);
                }
            }
            if ( ! (i < numLoops) && numLoops != 0){boundLoop = true;}
        }

        if(numLoops == 0){
            inString = String.valueOf(i);}
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
