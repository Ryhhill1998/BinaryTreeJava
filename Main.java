public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.addNode(4);
        binaryTree.addNode(6);
        binaryTree.addNode(2);
        binaryTree.addNode(3);
        binaryTree.addNode(1);
        binaryTree.addNode(3);
        binaryTree.addNode(5);
        binaryTree.addNode(7);

        binaryTree.traverseInOrder();

        System.out.println();
        System.out.println(binaryTree.nodeExists(8));
    }
}