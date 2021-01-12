package lcs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.martiansoftware.jsap.*;

public class LCS {
    
    static int l1;
    static int l2;
    static String sequence_1;
    static String sequence_2;
    public static void main(String[] args) {
        JSAP jsaper = parsePreparation();
        JSAPResult config = jsaper.parse(args);
        if (!config.success()) {
            System.err.println();
            System.err.println("Usage: java " + LCS.class.getName());
            System.err.println("		" + jsaper.getUsage());
            System.err.println();
            System.err.println("		" + jsaper.getHelp());
            System.exit(1);
        }
        Reader reader = new Reader();
        if(config.getBoolean("tabulation")){
            System.out.println("Algoritmo Tabulation");
            if (config.contains("file")) {
                System.out.println("Archivo: " + config.getString("file"));
                reader.read(config.getString("file"));
                tabulationFile(reader, config);
            }
            if (config.contains("directory")) {
                File carpeta = new File(config.getString("directory"));
                if (carpeta == null){
                    System.err.println("El directorio no existe");
                    System.exit(1);
                }
                for(File archivo : carpeta.listFiles()) {
                    System.out.println("Archivo: "+archivo.getName());
                    reader.clear();
                    reader.read(carpeta.getName() + "/" + archivo.getName());
                    tabulationFile(reader, config);
                }
            }
        }
        
        if(config.getBoolean("memoization")){
            System.out.println("Algoritmo Memoization");
            if (config.contains("file")) {
                System.out.println("Archivo: " + config.getString("file"));
                reader.read(config.getString("file"));
                memoizationFile(reader, config);
            }
            if (config.contains("directory")) {
                File carpeta = new File(config.getString("directory"));
                if (carpeta == null){
                    System.err.println("El directorio no existe");
                    System.exit(1);
                }
                for(File archivo : carpeta.listFiles()) {
                    System.out.println("Archivo: "+archivo.getName());
                    reader.clear();
                    reader.read(carpeta.getName() + "/" + archivo.getName());
                    memoizationFile(reader, config);
                }
            }
        }
        
        if(config.getBoolean("check")){
            System.out.println("Comprobando ambos algoritmos");
            if (config.contains("file")) {
                System.out.println("Archivo: " + config.getString("file"));
                reader.read(config.getString("file"));
                checkFile(reader, config);
            }
            if (config.contains("directory")) {
                File carpeta = new File(config.getString("directory"));
                if (carpeta == null){
                    System.err.println("El directorio no existe");
                    System.exit(1);
                }
                for(File archivo : carpeta.listFiles()) {
                    System.out.println("Archivo: " + archivo.getName());
                    reader.clear();
                    reader.read(carpeta.getName() + "/" + archivo.getName());
                    checkFile(reader, config);
                }
            }
        }
    }

    public static JSAP parsePreparation() {
            try {
                    JSAP jsaper = new JSAP();
                    jsaper.registerParameter(new FlaggedOption("directory")
                                    .setStringParser(JSAP.STRING_PARSER)
                                    .setShortFlag('d')
                                    .setLongFlag("directory")
                                    .setHelp("Directory (Process many files)"));
                    jsaper.registerParameter(new FlaggedOption("file")
                                    .setStringParser(JSAP.STRING_PARSER)
                                    .setShortFlag('f')
                                    .setLongFlag("file")
                                    .setHelp("File (Process a single file)"));
                    jsaper.registerParameter(new Switch("length")
                                    .setShortFlag('l')
                                    .setLongFlag("length")
                                    .setHelp("Display the LCS lenght"));
                    jsaper.registerParameter(new Switch("subsequence")
                                    .setShortFlag('s')
                                    .setLongFlag("room")
                                    .setHelp("Display the LCS taken"));
                    jsaper.registerParameter(new Switch("time")
                                    .setShortFlag('t')
                                    .setLongFlag("time")
                                    .setHelp("Display time"));
                    jsaper.registerParameter(new Switch("check")
                                    .setLongFlag("check")
                                    .setHelp("Checks result by comparing memoization and tabulation"));
                    jsaper.registerParameter(new Switch("tabulation")
                                    .setLongFlag("st")
                                    .setHelp("Solve it with tabulation"));
                    jsaper.registerParameter(new Switch("memoization")
                                    .setLongFlag("sm")
                                    .setHelp("Solve it with tabulation"));

                    return jsaper;
            }catch(JSAPException ex) {
                    Logger.getLogger(LCS.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }

    private static void tabulationFile(Reader reader, JSAPResult config) {
        l1 = reader.getL1();
        l2 = reader.getL2();
        sequence_1 = reader.getSequence_1();
        sequence_2 = reader.getSequence_2();
        long begin = System.currentTimeMillis();
        String res = Tabulation.tabulation(sequence_1, sequence_2);
        long end = System.currentTimeMillis();
        
                
        if (config.getBoolean("subsequence")) {
            System.out.println(res);
        }

        if (config.getBoolean("length")) {
            System.out.println(res.length());
        }

        if (config.getBoolean("time")) {
            System.out.println("Time: " + (end - begin) + " ms");
        }
        
    }
    
    private static void memoizationFile(Reader reader, JSAPResult config) {
        l1 = reader.getL1();
        l2 = reader.getL2();
        sequence_1 = reader.getSequence_1();
        sequence_2 = reader.getSequence_2();
        long begin = System.currentTimeMillis();
        String res = Memoization.memoization(sequence_1, sequence_2);
        long end = System.currentTimeMillis();

        if (config.getBoolean("subsequence")) {
            System.out.println(res);
        }

        if (config.getBoolean("length")) {
            System.out.println(res.length());
        }

        if (config.getBoolean("time")) {
            System.out.println("Time: " + (end - begin) + " ms");
        }
    }

    private static void checkFile(Reader reader, JSAPResult config) {
        l1 = reader.getL1();
        l2 = reader.getL2();
        sequence_1 = reader.getSequence_1();
        sequence_2 = reader.getSequence_2();
        long begin = System.currentTimeMillis();
        String resMem = Memoization.memoization(sequence_1, sequence_2);
        String resTab = Tabulation.tabulation(sequence_1, sequence_2);
        long end = System.currentTimeMillis();
        if(resMem.length() == resTab.length()){
            System.out.println("Ambas subcadenas son la subcadena más larga");
        }else {
            System.out.println("Los algoritmos no coinciden");
        }

        if (config.getBoolean("subsequence")) {
            System.out.println("Cadena Memoization: "+ resMem + "\nCadena Tabulation: " + resTab);
        }

        if (config.getBoolean("length")) {
            System.out.println("Longitud Cadena Memoization: "+ resMem.length() + "\nLongitud Cadena Tabulation: " + resTab.length());
        }

        if (config.getBoolean("time")) {
            System.out.println("Time: " + (end - begin) + " ms");
        }
    }
}
