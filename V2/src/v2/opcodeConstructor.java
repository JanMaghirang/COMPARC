/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2;

/**
 *
 * @author JanMaghirang
 */
public class opcodeConstructor {
    String instruction[] = {"LD","SD","DADDIU","DADDU","XORI","SLT","BLTZ","BC"};
    
    public void opcodeConstructor(String input, instruction line){
        String ins="",reg1="",reg2="",reg3="",imm="",offset="";
        String opcode="";
        StringBuilder sb = new StringBuilder();
        int z=0;
        
        if(input.contains(":")){
            String[] s=input.split(": ");
            input=s[1];
        }
        
        ins = DecodeIns(input);
        line.setIns(ins);
            
        if (ins.equalsIgnoreCase("LD")) {
            reg1=DecodeReg1(input,ins,reg1);
            offset=DecodeOffset2(input,ins,reg1,offset);
            reg2=DecodeBase(input,ins,reg1,offset,reg2);
            opcode=opcode.concat("110111");
            opcode=opcode.concat(regToOpcode(reg2));
            opcode=opcode.concat(regToOpcode(reg1));
            opcode=opcode.concat(immToOpcode(offset));
            line.setRt(reg1);
            line.setOffset(offset);
            line.setBase(reg2);
            
        } else if (ins.equalsIgnoreCase("SD")) {
            reg1=DecodeReg1(input,ins,reg1);
            offset=DecodeOffset2(input,ins,reg1,offset);
            reg2=DecodeBase(input,ins,reg1,offset,reg2);
            opcode=opcode.concat("110111");
            opcode=opcode.concat(regToOpcode(reg2));
            opcode=opcode.concat(regToOpcode(reg1));
            opcode=opcode.concat(immToOpcode(offset));
            line.setRt(reg1);
            line.setOffset(offset);
            line.setBase(reg2);
            
        } else if (ins.equalsIgnoreCase("DADDIU")) {
            reg1=DecodeReg1(input,ins,reg1);
            reg2=DecodeReg2(input,ins,reg1,reg2);
            imm=DecodeImm1(input,ins,reg1,reg2,imm);
            opcode=opcode.concat("111111");
            opcode=opcode.concat(regToOpcode(reg2));
            opcode=opcode.concat(regToOpcode(reg1));
            opcode=opcode.concat(immToOpcode(imm));
            line.setRt(reg1);
            line.setRs(reg2);
            line.setImm(imm);
            line.setOpcode(opcode);
                      
        } else if (ins.equalsIgnoreCase("DADDU")) {
            reg1=DecodeReg1(input,ins,reg1);
            reg2=DecodeReg2(input,ins,reg1,reg2);
            reg3=DecodeReg3(input,ins,reg1,reg2,reg3);
            opcode=opcode.concat("000000");
            opcode=opcode.concat(regToOpcode(reg2));
            opcode=opcode.concat(regToOpcode(reg3));
            opcode=opcode.concat(regToOpcode(reg1));
            opcode=opcode.concat("00000");
            opcode=opcode.concat("101101");
            line.setRd(reg1);
            line.setRs(reg2);
            line.setRt(reg3);
            line.setOpcode(opcode);
            
        } else if (ins.equalsIgnoreCase("XORI")) {
            reg1=DecodeReg1(input,ins,reg1);
            reg2=DecodeReg2(input,ins,reg1,reg2);
            imm=DecodeImm1(input,ins,reg1,reg2,imm);
            opcode=opcode.concat("001110");
            opcode=opcode.concat(regToOpcode(reg2));
            opcode=opcode.concat(regToOpcode(reg1));
            opcode=opcode.concat(immToOpcode(imm));
            line.setRt(reg1);
            line.setRs(reg2);
            line.setImm(imm);
            line.setOpcode(opcode);
            
        } else if (ins.equalsIgnoreCase("SLT")) {
            reg1=DecodeReg1(input,ins,reg1);
            reg2=DecodeReg2(input,ins,reg1,reg2);
            reg3=DecodeReg3(input,ins,reg1,reg2,reg3);
            opcode=opcode.concat("000000");
            opcode=opcode.concat(regToOpcode(reg2));
            opcode=opcode.concat(regToOpcode(reg3));
            opcode=opcode.concat(regToOpcode(reg1));
            opcode=opcode.concat("00000");
            opcode=opcode.concat("101010");
            line.setRd(reg1);
            line.setRs(reg2);
            line.setRt(reg3);
            line.setOpcode(opcode);
            
        } else if (ins.equalsIgnoreCase("BLTZ")) {
                
        } else if (ins.equalsIgnoreCase("BC")) {

        }else if(input=="NOP"){
            System.out.println("NOP");
        }else
           System.out.println("Invalid instruction");
    }
    
