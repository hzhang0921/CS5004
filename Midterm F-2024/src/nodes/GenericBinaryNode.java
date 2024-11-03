package nodes;

public class GenericBinaryNode<T> {
  private T data;
  private GenericBinaryNode<T> left;
  private GenericBinaryNode<T> right;

  public GenericBinaryNode(T data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }

  public GenericBinaryNode(T data, GenericBinaryNode<T> left, GenericBinaryNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public void addLeft(GenericBinaryNode<T> node) {
    this.left = node;
  }

  public void addRight(GenericBinaryNode<T> node) {
    this.right = node;
  }

  public GenericBinaryNode<T> getLeft() {
    return this.left;
  }

  public GenericBinaryNode<T> getRight() {
    return this.right;
  }

  public T getData() {
    return this.data;
  }

  @Override
  public String toString() {
    String result = "Node with data: " + data.toString();

    if (left != null) {
      result += "\n--> Left Node: " + left.toString();
    }

    if (right != null) {
      result += "\n--> Right Node: " + right.toString();
    }

    return result;
  }
}