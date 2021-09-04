private class Node<T> {
    private T data;
    private Node link;

    // Constructors
    private Node(T value) {
        this(value, null);
    }
    private Node(T value, Node next) {
        data = value;
        link = next;
    }

    // set data method
    private void setData(T value) {
        data = value;
    }
    // set link method
    private void setLink(Node next) {
        link = next;
    }

    // get data method
    private T getData() {
        return data;
    }
    // get link method
    private Node getLink() {
        return link;
    }
}