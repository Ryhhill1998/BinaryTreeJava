import java.util.Stack;

public class BinaryTree {

    private Node root;

    public boolean addNode(int value) {
        Node nodeToAdd = new Node(value);

        if (root == null) {
            root = nodeToAdd;
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
}
