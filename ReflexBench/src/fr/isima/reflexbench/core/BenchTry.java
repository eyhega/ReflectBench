/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.reflexbench.core;

import fr.isima.reflexbench.architecture.ReflectRequestType;

/**
 *
 * @author gaetan
 */
public class BenchTry {
    public BenchDifficulty difficulty;
    public ReflectRequestType type;
    public double time;
    public String reflectAPIName;
    public Long memoryUsage;
    
    
    
    public static String getTagsToCSV(String separator) {
        StringBuilder sb = new StringBuilder();
        sb.append("difficulty").append(separator).append("type").append(separator)
                .append("time").append(separator).append("reflectAPIName").append(separator).append("memoryUsage");
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[ API=").append(reflectAPIName).append(", Type =").append(type.toString()).append(", Difficulty=").append(difficulty.toString());
        sb.append(", time=").append(time).append("ns, Memory usage=").append(memoryUsage).append(" ]");
        
        return sb.toString();
    }
    
    public String toCSV(String separator) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(difficulty).append(separator).append(type).append(separator)
                .append(time).append(separator).append(reflectAPIName)
                .append(separator).append(memoryUsage);
        
        return sb.toString();
    }
}
