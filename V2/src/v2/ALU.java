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
public class ALU {
    int c;
    public int ALU(instruction i, String A, String B, String IMM){
        String ins = i.getIns();
        int ALUoutput=0;
            
        if (ins.equalsIgnoreCase("LD")) {
            
            
        } else if (ins.equalsIgnoreCase("SD")) {
            
            
        } else if (ins.equalsIgnoreCase("DADDIU")) {
             c=0;
            ALUoutput=executeDADDIU(B,IMM);
                      
        } else if (ins.equalsIgnoreCase("DADDU")) {
            c=0;
            ALUoutput=executeDADDU(A,B);
        } else if (ins.equalsIgnoreCase("XORI")) {
            ALUoutput=executeXORI(A,B);

        } else if (ins.equalsIgnoreCase("SLT")) {
            ALUoutput = executeSLT(B);
            
            
        } else if (ins.equalsIgnoreCase("BLTZ")) {
                
        } else if (ins.equalsIgnoreCase("BC")) {

        }else if(ins.equalsIgnoreCase("NOP")){
            
        }       
        return ALUoutput;
    }   
    
    public int executeDADDU(String A, String B){
        int result=0;
        int r1=Integer.parseInt(A,16);
        int r2=Integer.parseInt(B,16);
        result=r1+r2;
        return result;
    }
    public int executeDADDIU(String B, String IMM){
       int result =0;
       int r1=Integer.parseInt(B,16);
       int r2=Integer.parseInt(IMM,16);
       result = r1+r2;
       return result;
    }
    
    public int executeXORI(String B, String IMM){
        
        String xor = Integer.toBinaryString(Integer.parseInt(B, 2) ^ Integer.parseInt(IMM, 2));
        
        return Integer.parseInt(xor);
    }
    public int executeSLT(String B){
        
       if(Integer.parseInt(B)<0)
           return 1;
       return 0;
        
    }
    
    
    
    public int getCond(){
        return c;
    }
    
}
