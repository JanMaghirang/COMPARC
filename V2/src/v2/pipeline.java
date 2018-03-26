/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2;

/**
 *
 * @author Jeff
 */
public class pipeline {
    String cycle[] = {"IF","ID","EX","MEM","WB"};
    String[][] pipeline = new String[100][100];
    String[] reg1=new String[10];
    String[] reg2=new String[10];
    String[] reg3=new String[10];
    String[] ins=new String[10];
    int row=0,col=0;
    public void add(String[] line, int i){
       opcodeConstructor oc=new opcodeConstructor();
        int x=0;
        ins[i]=oc.DecodeIns(line[i]);
        System.out.println(ins[i]);
        reg1[i]=oc.DecodeReg1(line[i], ins[i], reg1[i]);
        reg2[i]=oc.DecodeReg2(line[i], ins[i], reg1[i], reg2[i]);
        reg3[i]=oc.DecodeReg3(line[i], ins[i], reg1[i], reg2[i], reg3[i]);
       
       if(i==0){
         for(x=0; x<5;x++)
              pipeline[row][x]= cycle[x]; 
        row++;
        col++;
       }
       else{
           if(!reg2.equals(reg1[i-1])){
               if(!reg3.equals(reg3[i-1])){
                for(x=0; x<5;x++)
                     pipeline[row][x+col]= cycle[x]; 
               }
            row++;
            col++;
           }
           else{
               for(x=0; x<8;x++)
                if(x==0)     
                 pipeline[row][x+col]= cycle[x];
                else if(x>0&&x<4)
                    pipeline[row][x+col]= "**";
                else 
                    pipeline[row][x+col]= cycle[x-3];
            
            row++;
            col=7+col;
           }
       }
       display();
    }
    
    public void display(){
       int x,y; 
          for(x=0; x<15;x++){
              for(y=0; y<15; y++)
                  System.out.print("|"+ pipeline[x][y] + "|");
              System.out.println();
          }
    }
    
    public void initialize(){
       int x,y; 
        for(x=0; x<100;x++)
              for(y=0; y<100; y++)
                  pipeline[x][y] = "NA";   
      
}
}
