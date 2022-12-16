import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class BinaryTree {

    private Node root;

    public boolean addNode(int value) {
        Node nodeToAdd = new Node(value);

        if (root == null) {
            root = nodeToAdd;
            return true;
        }

        Node currentNode = root;
        boolean added = false;

        while (!added) {
            if (value == currentNode.getValue()) {
                break;
            } else if (value < currentNode.getValue()) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(nodeToAdd);
                    added = true;
                } else {
                    currentNode = currentNode.getLeft();
                }
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(nodeToAdd);
                    added = true;
                } else {
                    currentNode = currentNode.getRight();
                }
            }
        }

        return added;
    }

    public boolean nodeExists(int value) {
        Node currentNode = root;
        boolean found = false;

        while (currentNode != null) {
            if (currentNode.getValue() == value) {
                found = true;
                break;
            } else if (currentNode.getValue() < value) {
                currentNode = currentNode.getRight();
            } else {
                currentNode = currentNode.getLeft();
            }
        }

        return found;
    }

    public boolean removeNode(int value) {
        boolean removed = false;
        Node currentNode = root;
        Node previousNode = null;

        while (currentNode != null && !removed) {
            if (currentNode.getValue() == value) {
                Node successor = findSuccessor(currentNode);
                if (previousNode.getValue() > currentNode.getValue()) {
                    previousNode.setLeft(successor);
                } else {
                    previousNode.setRight(successor);
                }
                removed = true;
            } else if (currentNode.getValue() < value) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            } else {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }
        }

        return removed;
    }

    private Node findSuccessor(Node nodeToRemove) {
        Node successor = nodeToRemove.getLeft();
        Node previous = nodeToRemove;
        boolean foundSuccessor = false;

        while (!foundSuccessor) {
            if (successor.getRight() == null) {
                foundSuccessor = true;
            } else {
                previous = successor;
                successor = successor.getRight();
            }
        }

        if (previous.getLeft() == successor) {
            previous.setLeft(null);
        } else {
            previous.setRight(null);
        }

        nodeToRemove.setLeft(null);
        nodeToRemove.setRight(null);

        return successor;
    }

    public void printTree() {
        Stack<Node> nodesToExpand = new Stack<>();
        nodesToExpand.push(root);

        while (!nodesToExpand.empty()) {
            Node currentNode = nodesToExpand.pop();

            System.out.print(currentNode.getValue() + " --> ");

            if (currentNode.getRight() != null) {
                nodesToExpand.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                nodesToExpand.push(currentNode.getLeft());
            }
        }
    }

    public void traverseInOrder() {
        Stack<Node> nodesToExpand = new Stack<>();
        nodesToExpand.push(root);
        HashSet<Node> visitedNodes = new HashSet<>();

        ArrayList<Integer> inorderNodeValues = new ArrayList<>();

        while(!nodesToExpand.empty()) {
            Node currentNode = nodesToExpand.pop();

            if (currentNode == null) {
                continue;
            }

            if (visitedNodes.contains(currentNode)) {
                inorderNodeValues.add(currentNode.getValue());
                continue;
            }

            nodesToExpand.push(currentNode.getRight());
            nodesToExpand.push(currentNode);
            nodesToExpand.push(currentNode.getLeft());

            visitedNodes.add(currentNode);
        }

        for (int value : inorderNodeValues) {
            System.out.print(value + " --> ");
        }
        System.out.println();
    }

    public void traverseInOrderRecursive() {
        ArrayList<Integer> inorderValues = new ArrayList<>();
        traverseInOrderRecursive(root, inorderValues);
        for (int value : inorderValues) {
            System.out.print(value + " --> ");
        }
        System.out.println();
    }

    private void traverseInOrderRecursive(Node currentNode, ArrayList<Integer> inorderValues) {
        if (currentNode == null) {
            return;
        }

        traverseInOrderRecursive(currentNode.getLeft(), inorderValues);

        inorderValues.add(currentNode.getValue());

        traverseInOrderRecursive(currentNode.getRight(), inorderValues);
    }

    public void traversePreOrder() {
        Stack<Node> nodesToExpand = new Stack<>();
        nodesToExpand.push(root);

        ArrayList<Integer> preOrderValues = new ArrayList<>();

        while (!nodesToExpand.empty()) {
            Node currentNode = nodesToExpand.pop();
            if (currentNode == null) {
                continue;
            }

            preOrderValues.add(currentNode.getValue());
            nodesToExpand.push(currentNode.getRight());
            nodesToExpand.push(currentNode.getLeft());
        }

        for (int value : preOrderValues) {
            System.out.print(value + " --> ");
        }

        System.out.println();
    }

    public void traversePreOrderRecursive() {
        ArrayList<Integer> nodeValues = new ArrayList<>();
        traversePreOrderRecursive(root, nodeValues);
        for (int value : nodeValues) {
            System.out.print(value + " --> ");
        }
        System.out.println();
    }

    private void traversePreOrderRecursive(Node currentNode, ArrayList<Integer> nodeValues) {
        if (currentNode == null) {
            return;
        }

        nodeValues.add(currentNode.getValue());

        traversePreOrderRecursive(currentNode.getLeft(), nodeValues);

        traversePreOrderRecursive(currentNode.getRight(), nodeValues);
    }

    public void traversePostOrder() {
        
    }
}
