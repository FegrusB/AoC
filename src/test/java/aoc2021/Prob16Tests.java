package aoc2021;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class Prob16Tests {

    public int testScriptP1(String input){

        ArrayList<String> packets = new ArrayList<>();
        Prob16.decode(packets,Prob16.hexToBin(input),0);

        int sum = 0;
        for(String packet: packets){
            String[] packetSplit =  packet.split(",");
            sum += Integer.parseInt(packetSplit[0],2);
        }

        return sum;

    }

    public long testScriptP2(String input){
        ArrayList<String> packets = new ArrayList<>();
        Prob16.decode(packets,Prob16.hexToBin(input),0);
        Collections.reverse(packets);
        return Prob16.calculate(packets);
    }


    @Test
    public void prob16Test1(){assert testScriptP1("D2FE28") == 6; assert testScriptP2("D2FE28") == 2021L;}

    @Test
    public void prob16Test2(){assert testScriptP1("38006F45291200") == 9;assert  testScriptP2("38006F45291200") == 1L;}

    @Test
    public void prob16Test3(){assert testScriptP1("EE00D40C823060") == 14;assert testScriptP2("EE00D40C823060") == 3L;}
    @Test
    public void prob16Test4(){assert testScriptP1("8A004A801A8002F478") == 16;assert testScriptP2("8A004A801A8002F478")  == 15L;}

    @Test
    public void prob16Test5(){assert testScriptP1("620080001611562C8802118E34") == 12;
        assert testScriptP2("620080001611562C8802118E34") == 46L;}

    @Test
    public void prob16Test6(){assert testScriptP1("C0015000016115A2E0802F182340") == 23;  assert testScriptP2("C0015000016115A2E0802F182340") == 46L;}

    @Test
    public void prob16Test7(){assert testScriptP1("A0016C880162017C3686B18A3D4780") == 31; assert testScriptP2("A0016C880162017C3686B18A3D4780") == 54L;}

    @Test
    public void pro16Test8(){assert testScriptP2("C200B40A82") == 3L;}

    @Test
    public void pro16Test9(){assert testScriptP2("04005AC33890") == 54L;}

    @Test
    public void pro16Test10(){assert testScriptP2("880086C3E88112") == 7L;}

    @Test
    public void pro16Test11(){assert testScriptP2("CE00C43D881120") == 9L;}

    @Test
    public void pro16Test12(){assert testScriptP2("D8005AC2A8F0") == 1L;}
    @Test
    public void pro16Test13(){assert testScriptP2("F600BC2D8F") == 0L;}
    @Test
    public void pro16Test14(){assert testScriptP2("9C005AC2F8F0") == 0L;}
    @Test
    public void pro16Test15(){assert testScriptP2("9C0141080250320F1802104A08") == 1L;}







}
