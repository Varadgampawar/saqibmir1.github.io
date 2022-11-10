import java.util.*;

import javax.swing.plaf.ColorUIResource;

import java.io.*;

public class Assembler {
    public static void main(String[] args)
    {
        BufferedReader br=null;
        FileReader fr=null;
        FileWriter fw=null;
        BufferedWriter bw=null;
        try
        {
            String inputfilename="C:\\Users\\Shree\\Desktop\\LP\\input.txt";
            fr=new FileReader(inputfilename);
            br=new BufferedReader(fr);
            String outputfilename="C:\\Users\\Shree\\Desktop\\LP\\output.txt";
            fw=new FileWriter(outputfilename);
            bw=new BufferedWriter(fw);

            Hashtable<String,String>is=new Hashtable<String,String>();
            is.put("STOP", "00");
            is.put("ADD","01");
            is.put("SUB","02");
            is.put("MULT","03");
            is.put("MOVER","04");
            is.put("MOVEM","05");
            is.put("COMP","06");
            is.put("BC","07");
            is.put("DIV","08");
            is.put("READ","09");
            is.put("PRINT","10");
            Hashtable<String,String>dl=new Hashtable<String,String>();
            dl.put("DC","01");
            dl.put("DS","02");
            Hashtable<String,String>ad=new Hashtable<String,String>();
            ad.put("START","01");
            ad.put("END","02");
            ad.put("ORIGIN","03");
            ad.put("EQU","04");
            ad.put("LTORG","05");

            Hashtable<String,String>symtab=new Hashtable<String,String>();
            Hashtable<String,String>littab=new Hashtable<String,String>();
            ArrayList<Integer> pooltab=new ArrayList<Integer>();

            String currentline;
            int locptr=0;
            int symptr=1;
            int litptr=1;
            int pooltabptr=1;

            currentline=br.readLine();
            String s1=currentline.split(" ")[1];
            if(s1.equals("START"))
            {
                bw.write("AD \t 01 \t");
                String s2=currentline.split(" ")[2];
                bw.write("C \t"+s2+"\n");
                locptr=Integer.parseInt(s2);
            }
            while((currentline=br.readLine())!=null)
            {
                int mind_the_LC=0;  //used to chk whether we are actually modifying location counter or not
                String type=null;
                int flag2=0;   //used to check whether address is assigned to current symbol
                String s=currentline.split(" |\\,")[0];//first word in the line
                for(Map.Entry m:symtab.entrySet()) //allocating address to symbol
                {
                    if(s.equals(m.getKey())){
                        m.setValue(locptr);
                        flag2=1;
                    }
                }
                if(s.length()!=0 && flag2==0)  //if the string is not null and value is not assogned then the current string ,ust be new symbol
                {
                    symtab.put(s,String.valueOf(locptr));
                    symptr++;
                }

                int isOpcode =0; //checks whether current word is opcode or not

                s=currentline.split(" |\\,")[1]; //second word in the line
                for(Map.Entry m:is.entrySet())
                {
                    if(s.equals(m.getKey()))    //if match found in imperatave statement
                    {
                        bw.write("IS\t"+m.getValue()+"\t");
                        type="is";
                        isOpcode=1;
                    }
                }
                for(Map.Entry m:dl.entrySet())
                {
                    if(s.equals(m.getKey()))    //if match found in imperatave statement
                    {
                        bw.write("DL\t"+m.getValue()+"\t");
                        type="dl";
                        isOpcode=1;
                    }
                }
                for(Map.Entry m:ad.entrySet())
                {
                    if(s.equals(m.getKey()))    //if match found in imperatave statement
                    {
                        bw.write("AD\t"+m.getValue()+"\t");
                        type="ad";
                        isOpcode=1;
                    }
                }
                if(s.equals("LTORG"))
                {
                    pooltab.add(pooltabptr);
                    for(Map.Entry m:littab.entrySet()){
                        if(m.getValue()=="")
                        {
                            m.setValue(locptr);
                            locptr++;
                            pooltabptr++;
                            mind_the_LC=1;
                            isOpcode=1;
                        }
                    }
                }
            if(s.equals("END"))
            {
                pooltab.add(pooltabptr);
                for(Map.Entry m : littab.entrySet())
                {
                    if(m.getValue()==""){
                        m.setValue(locptr);
                        locptr++;
                        mind_the_LC=1;
                    }
                }
            }
            if(s.equals("EQU"))
            {
                symtab.put("equ",String.valueOf(locptr));
            }
            if(currentline.split(" |\\,").length>2)
            {
                s=currentline.split(" |\\,")[2];//this must be either a Register/DL/Symbol as it is a first operand
                if(s.equals("AREG")){
                    bw.write("1\t");
                    isOpcode=1;
                }
                else if(s.equals("BREG")){
                    bw.write("2\t");
                    isOpcode=1;

                }
                else if(s.equals("CREG")){
                    bw.write("3\t");
                    isOpcode=1;
                    
                }
                else if(s.equals("DREG")){
                    bw.write("4\t");
                    isOpcode=1;
                    
                }
                else
                {
                    bw.write("C\t"+s+"\t");
                }
                // else
                // {
                //     symtab.put(s,"");   //forward referenced symbol
                // }

            }
            if(currentline.split(" |\\,").length>3)
            {
                s=currentline.split(" |\\,")[3];
                if(s.contains("="))
                {
                    littab.put(s,"");
                    bw.write("L\t"+litptr+"\t");
                    isOpcode=1;
                    litptr++;
                }
                else if(s.equals("AREG")){
                    bw.write("1\t");
                    isOpcode=1;
                }
                else if(s.equals("BREG")){
                    bw.write("2\t");
                    isOpcode=1;

                }
                else if(s.equals("CREG")){
                    bw.write("3\t");
                    isOpcode=1;
                    
                }
                else if(s.equals("DREG")){
                    bw.write("4\t");
                    isOpcode=1;
                    
                }
                else
                {
                    symtab.put(s,"");
                    bw.write("S\t"+symptr+"\t");
                    symptr++;
                }
            }
            bw.write("\n");
            if(mind_the_LC==0)
            {
                locptr++;
            }
        }
            String f1="C:\\Users\\Shree\\Desktop\\LP\\SYMTAB.txt";
            FileWriter fw1=new FileWriter(f1);
            BufferedWriter bw1= new BufferedWriter(fw1);
            for(Map.Entry m:symtab.entrySet())
            {
                bw1.write(m.getKey()+"\t"+m.getValue()+"\n");
                System.out.println(m.getKey()+" "+m.getValue());
            }
            String f2="C:\\Users\\Shree\\Desktop\\LP\\LITTAB.txt";
            FileWriter fw2=new FileWriter(f2);
            BufferedWriter bw2= new BufferedWriter(fw2);
            for(Map.Entry m:symtab.entrySet())
            {
                bw2.write(m.getKey()+"\t"+m.getValue()+"\n");
                System.out.println(m.getKey()+" "+m.getValue());
            }
            String f3="C:\\Users\\Shree\\Desktop\\LP\\POOLTAB.txt";
            FileWriter fw3=new FileWriter(f3);
            BufferedWriter bw3= new BufferedWriter(fw3);
            for(Map.Entry m:symtab.entrySet())
            {
                bw3.write(m.getKey()+"\t"+m.getValue()+"\n");
                System.out.println(m.getKey()+" "+m.getValue());
            }
            bw.close();
            bw1.close();
            bw2.close();
            bw3.close();



        







        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
