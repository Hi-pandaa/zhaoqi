package com.test.structure.tree;

import com.alibaba.fastjson.JSONObject;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/30 0030 PM 3:30
 * 4
 */
public class TreeTest1 {


    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<Integer>();
        tree1.add(new TreeNode<Integer>(10), Tree.type.ITERATOR);
        tree1.add(new TreeNode<Integer>(11), Tree.type.ITERATOR);
        tree1.add(new TreeNode<Integer>(9), Tree.type.ITERATOR);
        tree1.add(new TreeNode<Integer>(12), Tree.type.ITERATOR);


        System.out.println(JSONObject.toJSONString(tree1));

    }
}
