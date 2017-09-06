package org.virgil.jdk;

/**
 * Created by Virgil on 2017/8/24.
 */
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int data) {
        Node newNode = new Node(data);
        //只有根节点
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (data < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public boolean find(int data) {
        Node currnet = root;
        while (currnet != null) {
            if (currnet.data == data) {
                return true;
            } else if (currnet.data > data) {
                currnet = currnet.left;
            } else {
                currnet = currnet.right;
            }
        }
        return false;
    }

    public void display() {
        this.display(root);
    }

    private void display(Node node) {
        if (node != null) {
            display(node.left);
            System.out.print(node.data + " ");
            display(node.right);
        }
    }

    /*
     *  查找后继
     */
    private Node getSuccessor(Node node) {
        Node successor = null;
        Node sucessorParent = null;
        Node current = node.right;
        while (current != null) {
            sucessorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right) {
            //如果还有右节点的话
            sucessorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    public boolean delete(int data) {
        Node current = root;
        Node parent = root;
        //记录被找到的节点是父节点的左子节点还是右子节点
        boolean isLeftChild = false;
        while (current.data != data) {
            parent = current;
            if (current.data > data) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        //如果待删除的节点没有任何子节点
        //直接将该节点的原本指向该节点的指针设置为null
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            //如果待删除的节点有一个子节点,且其为左子节点
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {
            //寻找右子树中的最小值
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }

        return true;
    }

    public void preOrder() {
        this.preOrderAction(root);
    }

    protected void preOrderAction(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderAction(node.left);
            preOrderAction(node.right);
        }
    }


    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = null;
        binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(3);
        binarySearchTree.insert(8);
        binarySearchTree.insert(1);
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(2);
        binarySearchTree.insert(10);
        binarySearchTree.insert(9);
        binarySearchTree.insert(20);
        binarySearchTree.insert(25);
        binarySearchTree.insert(15);
        binarySearchTree.insert(16);
        System.out.println("原始的树 : ");
        binarySearchTree.display();
        System.out.println("");
        System.out.println("删除值为2的节点 : " + binarySearchTree.delete(2));
        binarySearchTree.display();
        System.out.println("");
        System.out.println("删除有一个子节点值为4的节点 : " + binarySearchTree.delete(4));
        binarySearchTree.display();
        System.out.println("");
        System.out.println("删除有两个子节点的值为10的节点 : " + binarySearchTree.delete(10));
        binarySearchTree.display();
        System.out.println("");
    }
}
