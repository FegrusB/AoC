package aoc2021;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Prob16Tests {

    public int testScript(String input){

        ArrayList<String> packets = new ArrayList<>();
        Prob16.decode(packets,Prob16.hexToBin(input),0);

        int sum = 0;
        for(String packet: packets){
            String[] packetSplit =  packet.split(",");
            sum += Integer.parseInt(packetSplit[0],2);
        }

        return sum;

    }


    @Test
    public void prob16Test1(){assert testScript("D2FE28") == 6;}
    @Test
    public void prob16Test2(){assert testScript("8A004A801A8002F478") == 16;}

    @Test
    public void prob16Test3(){assert testScript("620080001611562C8802118E34") == 12;}

    @Test
    public void prob16Test4(){assert testScript("C0015000016115A2E0802F182340") == 23;}

    @Test
    public void prob16Test5(){assert testScript("A0016C880162017C3686B18A3D4780") == 31;}

}
