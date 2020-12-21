package busca_arvore_binaria;

public class BinarySearchTree {
  Node root;

  BinarySearchTree() {
    root = null;
  }

  public Node search(Node root, int key) {
    if (root == null || root.key == key)
      return root;

    if (root.key < key)
      return search(root.right, key);

    return search(root.left, key);
  }

  void insert(int key) {
    root = insertRec(root, key);
  }

  Node insertRec(Node root, int key) {
    if (root == null) {
      root = new Node(key);
      return root;
    }

    if (key < root.key)
      root.left = insertRec(root.left, key);
    else if (key > root.key)
      root.right = insertRec(root.right, key);

    return root;
  }

  Node find(int key) {
    Node node = search(root, key);

    return node;
  }
}
