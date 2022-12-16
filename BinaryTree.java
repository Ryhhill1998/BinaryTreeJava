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

    private Node[] findNodeAndPrevious(int value) {
        Node currentNode = root;
        Node previousNode = null;

        while (currentNode != null) {
            if (currentNode.getValue() == value) {
                return new Node[]{currentNode, previousNode};
            } else if (currentNode.getValue() < value) {
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            } else {
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }
        }

        return new Node[]{null, null};
    }

    private Node findSuccessor(Node nodeToRemove) {
        Node successor = nodeToRemove.getLeft();
        Node previous = nodeToRemove;
        boolean goingLeft = true;

        while (successor != null && successor.getRight() != null) {
            previous = successor;
            successor = successor.getRight();
            goingLeft = false;
        }

        if (goingLeft) {
            previous.setLeft(null);
        } else {
            previous.setRight(null);
        }

        return successor;
    }

    public void removeNode(int value) {
        if (!nodeExists(value)) {
            return;
        }

        Node[] nodeAndPrevious = findNodeAndPrevious(value);
        Node nodeToRemove = nodeAndPrevious[0];
        Node previousNode = nodeAndPrevious[1];

        Node successor = findSuccessor(nodeToRemove);

        replaceNode(nodeToRemove, successor, previousNode);
    }

    private void replaceNode(Node nodeToReplace, Node successor, Node nodeBeforeSuccessor) {
        if (successor != null) {
            successor.setLeft(nodeToReplace.getLeft());
            successor.setRight(nodeToReplace.getRight());
        }

        nodeToReplace.setLeft(null);
        nodeToReplace.setRight(null);

        if (nodeBeforeSuccessor == null) {
            root = successor;
            return;
        }

        if (nodeBeforeSuccessor.getValue() < nodeToReplace.getValue()) {
            nodeBeforeSuccessor.setRight(successor);
        } else {
            nodeBeforeSuccessor.setLeft(successor);
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
        Stack<Node> nodesToExpand = new Stack<>();
        nodesToExpand.push(root);
        HashSet<Node> visitedNodes = new HashSet<>();

        ArrayList<Integer> nodeValues = new ArrayList<>();

        while (!nodesToExpand.empty()) {
            Node currentNode = nodesToExpand.peek();

            if (currentNode == null) {
                nodesToExpand.pop();
                continue;
            }

            if (visitedNodes.contains(currentNode)) {
                nodeValues.add(currentNode.getValue());
                nodesToExpand.pop();
                continue;
            }

            nodesToExpand.push(currentNode.getRight());
            nodesToExpand.push(currentNode.getLeft());

            visitedNodes.add(currentNode);
        }

        for (int value : nodeValues) {
            System.out.print(value + " --> ");
        }

        System.out.println();
    }

    public void traversePostOrderRecursive() {
        ArrayList<Integer> nodeValues = new ArrayList<>();
        traversePostOrderRecursive(root, nodeValues);
        for (int value : nodeValues) {
            System.out.print(value + " --> ");
        }
        System.out.println();
    }

    private void traversePostOrderRecursive(Node currentNode, ArrayList<Integer> nodeValues) {
        if (currentNode == null) {
            return;
        }

        traversePostOrderRecursive(currentNode.getLeft(), nodeValues);

        traversePostOrderRecursive(currentNode.getRight(), nodeValues);

        nodeValues.add(currentNode.getValue());
    }
}
