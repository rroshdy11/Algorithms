import java.util.ArrayList;
import java.util.Scanner;

public class P10{
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> output = new ArrayList<>();
        while (true){
            int choice = input.nextInt();
            if(choice == 1){
                int element = input.nextInt();
                tree.insert(element);
                Node node = tree.searchTree(element);
                tree.getIndex(node);
            }
            else if(choice == 2){
                int element = input.nextInt();
                tree.deleteNode(element);
                if(element > tree.getRoot().data){
                    tree.getIndex(tree.getRoot().right);
                }else{
                    tree.getIndex(tree.getRoot());
                }
            }
            else if(choice == 3){
                int element = input.nextInt();
                Node node = tree.searchTree(element);
                output.add(node.index);
            }
            else if(choice == 4){
                int element = input.nextInt();
                output.add(tree.getData(element));
            }
            else if(choice == -1){
                break;
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
    
    public int getData(int index){
        Node itr = root;
        while(itr != TNULL){
            if(index > itr.index){
                itr = itr.right;
            }
            else if(index < itr.index){
                itr = itr.left;
            }
            else if(index == itr.index){
                return itr.data;
            }
        }
        return -1;
    }
  
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
  
    // Balance the tree after deletion of a node
    private void fixDelete(Node x) {
      Node s;
      while (x != root && x.color == 0) {
        if (x == x.parent.left) {
          s = x.parent.right;
          if (s.color == 1) {
            s.color = 0;
            x.parent.color = 1;
            leftRotate(x.parent);
            s = x.parent.right;
          }
  
          if (s.left.color == 0 && s.right.color == 0) {
            s.color = 1;
            x = x.parent;
          } else {
            if (s.right.color == 0) {
              s.left.color = 0;
              s.color = 1;
              rightRotate(s);
              s = x.parent.right;
            }
  
            s.color = x.parent.color;
            x.parent.color = 0;
            s.right.color = 0;
            leftRotate(x.parent);
            x = root;
          }
        } else {
          s = x.parent.left;
          if (s.color == 1) {
            s.color = 0;
            x.parent.color = 1;
            rightRotate(x.parent);
            s = x.parent.left;
          }
  
          if (s.right.color == 0 && s.right.color == 0) {
            s.color = 1;
            x = x.parent;
          } else {
            if (s.left.color == 0) {
              s.right.color = 0;
              s.color = 1;
              leftRotate(s);
              s = x.parent.left;
            }
  
            s.color = x.parent.color;
            x.parent.color = 0;
            s.left.color = 0;
            rightRotate(x.parent);
            x = root;
          }
        }
      }
      x.color = 0;
    }
  
    private void rbTransplant(Node u, Node v) {
      if (u.parent == null) {
        root = v;
      } else if (u == u.parent.left) {
        u.parent.left = v;
      } else {
        u.parent.right = v;
      }
      v.parent = u.parent;
    }
  
    private void deleteNodeHelper(Node node, int key) {
      Node z = TNULL;
      Node x, y;
      while (node != TNULL) {
        if (node.data == key) {
          z = node;
        }
  
        if (node.data <= key) {
          node = node.right;
        } else {
          node = node.left;
        }
      }
  
      if (z == TNULL) {
        return;
      }
  
      y = z;
      int yOriginalColor = y.color;
      if (z.left == TNULL) {
        x = z.right;
        rbTransplant(z, z.right);
      } else if (z.right == TNULL) {
        x = z.left;
        rbTransplant(z, z.left);
      } else {
        y = minimum(z.right);
        yOriginalColor = y.color;
        x = y.right;
        if (y.parent == z) {
          x.parent = y;
        } else {
          rbTransplant(y, y.right);
          y.right = z.right;
          y.right.parent = y;
        }
  
        rbTransplant(z, y);
        y.left = z.left;
        y.left.parent = y;
        y.color = z.color;
      }
        if (yOriginalColor == 0) {
            fixDelete(x);
        }
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
  
    public Node minimum(Node node) {
      while (node.left != TNULL) {
        node = node.left;
      }
      return node;
    }
  
    public Node maximum(Node node) {
      while (node.right != TNULL) {
        node = node.right;
      }
      return node;
    }
  
    public Node successor(Node x) {
        if(x == TNULL){
            return x;
        }
      if (x.right != TNULL) {
        return minimum(x.right);
      }
  
      Node y = x.parent;
      while (y != TNULL && x == y.right) {
        x = y;
        y = y.parent;
      }
      return y;
    }
  
    public Node predecessor(Node x) {
        if(x == TNULL){
            return x;
        }
      if (x.left != TNULL) {
        return maximum(x.left);
      }
  
      Node y = x.parent;
      while (y != TNULL && x == y.left) {
        x = y;
        y = y.parent;
      }
  
      return y;
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
        node.index = 1;
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
  
    public Node getRoot() {
      return this.root;
    }
  
    public void deleteNode(int data) {
      deleteNodeHelper(this.root, data);
    }
  }