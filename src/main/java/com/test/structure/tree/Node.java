package com.test.structure.tree;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/30 0030 AM 11:37
 * 4
 */
public class Node<T extends Comparable> implements Comparable<Node<T>> {


    T value;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }



    @Override
    public int compareTo(Node<T> o) {
        return this.value.compareTo(o.value);
    }
}
