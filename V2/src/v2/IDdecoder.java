package v2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JanMaghirang
 */
public class IDdecoder {
    public String getA(instruction i,String[][] reg){
        String A="";
        String regA="";
        int r1=Integer.parseInt(i.getOpcode().substring(6, 11),2);
        regA="R"+String.valueOf(r1);
        for(int z=0;z<reg.length;z++){
            if(regA.equals(reg[z][0]))
                A=reg[z][1];
        }
        return A;
    }
public String getB(instruction i,String[][] reg){
        String B="";
        String regB="";
        int r1=Integer.parseInt(i.getOpcode().substring(11, 16),2);
        regB="R"+String.valueOf(r1);
        for(int z=0;z<reg.length;z++){
            if(regB.equals(reg[z][0]))
                B=reg[z][1];
        }      
        return B;
    }
public String getIMM(instruction i){
        String IMM=i.getOpcode().substring(16, i.getOpcode().length());
        return IMM;
    }
}