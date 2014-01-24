/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.reflexbench.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gaetan
 */
public class CSVExportation extends ExportStrategy {

    
    private static final String DEFAULT_CSV_PREFIXNAME = "exportation";
    
    @Override
    public Boolean exportBenchmarksResult(Collection<BenchTry> benchTries,String fileOrDirectoryPath) {
        Boolean result = false;
        File file = null;
        if(fileOrDirectoryPath != null) {
            file = new File(fileOrDirectoryPath);
            String filePath = fileOrDirectoryPath;
            if(file.isDirectory()) {
                DateFormat format = new SimpleDateFormat("HHmmss-dd-MM-yyyy");
                Date currentDate = new Date();
                filePath = fileOrDirectoryPath + File.separator + DEFAULT_CSV_PREFIXNAME +format.format(currentDate) + ".csv";
            }
        }
        else {
            file = new File(DEFAULT_CSV_PREFIXNAME + ".csv");
        }
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVExportation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pw != null) {
            
            //Write headers
            pw.println(BenchTry.getTagsToCSV(";"));
            
            //Write Data
            for(BenchTry currentTry : benchTries) {
                pw.println(currentTry.toCSV(";"));
            }
            
            pw.flush();
            pw.close();
            result = true;
        }
        
        return result;
    }
    
}
