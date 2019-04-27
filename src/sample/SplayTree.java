
// Juan Diego Solorzano 18151
// Extraido de https://www.sanfoundry.com/java-program-implement-splay-tree/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SplayTree {
    TreeNode root;

    public SplayTree() {

    }

    public void insert(Entry entry) {
        TreeNode newNode = new TreeNode(entry);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode lastNonNullNode = findLastNodeInSearch(entry.getKey());
            if (entry.compareTo(lastNonNullNode.getEntry()) > 0) {
                //New entry is bigger, hence it will be a right child
                lastNonNullNode.setRightChild(newNode);
            } else {
                //Equal or lesser entries go to the left
                lastNonNullNode.setLeftChild(newNode);
            }
            newNode.setParent(lastNonNullNode);
            rebalanceTree(newNode);
        }

    }

    public Entry find(String key) {
        TreeNode lastNodeInSearch = findLastNodeInSearch(key);
        rebalanceTree(lastNodeInSearch);
        Entry closestEntry = lastNodeInSearch.getEntry();

        return closestEntry;
    }

    private void rebalanceTree(TreeNode nodeToSplay) {
        TreeNode parent = nodeToSplay.getParent();
        TreeNode grandParent = null;
        if (parent != null) {

            grandParent = parent.getParent();
        }

        if (grandParent == null) {
            rotateThruParent(nodeToSplay);
        } else {
            splayNodeWithGrandParent(nodeToSplay);
        }

    }

    private void splayNodeWithGrandParent(TreeNode nodeToSplay) {
        TreeNode parent = nodeToSplay.getParent();
        TreeNode grandParent = parent.getParent();

        if (nodeToSplay.findWhichChild().equals(TreeNode.WhichChild.LEFTCHILD) && parent.findWhichChild().equals(TreeNode.WhichChild.LEFTCHILD)
                ||
                nodeToSplay.findWhichChild().equals(TreeNode.WhichChild.RIGHTCHILD) && parent.findWhichChild().equals(TreeNode.WhichChild.RIGHTCHILD)) {
            //Left child of a left child or right child or a right child
            rotateThruParent(parent);
        } else {
            rotateThruParent(nodeToSplay);
        }

        rotateThruParent(nodeToSplay);

        if (nodeToSplay.hasGrandParent()) {
            splayNodeWithGrandParent(nodeToSplay);
        } else if (nodeToSplay.getParent() != null) {
            rotateThruParent(nodeToSplay);
        }
    }

    private TreeNode findLastNodeInSearch(Comparable key) {
        TreeNode node = root;
        TreeNode lastNonNullNode = node;

        while (node != null) {
            if (key.compareTo(lastNonNullNode.getEntry().getKey()) == 0) {
                return lastNonNullNode;
            }

            if (key.compareTo(node.getEntry().getKey()) > 0) {
                //Entry is bigger. Go right to find a match
                node = node.getRightChild();
            } else {
                //we have decided to enter equal keys in the left side
                //hence we go left for less than or equal
                node = node.getLeftChild();
            }

            if (node != null) {
                lastNonNullNode = node;
            }

        }
        return lastNonNullNode;
    }

    public void printSorted() {
        root.inOrder();
    }

    //todo move this to the tree node class
    private void rotateThruParent(TreeNode splayNode) {

        //Preserve all references to nodes
        TreeNode parent = splayNode.getParent();
        TreeNode grandParent = parent.getParent();
        TreeNode.WhichChild whichChildIsParent = TreeNode.WhichChild.NOT_A_CHILD;
        if (grandParent != null) {
            whichChildIsParent = parent.findWhichChild();
        }


        //Rotate splayNode thru parent
        if (splayNode.findWhichChild().equals(TreeNode.WhichChild.LEFTCHILD)) {
            TreeNode rightChildOfSplayNode = splayNode.getRightChild();
            parent.setLeftChild(rightChildOfSplayNode);
            if (rightChildOfSplayNode != null) {
                rightChildOfSplayNode.setParent(parent);
            }


            splayNode.setRightChild(parent);
        } else if (splayNode.findWhichChild().equals(TreeNode.WhichChild.RIGHTCHILD)) {
            TreeNode leftChildOfSplayNode = splayNode.getLeftChild();

            parent.setRightChild(leftChildOfSplayNode);
            if (leftChildOfSplayNode != null) {
                leftChildOfSplayNode.setParent(parent);
            }


            splayNode.setLeftChild(parent);
        }

        parent.setParent(splayNode);

        //set references to and from grand parent
        if (grandParent != null) {
            if (whichChildIsParent == TreeNode.WhichChild.LEFTCHILD) {
                grandParent.setLeftChild(splayNode);
            } else if (whichChildIsParent == TreeNode.WhichChild.RIGHTCHILD) {
                grandParent.setRightChild(splayNode);
            }
            splayNode.setParent(grandParent);
        } else {
            splayNode.setParent(null);
            root = splayNode;
        }

    }

    public void add(String archivo, SplayTree tree) throws IOException {
        File file = new File(archivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String text;
        while ((text = br.readLine()) != null) {
            text = text.toLowerCase();
            String[] tx = text.split("");
            String ch = "";
            String[] k = {};
            for (int i = 0; i < tx.length; i++) {
                if ((!tx[i].equals(" ") && !(tx[i].equals(",")) && !(tx[i].equals(";")))) {
                    ch = ch + tx[i];
                }
                if (!ch.equals("") && (tx[i].equals(" "))) {
                    if (k.length == 1) {
                        k[0] = ch;
                    }else if(k.length == 2){
                        k[1] = ch;
                        tree.insert(new Entry(k[0], k[1]));
                        ch = "";
                    }


                }
                }
            }

        }

    public String traducir(String nombre, SplayTree tree) throws IOException {

        File file = new File(nombre);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String[] translated = null;
        int n= 0;
        while((line = br.readLine()) != null){
            translated = line.split(" ");
            for (String node:translated) {
                node = node.toLowerCase();
                translated[n] = node;
                n++;
            }
        }
        String oracion = "";
        for (int i=0; translated[i] != null; i++){
            if (tree.find(translated[i]) != null){
                oracion += tree.find(translated[i]);
            }
            else{
                oracion += "*" + translated[i] + "*";
            }
        }

        return oracion;
    }


}