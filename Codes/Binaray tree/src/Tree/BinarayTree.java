package Tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Node {
	int value;
	Node  left;
	Node  right;
	public Node(int data) {
		value = data;
		left = right = null; 
	}
}

public class BinarayTree {
	Node root;
	public BinarayTree() {
		root = null;
	}
	private Node insertrec(Node root,int value) {
		if (root == null) {
			root = new Node(value);
			return root;
		}
		if(root.value<value)root.right = insertrec(root.right,value);
		else if (root.value>value)root.left = insertrec(root.left,value);
		return root;
	}
	public void insert(int value) {
		root = insertrec(root,value);
	}
	
	public void leafnodes() {
		System.out.println("LeafNodes: ");
		leafNodesrec(root);
		System.out.println();
	}
	private void leafNodesrec(Node node) {
		if(node==null) return;
		if(node.left==null && node.right==null) {
			System.out.print(node.value+" ");
			return;
		}
		leafNodesrec(node.left);
		leafNodesrec(node.right);
	}
	public void Search(int num) {
		Node parent = null;
		Node current = root;
		while (current!=null) {
			if (num == current.value) {
				if (parent==null) System.out.println("Root is your value");
				else if (parent.left==current) System.out.println("Your value is present in the LEFT Subtree of "+parent.value);
				else if (parent.right==current) System.out.println("Your value is present in the RIGHT Subtree of "+parent.value);
				return;
			}
			parent = current;
			if (num <current.value) {
				current = current.left;
			} else { current = current.right;}
		}
		System.out.println("Value not found");
	}
	public void SearchPandC(int num) {
		Node parent = null;
		Node Rchilde =null, Lchilde =null; 
		Node current = root;
		while (current!=null) {
			if (num == current.value) {
				Rchilde = current.right;
				Lchilde = current.left;
				String rightValue = (Rchilde == null) ? "null" : String.valueOf(Rchilde.value);
			    String leftValue  = (Lchilde == null) ? "null" : String.valueOf(Lchilde.value);
				if (parent==null) System.out.println("Root is your value:"+current.value+ " its left childe node is "+leftValue +" and the Right childe node is "+rightValue);
				else if (parent.left==current) System.out.println("Your value is " +current.value+ " its left childe node is "+leftValue +" and the Right childe node is "+rightValue+" The parent node is "+parent.value);
				return;
			}
			parent = current;
			if (num <current.value) {
				current = current.left;
			} else { current = current.right;}
		}
		System.out.println("Value not found");
	}
	
	public void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + "  ");
            inorderRec(root.right);
        }
    }
	
    public void PrintFile(String filename) {
    	try (PrintWriter writer = new PrintWriter(new FileWriter(filename))){
                save(root, writer);
    		
    	} catch (IOException e) {
    		 e.printStackTrace();
    	}
    	
    }
    
    public void save(Node node,PrintWriter writer) {
    	if(node == null)return;
    	writer.print(node.value + "  ");
    	save(node.left,writer);
    	save(node.right,writer);
    }
    
    public void ReadFile(String filename) {
    	File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Error: File not found -> " + file.getAbsolutePath());
            return;
        }
    	try (Scanner scanner = new Scanner(file)){
    		while (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                insert(value);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	public static void main(String[] args) {
		BinarayTree tree = new BinarayTree();
    
		tree.insert(45);
		tree.insert(32);
		tree.insert(76);
		tree.insert(81);
    	tree.insert(12);
    	tree.insert(25);
    	tree.insert(40);
    	tree.insert(42);
    	tree.insert(3);
    	tree.insert(4);
    	tree.insert(-17);
    	tree.insert(0);
    	tree.insert(99);
    	
    	tree.leafnodes();
    	tree.Search(81);
    	tree.SearchPandC(32);
    	tree.inorder();
    	
    	tree.PrintFile("tree.txt");
    	System.out.println("tree saved to tree.txt");
    	
    	BinarayTree Ntree = new BinarayTree();
    	Ntree.ReadFile("tree.txt");
    	
    	System.out.println("BST read from file (inorder):");
    	Ntree.inorder();
	}
}