    public String DecodeIns(String input){
        String ins="";
        for (int j = 0; j < 8; j++) {
            if (input.contains(instruction[j])) {    
                ins = instruction[j];
            }
        }
        return ins;
    }
    
    public String DecodeReg1(String input,String ins,String reg1){
       
        if(input.substring(ins.length()+1,(ins.length()+4)).contains(","))
            reg1= input.substring(ins.length()+1,(ins.length()+3));
        else
            reg1= input.substring(ins.length()+1,(ins.length()+4)); 
        
        return reg1;
    }
    
    public String DecodeReg2(String input,String ins,String reg1, String reg2){
       
        if(input.substring((ins.length()+reg1.length()+2),(ins.length()+reg1.length()+5)).contains(",") || input.substring((ins.length()+reg1.length()+2),(ins.length()+reg1.length()+5)).contains("("))
            reg2= input.substring((ins.length()+reg1.length()+2),(ins.length()+reg1.length()+4));
        else
            reg2= input.substring((ins.length()+reg1.length()+2),(ins.length()+reg1.length()+5));
         
       
        return reg2;
    }
    
    public String DecodeReg3(String input,String ins,String reg1, String reg2, String reg3){
        try{
            reg3= input.substring((ins.length()+reg1.length()+reg2.length()+3),(ins.length()+reg1.length()+reg2.length()+6));
        }
        catch(Exception e){
            reg3= input.substring((ins.length()+reg1.length()+reg2.length()+3),(ins.length()+reg1.length()+reg2.length()+5));
        }
        return reg3;
    }
    
    //For XORI, DADDIU
    public String DecodeImm1(String input,String ins,String reg1, String reg2,String imm){
            imm= input.substring((ins.length()+reg1.length()+reg2.length()+4),(input.length()));
        return imm;
    } 
    
    public String DecodeOffset2(String input,String ins, String reg1, String offset){
            offset= input.substring(ins.length()+1+reg1.length()+2,(ins.length()+1+reg1.length()+1)+5);
        return offset;
    }
    
    public String DecodeBase(String input, String ins, String reg1, String offset, String reg2){
            reg2= input.substring((ins.length()+1+reg1.length()+1)+6, input.length()-1);
        return reg2;
    }
      
    //For BC
    public String DecodeOffset(String input,String ins,String branch){
            branch= input.substring(ins.length()+1,input.length());
        return branch;
      }
      
    public String regToOpcode(String reg){
        String opcode="";
        String x;
        int zero;
        x = Integer.toBinaryString(Integer.parseInt(reg.substring(1)));
        zero = 5 - x.length();
        for(int i=0;i<zero;i++){
            opcode=opcode.concat("0");
        }
        opcode=opcode.concat(x);
        return opcode;    
    }
    
    public String immToOpcode(String imm){
        String opcode="";
        int i = Integer.parseInt(imm,16);
        opcode = Integer.toString(i,2);
        int pad = imm.length()*4-opcode.length();
        for(int j=0;j<pad;j++){
                opcode="0"+opcode;
            }
        return opcode;
    }
}
