//Juan Diego Solorzano 18151
import com.sun.nio.sctp.Association;

import java.io.*;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws FileNotFoundException {


        Scanner sc = new Scanner(System.in);
        SplayTree sTree = new SplayTree();
        RedBlackTree rTree = new RedBlackTree();

        try{
            File file = new File("freedict-eng-spa.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            File file2 = new File("texto.txt");
            FileReader fr2 = new FileReader(file);
            BufferedReader br2 = new BufferedReader(fr);
                System.out.println("\nQue implementacion desea usar?\n");
                System.out.println("1. SplayTree ");
                System.out.println("2. RBT ");
                System.out.println("3. Salir");

                int ingreso = sc.nextInt();
                if (ingreso == 1){
                    sTree.add("freedict-eng-spa.txt", sTree);
                    String res =sTree.traducir("texto.txt", sTree);
                    System.out.println(res);
                }
                else if(ingreso == 2){
                    rTree.add("freedict-eng-spa.txt", rTree);
                    String res =rTree.traducir("texto.txt");
                    System.out.println(res);


                }else if(ingreso == 3){
                    System.out.println("Gracias por utilizar el programa");
                }
            } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
