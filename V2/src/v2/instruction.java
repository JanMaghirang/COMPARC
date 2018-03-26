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
public class instruction {
    String ins="";
    String rs="";
    String rt="";
    String rd="";
    String offset="";
    String imm="";
    String opcode="";
    String base="";
    String cycle="";
    public void setIns(String I){
        ins=I;
    }
    public void setRs(String r){
        rs=r;
    }
    public void setRt(String r){
        rt=r;
    }
    public void setRd(String r){
        rd=r;
    }
    public void setOffset(String o){
        offset=o;
    }
    public void setImm(String i){
        imm=i;
    }
    public void setBase(String b){
        base=b;
    }
    public void setOpcode(String o){
        opcode=o;
    }
    public void setCycle(String i){
        cycle=i;
    }
    
    public String getIns(){
        return ins;
    }
    public String getRs(){
        return rs;
    }
    public String getRt(){
        return rt;
    }
    public String getRd(){
        return rd;
    }
    public String getOffset(){
        return offset;
    }
    public String getImm(){
        return imm;
    }
    public String getBase(){
        return base;
    }
    public String getOpcode(){
        return opcode;
    }
    public String getCycle(){
        return cycle;
    }
        
    public String getIR(instruction i){
        int decimal = Integer.parseInt(i.getOpcode(),2);
        String hexStr = Integer.toString(decimal,16);
        String IR=hexStr;
        return IR;
    }
}
