package org.os;


import org.os.core.Runner;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 1) throw new Exception("Dependeny txt file not provided ...");
        Runner.generateProject(args[0]);
    }
}