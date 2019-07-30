package com.test.structure.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/30 0030 AM 11:43
 * 4
 */
public class Tree<T extends Comparable> {

    enum type {
        //递归
        RECURSIVE,
        //循环
        ITERATOR
    }

    /**
     * 顶级节点
     */
    TreeNode<T> top;


    /**
     * 递归的方法 如果层级很深 会有栈溢出的风险
     *
     * @param node
     * @param type
     */
    public void add(TreeNode<T> node, Tree.type type) {
        //if top is null
        if (top == null) {
            top = node;
            return;
        }
        //get top

        //根据左节点还是右节点来判断是否需要继续寻找节点
        switch (type) {
            case RECURSIVE:
                deepFind(top, node);
                break;
            case ITERATOR:
                eachFind(top, node);
                break;
        }


    }

    /**
     * 因为引用只有一个  所以不会栈溢出了
     *
     * @param temp
     * @param node
     */
    private void eachFind(TreeNode<T> temp, TreeNode<T> node) {
        TreeNode<T> root = temp;
        for (; ; ) {
            int i = node.compareTo(temp);
            if (i == 0) {
                break;
            }
            if (i < 0) {
                if (root.left == null) {
                    root.left = node;
                    break;
                }
                root = root.left;
                continue;
            } else {
                if (root.right == null) {
                    root.right = node;
                    break;
                }
                root = root.right;
                continue;
            }

        }
    }


    /**
     * 使用递归
     * deep find
     * 将与上个根节点比较  赋值或者继续下一层比较抽成单独的方法
     *
     * @param tempNode
     * @param addNode
     */
    private void deepFind(TreeNode<T> tempNode, TreeNode<T> addNode) {


        int compareVal = addNode.compareTo(tempNode);
        if (compareVal == 0) {
            //same node
            return;
        }
        if (compareVal < 0) {
            //左叶子节点
            if (tempNode.left == null) {
                tempNode.left = addNode;
                return;
            }
            deepFind(tempNode.left, addNode);
        } else {
            if (tempNode.right == null) {
                tempNode.right = addNode;
                return;
            }
            deepFind(tempNode.right, addNode);

        }

    }

    public Deque<T> traversal() {
        Deque<T> queue = new ArrayDeque();


        TreeNode<T> temp = top;
        if (temp == null) {
            return queue;

        }
        for (; ; ) {


        }


    }


    public TreeNode<T> getTop() {
        return top;
    }

    public void setTop(TreeNode<T> top) {
        this.top = top;
    }
}
