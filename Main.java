public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.addNode(7);
        binaryTree.addNode(2);
        binaryTree.addNode(5);
        binaryTree.addNode(4);
        binaryTree.addNode(3);
        binaryTree.addNode(6);
        binaryTree.addNode(9);
        binaryTree.addNode(8);
        binaryTree.addNode(10);

        binaryTree.traverseInOrder();
        binaryTree.traverseInOrderRecursive();
        System.out.println();

        binaryTree.traversePreOrder();
        binaryTree.traversePreOrderRecursive();
        System.out.println();

        binaryTree.traversePostOrder();
        binaryTree.traversePostOrderRecursive();
        System.out.println();

        binaryTree.removeNode(2);
        binaryTree.traverseInOrderRecursive();
        binaryTree.traversePostOrderRecursive();
    }
}