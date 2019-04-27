package sample;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        SplayTree sTree = new SplayTree();
        RedBlackTree rTree = new RedBlackTree();

        System.out.println("\nQue implementacion desea usar?\n");
        System.out.println("1. SplayTree ");
        System.out.println("2. RBT ");
        System.out.println("3. Salir");

        int ingreso = sc.nextInt();
        if (ingreso == 1){
            sTree.printSorted();
        }
        else if(ingreso == 2){
            rTree.printTree(root);


        }else if(ingreso == 3){
            System.out.println("Gracias por utilizar el programa");
        }

    }
}
