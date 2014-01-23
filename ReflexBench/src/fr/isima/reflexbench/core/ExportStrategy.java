/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.reflexbench.core;

import java.util.Collection;

/**
 *
 * @author Gaetan
 */
public abstract class ExportStrategy {
    
    public abstract Boolean exportBenchmarksResult(Collection<BenchTry> benchTries,String fileOrDirectoryPath);    
}
