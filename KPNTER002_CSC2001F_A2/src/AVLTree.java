public class AVLTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType> {
   // Instance variables to track comparison operations
   private int insertComparisonCount = 0;
   private int searchComparisonCount = 0;

   // Getter methods to access comparison counts
   public int getInsertComparisonCount() {
      return insertComparisonCount;
   }

   public int getSearchComparisonCount() {
      return searchComparisonCount;
   }

   // Method to reset comparison counts
   public void resetComparisonCounts() {
      insertComparisonCount = 0;
      searchComparisonCount = 0;
   }

   // Method to calculate the height of a node
   public int height(BinaryTreeNode<dataType> node) {
      if (node != null)
         return node.height;
      return -1;
   }

   // Method to calculate the balance factor of a node
   public int balanceFactor(BinaryTreeNode<dataType> node) {
      return height(node.right) - height(node.left);
   }

   // Method to update the height of a node
   public void fixHeight(BinaryTreeNode<dataType> node) {
      node.height = Math.max(height(node.left), height(node.right)) + 1;
   }

   // Method to perform a right rotation at a node
   public BinaryTreeNode<dataType> rotateRight(BinaryTreeNode<dataType> p) {
      BinaryTreeNode<dataType> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight(p);
      fixHeight(q);
      return q;
   }

   // Method to perform a left rotation at a node
   public BinaryTreeNode<dataType> rotateLeft(BinaryTreeNode<dataType> q) {
      BinaryTreeNode<dataType> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight(q);
      fixHeight(p);
      return p;
   }

   // Method to balance a node in the AVL tree
   public BinaryTreeNode<dataType> balance(BinaryTreeNode<dataType> p) {
      fixHeight(p);
      if (balanceFactor(p) == 2) {
         if (balanceFactor(p.right) < 0)
            p.right = rotateRight(p.right);
         return rotateLeft(p);
      }
      if (balanceFactor(p) == -2) {
         if (balanceFactor(p.left) > 0)
            p.left = rotateLeft(p.left);
         return rotateRight(p);
      }
      return p;
   }

   // Method to insert a node into the AVL tree
   public void insert(dataType d) {
      root = insert(d, root);
   }

   private BinaryTreeNode<dataType> insert(dataType d, BinaryTreeNode<dataType> node) {
      if (node == null)
         return new BinaryTreeNode<dataType>(d, null, null);
      if (d.compareTo(node.data) <= 0)
         node.left = insert(d, node.left);
      else
         node.right = insert(d, node.right);
      insertComparisonCount++; // Increment comparison count
      return balance(node);
   }

   // Method to delete a node from the AVL tree
   public void delete(dataType d) {
      root = delete(d, root);
   }

   private BinaryTreeNode<dataType> delete(dataType d, BinaryTreeNode<dataType> node) {
      if (node == null)
         return null;
      if (d.compareTo(node.data) < 0)
         node.left = delete(d, node.left);
      else if (d.compareTo(node.data) > 0)
         node.right = delete(d, node.right);
      else {
         BinaryTreeNode<dataType> q = node.left;
         BinaryTreeNode<dataType> r = node.right;
         if (r == null)
            return q;
         BinaryTreeNode<dataType> min = findMin(r);
         min.right = removeMin(r);
         min.left = q;
         return balance(min);
      }
      return balance(node);
   }

   // Method to find the minimum node in the AVL tree
   public BinaryTreeNode<dataType> findMin(BinaryTreeNode<dataType> node) {
      if (node.left != null)
         return findMin(node.left);
      else
         return node;
   }

   // Method to remove the minimum node from the AVL tree
   public BinaryTreeNode<dataType> removeMin(BinaryTreeNode<dataType> node) {
      if (node.left == null)
         return node.right;
      node.left = removeMin(node.left);
      return balance(node);
   }

   // Method to search for a node in the AVL tree
   public BinaryTreeNode<dataType> find(dataType d) {
      if (root == null)
         return null;
      else
         return find(d, root);
   }

   private BinaryTreeNode<dataType> find(dataType d, BinaryTreeNode<dataType> node) {
      if (node == null || d.compareTo(node.data) == 0) {
         searchComparisonCount++; // Increment comparison count
         return node;
      }
      searchComparisonCount++; // Increment comparison count
      if (d.compareTo(node.data) < 0)
         return find(d, node.left);
      else
         return find(d, node.right);
   }

   // Method to perform an in-order traversal of the AVL tree
   public void treeOrder() {
      treeOrder(root, 0);
   }

   private void treeOrder(BinaryTreeNode<dataType> node, int level) {
      if (node != null) {
         for (int i = 0; i < level; i++)
            System.out.print(" ");
         System.out.println(node.data);
         treeOrder(node.left, level + 1);
         treeOrder(node.right, level + 1);
      }
   }
}
