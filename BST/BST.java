/*
BST tree with search, insert, and delete
@author Evan Yango
*/

public class BST<E extends Comparable<E>> implements Tree<E> {

    // ── Inner node class ──────────────────────────────────────────────────
    // protected static class TreeNode<E> {
    protected static class TreeNode<E extends Comparable<E>> { // Used AI to fix bug with CompareTo
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E e) {
            element = e;
            left    = null;
            right   = null;
        }

        public int compareTo(E e) {
            return this.element.compareTo(e);
        }
    }

    // ── Fields ────────────────────────────────────────────────────────────
    protected TreeNode<E> root;
    protected int size;

    // ── Constructor ───────────────────────────────────────────────────────
    public BST() {
        root = null;
        size = 0;
    }

    // ── Search ────────────────────────────────────────────────────────────
    @Override
    public boolean search(E e) {
        TreeNode<E> current = this.root;

        while (current != null) {
            int compare = current.compareTo(e);
            if (compare < 0) current = current.right;
            else if (compare > 0) current = current.left;
            else if (compare == 0) return true;
        }
        
        // e not found
        return false;
    }

    // ── Insert ────────────────────────────────────────────────────────────
    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
            size++;
            return true;
        }

        TreeNode<E> current = this.root;
        TreeNode<E> parent = null;

        while (current != null) {
            int compare = current.compareTo(e);
            if (compare < 0) {
                parent = current;
                current = current.right;
            } else if (compare > 0) {
                parent = current;
                current = current.left;
            } else if (compare == 0) return false; // duplicate
        }

        if (e.compareTo(parent.element) < 0) parent.left = new TreeNode<>(e);
        else parent.right = new TreeNode<>(e);
        size++;
        return true;
    }

    // ── Delete ────────────────────────────────────────────────────────────
    @Override
    public boolean delete(E e) {
        // Step 1: find the node -- same path as search, tracking parent
        TreeNode<E> parent  = null;
        TreeNode<E> current = root;

        boolean isLeft = false;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if      (cmp < 0) { parent = current; current = current.left; isLeft = true; }
            else if (cmp > 0) { parent = current; current = current.right; isLeft = false; }
            else break; // found
        }

        if (current == null) return false; // not found

        if (current.left == null && current.right == null) { // no children
            // Step 2: determine which case applies and handle it
            // TODO Case 1: current has no children
            //   -- set parent's left or right to null
            //   -- handle the special case where current is the root
            if (parent == null) this.root = current;
            else if (isLeft) parent.left = null;
            else parent.right = null;
            size--;
        } else if ((current.left != null) != (current.right != null)) { // 1 child
            // TODO Case 2: current has one child
            //   -- set parent's pointer to current's only child
            //   -- handle the special case where current is the root
            if (parent == null) this.root = current.left != null ? current.left : current.right;
            else if (isLeft) parent.left = current.left != null ? current.left : current.right;
            else parent.right = current.left != null ? current.left : current.right;
            size--;
        } else {
            // TODO Case 3: current has two children
            //   -- find the in-order successor: go right once, then left as far as possible
            //   -- copy successor's value into current
            //   -- delete the successor (it has at most one child, so Case 1 or 2)
            TreeNode<E> successor = current.right;
            while (true) {
                if (successor.left == null) break;
                successor = successor.left;
            }
            E successorElement = successor.element;
            this.delete(successor.element);
            current.element = successorElement;
        }

        // TODO: decrement size and return true
       
        return true;
    }

    // ── Inorder traversal ─────────────────────────────────────────────────
    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode<E> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.element + " ");
        inorder(node.right);    
    }

    // ── Preorder traversal ────────────────────────────────────────────────
    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode<E> node) {
        if (node == null) return;
        System.out.print(node.element + " ");
        preorder(node.left);
        preorder(node.right);   
    }

    // ── Postorder traversal ───────────────────────────────────────────────
    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode<E> node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);   
        System.out.print(node.element + " ");
    }

    // ── Size and empty ────────────────────────────────────────────────────
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // ── Test driver ───────────────────────────────────────────────────────
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        // Insert
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(90);

        // Traversals -- predict the output before running
        System.out.print("Inorder:   "); tree.inorder();   System.out.println();
        System.out.print("Preorder:  "); tree.preorder();  System.out.println();
        System.out.print("Postorder: "); tree.postorder(); System.out.println();

        // Search
        System.out.println("Search 30: " + tree.search(30));  // true
        System.out.println("Search 40: " + tree.search(40));  // false

        // Delete leaf
        tree.delete(30);
        System.out.print("After delete 30: "); tree.inorder(); System.out.println();

        // Delete node with one child
        tree.delete(25);
        System.out.print("After delete 25: "); tree.inorder(); System.out.println();

        // Delete node with two children
        tree.delete(75);
        System.out.print("After delete 75: "); tree.inorder(); System.out.println();

        // Size
        System.out.println("Size: " + tree.getSize());  // 4
    }
}
