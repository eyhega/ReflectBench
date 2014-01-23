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
        File file = new File(fileOrDirectoryPath);
        String filePath = fileOrDirectoryPath;
        if(file.isDirectory()) {
            DateFormat format = new SimpleDateFormat("HHmmss-dd-MM-yyyy");
            Date currentDate = new Date();
            filePath = fileOrDirectoryPath + File.pathSeparator + DEFAULT_CSV_PREFIXNAME +format.format(currentDate) + ".csv";
        }
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVExportation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pw != null) {
            
            Field[] headers = BenchTry.class.getDeclaredFields();
            
            //Write headers
            pw.print(headers[0].getName());
            for(int i = 1 ; i < headers.length ; ++i) {
                pw.print(";" + headers[i].getName());
            }
            pw.print("\n");
            
            //Write Data
            for(BenchTry currentTry : benchTries) {
                try {
                    pw.print(headers[0].get(currentTry));
                    for(int index = 1 ; index < headers.length ; ++index) {
                       pw.print(";" + headers[index].get(currentTry));
                    }
                    pw.print("\n");
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(CSVExportation.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CSVExportation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            pw.flush();
            pw.close();
            result = true;
        }
        
        return result;
    }
    
}
