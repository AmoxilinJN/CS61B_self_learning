public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given 2-3 TREE. */
    public RedBlackTree(TwoThreeTree<T> tree) {
        Node<T> ttTreeRoot = tree.root;
        root = buildRedBlackTree(ttTreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }

        if (r.getItemCount() == 1) {
            // TODO: Replace with code to create a 2-node equivalent
            RBTreeNode<T> temp = new RBTreeNode<T>(true,r.getItemAt(0));
            temp.left = buildRedBlackTree(r.getChildAt(0));
            temp.right = buildRedBlackTree(r.getChildAt(1));
            return temp;
        } else {
            // TODO: Replace with code to create a 3-node equivalent
            RBTreeNode<T> tempRed = new RBTreeNode<T>(false, r.getItemAt(0));
            RBTreeNode<T> tempBlack = new RBTreeNode<>(true,r.getItemAt(1),tempRed,null);
            tempRed.left = buildRedBlackTree(r.getChildAt(0));
            tempRed.right = buildRedBlackTree(r.getChildAt(1));
            tempBlack.right = buildRedBlackTree(r.getChildAt(2));
            return tempBlack;
        }
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        RBTreeNode<T> temp = node;
        RBTreeNode<T> tempChild = node.left.right;
        boolean tempIsBlack = node.isBlack;
        node.isBlack = node.left.isBlack;
        node.left.isBlack = tempIsBlack;
        node = node.left;
        node.right = temp;
        temp.left = tempChild;
        return node;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        RBTreeNode<T> temp = node;
        RBTreeNode<T> tempChild = node.right.left;
        boolean tempIsBlack = node.isBlack;
        node.isBlack = node.right.isBlack;
        node.right.isBlack = tempIsBlack;
        node = node.right;
        node.left = temp;
        temp.right = tempChild;
        return node;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: Insert (return) new red leaf node.
        if (node == null){
            node = new RBTreeNode<>(false,item);
            return node;
        }


        // TODO: Handle normal binary search tree insertion. The below line may help.
        int comp = item.compareTo(node.item);
        if (comp == 0){
            return node;
        }
        if (comp < 0){
            if (node.left == null){
                node.left = new RBTreeNode<>(false,item);
            } else {
                node.left = insert(node.left, item);
            }
        } else {
            if (node.right == null){
                node.right = new RBTreeNode<>(false,item);
            } else {
                node.right = insert(node.right,item);
            }
        }


        // TODO: Rotate left operation (handle "middle of three" and "right-leaning red" structures)
        if (!isRed(node.left) && isRed(node.right)){
            node = rotateLeft(node);
        }


        // TODO: Rotate right operation (handle "smallest of three" structure)
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }


        // TODO: Color flip (handle "largest of three" structure)
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }


        return node; // TODO: fix this return statement
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

}
