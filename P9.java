import java.util.ArrayList;
import java.util.Scanner;

public class P9{
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        ArrayList<Integer> output = new ArrayList<>();
        for (int i=0; i<size; i++){
            int choice = input.nextInt();
            if(choice == 1){
                int element = input.nextInt();
                tree.insert(element);
                Node node = tree.searchTree(element);
                tree.getIndex(node);
            }
            else if(choice == 2){
                int element = input.nextInt();
                Node node = tree.searchTree(element);
                output.add(node.index);
            }
        }
        for (Integer x : output) {
            System.out.print(x + " ");
        }
        input.close();
    }
}
// Implementing Red-Black Tree in Java

class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    int color;
    static int count = 0;
    int index = -1;
  }
  
  class RedBlackTree {
    private Node root;
    private Node TNULL;

    public void getIndex(Node node){
      if(node == TNULL){
        node.index = -1;
      }
      if(node.data > root.data){
        Node.count = root.index;
        inOrderHelper(this.root.right);
      }
      else{
          inOrderHelper(this.root);
      }
      Node.count = 0;
    }

    // Inorder
    private void inOrderHelper(Node node) {
      if (node != TNULL) {
          inOrderHelper(node.left);
          Node.count ++;
          node.index = Node.count;
          inOrderHelper(node.right);
      }
  }
  
    // Search the tree
    private Node searchTreeHelper(Node node, int key) {
      if (key == node.data) {
        return node;
      }
      if (node == TNULL){
          return node;
      }
  
      if (key < node.data) {
        return searchTreeHelper(node.left, key);
      }
      return searchTreeHelper(node.right, key);
    }
  
    // Balance the node after insertion
    private void fixInsert(Node k) {
      Node u;
      while (k.parent.color == 1) {
        if (k.parent == k.parent.parent.right) {
          u = k.parent.parent.left;
          if (u.color == 1) {
            u.color = 0;
            k.parent.color = 0;
            k.parent.parent.color = 1;
            k = k.parent.parent;
          } else {
            if (k == k.parent.left) {
              k = k.parent;
              rightRotate(k);
            }
            k.parent.color = 0;
            k.parent.parent.color = 1;
            leftRotate(k.parent.parent);
          }
        } else {
          u = k.parent.parent.right;
  
          if (u.color == 1) {
            u.color = 0;
            k.parent.color = 0;
            k.parent.parent.color = 1;
            k = k.parent.parent;
          } else {
            if (k == k.parent.right) {
              k = k.parent;
              leftRotate(k);
            }
            k.parent.color = 0;
            k.parent.parent.color = 1;
            rightRotate(k.parent.parent);
          }
        }
        if (k == root) {
          break;
        }
      }
      root.color = 0;
    }
  
    public RedBlackTree() {
      TNULL = new Node();
      TNULL.color = 0;
      TNULL.left = null;
      TNULL.right = null;
      root = TNULL;
    }

    public void inorder() {
      inOrderHelper(this.root);
    }

    public Node searchTree(int k) {
      return searchTreeHelper(this.root, k);
    }
  
    public void leftRotate(Node x) {
      Node y = x.right;
      x.right = y.left;
      if (y.left != TNULL) {
        y.left.parent = x;
      }
      y.parent = x.parent;
      if (x.parent == null) {
        this.root = y;
      } else if (x == x.parent.left) {
        x.parent.left = y;
      } else {
        x.parent.right = y;
      }
      y.left = x;
      x.parent = y;
    }
  
    public void rightRotate(Node x) {
      Node y = x.left;
      x.left = y.right;
      if (y.right != TNULL) {
        y.right.parent = x;
      }
      y.parent = x.parent;
      if (x.parent == null) {
        this.root = y;
      } else if (x == x.parent.right) {
        x.parent.right = y;
      } else {
        x.parent.left = y;
      }
      y.right = x;
      x.parent = y;
    }
  
    public void insert(int key) {
      Node node = new Node();
      node.parent = null;
      node.data = key;
      node.left = TNULL;
      node.right = TNULL;
      node.color = 1;
      node.index = 0;
  
      Node y = null;
      Node x = this.root;
  
      while (x != TNULL) {
        y = x;
        if (node.data < x.data) {
          x = x.left;
        } else {
          x = x.right;
        }
      }
  
      node.parent = y;
      if (y == null) {
        root = node;
      } else if (node.data < y.data) {
        y.left = node;
      } else {
        y.right = node;
      }
  
      if (node.parent == null) {
        node.color = 0;
        return;
      }
  
      if (node.parent.parent == null) {
        return;
      }
      fixInsert(node);
    }
  }