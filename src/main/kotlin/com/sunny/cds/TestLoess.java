package com.sunny.cds;

import java.io.*;
import java.util.Random;

import org.apache.commons.math3.analysis.interpolation.LoessInterpolator;


public class TestLoess {
    private String RScript = "/usr/local/bin/Rscript";

    private static class ConsummeInputStream
            extends Thread {
        private InputStream in;

        ConsummeInputStream(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                int c;
                while ((c = this.in.read()) != -1)
                    System.err.print((char) c);
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    TestLoess() {

    }

    private void run() throws Exception {
        int num = 100;
        Random rand = new Random(0L);
        double x[] = new double[num];
        double y[] = new double[x.length];
        for (int i = 0; i < x.length; ++i) {
            x[i] = rand.nextDouble() + (i > 0 ? x[i - 1] : 0);
            y[i] = Math.sin(i) * 100;
        }
        LoessInterpolator loessInterpolator = new LoessInterpolator(
                0.75,//bandwidth,
                2//robustnessIters

        );
        double y2[] = loessInterpolator.smooth(x, y);

        Process proc = Runtime.getRuntime().exec(
                new String[]{RScript, "-"}
        );
        ConsummeInputStream errIn = new ConsummeInputStream(proc.getErrorStream());
        BufferedReader stdin = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        PrintStream out = new PrintStream(proc.getOutputStream());
        errIn.start();
        out.print("T<-as.data.frame(matrix(c(");
        for (int i = 0; i < x.length; ++i) {
            if (i > 0) out.print(',');
            out.print(x[i] + "," + y[i]);
        }
        out.println("),ncol=2,byrow=TRUE))");
        out.println("colnames(T)<-c('x','y')");
        out.println("T2<-loess(y ~ x, T)");
        out.println("write.table(residuals(T2),'',col.names= F,row.names=F,sep='\\t')");
        out.flush();
        out.close();
        double y3[] = new double[x.length];
        for (int i = 0; i < y3.length; ++i) {
            y3[i] = Double.parseDouble(stdin.readLine());
        }
        System.out.println("X\tY\tY.java\tY.R");
        for (int i = 0; i < y3.length; ++i) {
            System.out.println("" + x[i] + "\t" + y[i] + "\t" + y2[i] + "\t" + y3[i]);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new TestLoess().run();
    }
}