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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[ API=").append(reflectAPIName).append(", Type =").append(type.toString()).append(", Difficulty=").append(difficulty.toString());
        sb.append(", time=").append(time).append("ns ]");
        
        return sb.toString();
    }
}
