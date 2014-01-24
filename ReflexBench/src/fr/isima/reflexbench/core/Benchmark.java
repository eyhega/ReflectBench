/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.reflexbench.core;

import fr.isima.reflexbench.architecture.ApacheReflect;
import fr.isima.reflexbench.architecture.JavaLangReflect;
import fr.isima.reflexbench.architecture.ReflectAPI;
import fr.isima.reflexbench.architecture.ReflectRequestType;
import fr.isima.reflexbench.samples.SampleEmployee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaetan
 */
public class Benchmark {
    
    private final static int NB_REPLICATIONS = 10000;
    private List<ReflectAPI> reflectEngines;
    
    public Benchmark() {
        reflectEngines = new ArrayList<>();
        reflectEngines.add(new JavaLangReflect());
        reflectEngines.add(new ApacheReflect());
    }
    
    private BenchTry bench(ReflectAPI motor, BenchDifficulty difficulty,ReflectRequestType type,Object obj) {
        BenchTry benchData = new BenchTry();
        Long beginTime;
        Long endTime;
        
        benchData.type = type;
        benchData.reflectAPIName = motor.getName();
        benchData.difficulty = difficulty;
        
        
        beginTime = System.nanoTime();
        motor.process(type, obj);
        endTime = System.nanoTime();
        
        benchData.time = endTime - beginTime;
        
        return benchData;
    }

    public List<BenchTry> launchAllBenchmarks() {
        List<BenchTry> benchs = new ArrayList<>();
        BenchTry result = null;
        SampleEmployee sample = new SampleEmployee();
        sample.fillObject();
        
        ReflectRequestType[] types = ReflectRequestType.values();
        for(ReflectRequestType currentType : types) {
            for(ReflectAPI engine : reflectEngines) {
                for(int i = 0 ; i < NB_REPLICATIONS ; ++i) {
                    Runtime.getRuntime().traceMethodCalls(true);
                    result = bench(engine, BenchDifficulty.EASY, currentType, sample);
                    Runtime.getRuntime().traceMethodCalls(false);
                    benchs.add(result);
                }
            }
        }
        
        return benchs;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        List<BenchTry> data = benchmark.launchAllBenchmarks();
        
        ExportStrategy export = new RPlotExportation();
        export.exportBenchmarksResult(data, null);
    }
    
}
