package lcs;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    int l1;
    int l2;
    String sequence_1;
    String sequence_2;
    String fileName;
    
    public void read(String fileName) {
        int i = 0;
        this.fileName = fileName;
        try{
        	//System.out.println(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = reader.readLine();
            l1 = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            l2 = Integer.parseInt(line.substring(line.indexOf(" ")+1));
            sequence_1 = reader.readLine();
            sequence_2 = reader.readLine();
            reader.close();
        }catch (IOException e){
            System.out.println("ERROR: MailListReader::read(IO)"+ e.getMessage());
        }
    }

    public int getL1() {
        return l1;
    }

    public int getL2() {
        return l2;
    }

    public String getSequence_1() {
        return sequence_1;
    }

    public String getSequence_2() {
        return sequence_2;
    }
    
    public void clear() {
    	this.l1 = 0;
    	this.l2 = 0;
    	this.sequence_1="";
        this.sequence_2="";
    }
    
    @Override
    public String toString() {
        return fileName;
    }
}
