/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.reflexbench.core;

import java.util.Collection;
import org.rosuda.JRI.Rengine;

/**
 *
 * @author Gaetan
 */
public class RPlotExportation extends ExportStrategy {
    
    protected Rengine engine;
    
    public RPlotExportation() {
        super();
        engine = Rengine.getMainEngine();
    }
    
    @Override
    public Boolean exportBenchmarksResult(Collection<BenchTry> benchTries,String fileOrDirectoryPath) {
        Boolean result = false;
        
        return result;
    }
    
}
