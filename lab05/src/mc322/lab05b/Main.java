package mc322.lab05;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
     
public class Main {
      public static void main(String[] args){
            AppDama jogo = new AppDama();
            String EstadosDoJogo[];

            // java mc322.lab05.Main mc322/lab05/data/comandos.csv mc322/lab05/data/EstruturaTabuleiro.csv mc322/lab05/data/outputTabuleiro.csv

            String caminhoComandos = args[0];
            String caminhoTabuleiro = args[1];
            String caminhoOutput = args[2];
                              
            EstadosDoJogo = jogo.executaJogo(caminhoComandos, caminhoTabuleiro, caminhoOutput);
      }
}               

