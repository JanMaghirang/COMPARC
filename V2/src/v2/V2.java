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
public class V2 {
    String reg[][] = {
        {"R0","0000000000000000"},{"R1","0000000000000000"},{"R2","0000000000000000"},{"R3","0000000000000004"},
        {"R4","0000000000000000"},{"R5","0000000000000000"},{"R6","0000000000000000"},{"R7","0000000000000000"},
        {"R8","0000000000000000"},{"R9","0000000000000000"},{"R10","0000000000000000"},{"R11","0000000000000000"},
        {"R12","0000000000000000"},{"R13","0000000000000000"},{"R14","0000000000000000"},{"R15","0000000000000000"},
        {"R16","0000000000000000"},{"R17","0000000000000000"},{"R18","0000000000000000"},{"R19","0000000000000000"},
        {"R20","0000000000000000"},{"R21","0000000000000000"},{"R22","0000000000000000"},{"R23","0000000000000000"},
        {"R24","0000000000000000"},{"R25","0000000000000000"},{"R26","0000000000000000"},{"R27","0000000000000000"},
        {"R28","0000000000000000"},{"R29","0000000000000000"},{"R30","0000000000000000"},{"R31","0000000000000000"},
    };
    String[] line;
    int PC=0,CC=0,NPC=0;
    boolean done=false,stall=false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        mainGUI main = new mainGUI();
        main.setVisible(true);
    }
    
    public void execute(String[] lines){    
        opcodeConstructor oc =new opcodeConstructor();
        instruction[] ins = new instruction[lines.length];
        pipeline pipe = new pipeline();
        pipe.initialize();
        line=lines;
        for(int x=0; x<line.length; x++)
           ins[x] = new instruction();
        
        for(int i=0;i<line.length;i++){
            oc.opcodeConstructor(line[i],ins[i]);
            pipe.add(line, i);
        }    
        while(!done){
            if(PC<ins.length){
                if(!stall){
                    IF(ins,ins[PC],PC);
                    PC++;
                }
                else
                    CC++;
            }else done=true;
        }
    }
     
    public void IF(instruction[] ins, instruction i, int PC){
        String IR="";
        int decimal = Integer.parseInt(i.getOpcode(),2);
        String hexStr = Integer.toString(decimal,16);
        IR=hexStr;
        NPC=PC+1;
        try{
            if(ins[NPC].getRs().equals(i.getRd())||ins[NPC].getRt().equals(i.getRd()))
                stall=true;
            System.out.println("IF");
            System.out.println("IR="+IR);
            ID(ins,i,NPC,IR);       
            
            }catch(Exception e){
            System.out.println("IF");
            System.out.println("IR="+IR);
            ID(ins,i,NPC,IR);  
        }
        
    }
    
    public void ID(instruction[] ins, instruction i, int NPC, String IR){
        String A="", B="", IMM="";
        IDdecoder id=new IDdecoder();
        A=id.getA(i,reg);
        B=id.getB(i,reg);
        IMM=id.getIMM(i);
        System.out.println("ID");
        System.out.println("A="+A);
        System.out.println("B="+B);
        System.out.println("IMM="+IMM);
        System.out.println("NPC="+NPC);
        System.out.println("IR="+IR);
        EX(ins,i,NPC,IR,A,B,IMM);
    }
    
    public void EX(instruction[] ins, instruction i, int NPC, String IR, String A, String B, String IMM){
        int x=0,cond=0,zero=0;
        String m="", ALUout="";
        //ALU instruction only
        ALU alu = new ALU();
        x=alu.ALU(i,A,B,IMM);
        m=Integer.toString(x, 16);
        zero=16-m.length();
        for(int z=0;z<zero;z++)
            ALUout=ALUout.concat("0");
        ALUout=ALUout.concat(m);
        System.out.println("EX");
        System.out.println("ALU="+ALUout);
        System.out.println("IR="+IR);
        System.out.println("Cond="+cond);
        MEM(i,IR,ALUout);
    }
    
    public void MEM(instruction i,String IR, String ALUout){
        System.out.println("MEM");
        System.out.println("IR="+IR);
        System.out.println("ALU="+ALUout);
        WB(i,IR,ALUout);
    }
    
    public void WB(instruction i,String IR, String ALUout){
        //Register-Register
        int x=0;
        for(int z=0;z<reg.length;z++){
            if(i.getRd().equals(reg[z][0])){               
                x=z;
                reg[z][1]=ALUout;
            }
        }
        stall=false;
        System.out.println("WB");
        System.out.println(reg[x][0]+"="+reg[x][1]);
    }
    
}