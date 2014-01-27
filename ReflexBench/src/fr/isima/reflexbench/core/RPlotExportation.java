/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.reflexbench.core;

import fr.isima.reflexbench.architecture.ReflectRequestType;
import java.io.File;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rosuda.JRI.Rengine;

/**
 *
 * @author Gaetan
 */
public class RPlotExportation extends ExportStrategy {
    
    private static final String DEFAULT_R_DIRECTORY = "RExportation";
    private static final int DEFAULT_IMG_WIDTH = 16;
    private static final int DEFAULT_IMG_HEIGHT = 8;
    protected Rengine engine;
    
    static{
        System.loadLibrary("jri");
    }
    
    public RPlotExportation() {
        
        String[] rArgs = {"--vanilla"};
        engine = new Rengine(rArgs,false,null);
        
        if(!engine.waitForR()) {
            Logger.getGlobal().log(Level.SEVERE, "Problem with Rengine");
        }     
    }
    
    @Override
    public Boolean exportBenchmarksResult(Collection<BenchTry> benchTries,String fileOrDirectoryPath) {
        Boolean result = false;
        String directoryPath = fileOrDirectoryPath;
        
        //Handle path
        if(directoryPath == null) {
            directoryPath = DEFAULT_R_DIRECTORY;
            File dir = new File(directoryPath);
            dir.mkdir();
        }
        
        File file = new File(directoryPath);
        if(file.exists() && !file.isDirectory()) {
            directoryPath = DEFAULT_R_DIRECTORY;
            File dir = new File(directoryPath);
            dir.mkdir();
        } else if(!file.exists()){
            file.mkdir();
        }
        
        //Generate CSV file to load in R
        ExportStrategy exp = new CSVExportation();
        String csvPathName = file.getAbsolutePath()+ File.separator + "data.csv";
        Boolean res = exp.exportBenchmarksResult(benchTries,csvPathName);
        
        if(res) {
            result = processRPlotGeneration(csvPathName,file);
        }
        
        return result;
    }

    private Boolean processRPlotGeneration(String csvPathName, File directory) {
        Boolean sucess = true;
        
        if(!engine.waitForR()) {
            Logger.getGlobal().log(Level.SEVERE, "Can not get R ready");
            sucess = false;
        }
        if(sucess) {
            
            engine.eval("library(ggplot2)"); // load ggplot2

            String pattern = File.separator;
            String csvPathNameSanitized = csvPathName.replace(pattern, "\\" + File.separator);
            engine.eval("filepath <- \""+ csvPathNameSanitized + "\"");
            engine.eval("benchData <- read.table(file=filepath,header=T,sep=\";\")");

            engine.eval("meanData <- aggregate(list(time=benchData$time),list(difficulty=benchData$difficulty,type=benchData$type,reflectAPIName=benchData$reflectAPIName),mean)");

            //global chart
            engine.eval("qplot(main=\"Time to process reflection / API.\",data=meanData,x=reflectAPIName,y=time,fill=reflectAPIName) + geom_bar(stat=\"identity\",width=.5, position=\"dodge\") + coord_flip() + facet_wrap(~type)");
            String directoryNameSanitized = (directory.getAbsolutePath()+ File.separator).replace(pattern, "\\"+File.separator);
            engine.eval("ggsave(\""+directoryNameSanitized+"main.png\",width="+DEFAULT_IMG_WIDTH+",height="+DEFAULT_IMG_HEIGHT+")");


            //bar charts for each type
            for(ReflectRequestType currentType : ReflectRequestType.values()) {
                engine.eval("qplot(main=\""+currentType+" graph.\", data=meanData[meanData$type==\""+currentType+"\",],x=reflectAPIName,y=time,ylab=\"Time in nanosecond\",fill=reflectAPIName) + geom_bar(stat=\"identity\",width=.5, position=\"dodge\") + coord_flip()");
                engine.eval("ggsave(\""+directoryNameSanitized+currentType+".png\",width="+DEFAULT_IMG_WIDTH+",height="+DEFAULT_IMG_HEIGHT+")");
            }
            
            
            
            /*
            //error bars for each type
            for(ReflectRequestType currentType : ReflectRequestType.values()) {
                engine.eval("qplot(main=\""+currentType+" values repartition.\", data=benchData[benchData$type==\""+currentType+"\",],x=reflectAPIName,y=time,ylab=\"Time in nanosecond\",fill=reflectAPIName)+ geom_boxplot(outlier.size=0) + coord_flip()");
                engine.eval("ggsave(\""+directoryNameSanitized+currentType+"Errors.png\",width="+DEFAULT_IMG_WIDTH+",height="+DEFAULT_IMG_HEIGHT+")");
            }*/
            
            engine.end();
        }
        return sucess;
    }
    
}
