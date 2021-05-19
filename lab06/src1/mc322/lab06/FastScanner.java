package mc322.lab06;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;  

public class FastScanner {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st=new StringTokenizer("");
      public String next() {
            while (!st.hasMoreTokens()){
                  try { st = new StringTokenizer(br.readLine()); } 
                  catch (IOException e) {}
            }
            return st.nextToken();
    }
    public int nextInt() { return Integer.parseInt(next()); }
    public long nextLong() { return Long.parseLong(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
}
