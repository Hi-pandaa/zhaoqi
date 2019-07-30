package com.test.structure.tree;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/30 0030 AM 11:40
 * 4
 */
public class TreeNode<T extends Comparable> extends Node<T> {

    /**
     * 左叶子节点
     */
    TreeNode<T> left;

    /**
     * 右叶子节点
     */
    TreeNode<T> right;

    public TreeNode(T value) {
        super(value);
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
