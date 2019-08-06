package com.test.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/31 0031 AM 10:17
 * 4
 */
public class SortUtils {

    private static final Random RANDOM = new Random();


    public static void main(String[] args) {
        int[] arr = new int[]{5, 5, 6, 7, 9, 16, 45, 45, 15, 711, 4};
        SortUtils.fast(arr);
        System.out.println(arr);
    }


    public static int[] bubbling(int[] arr) {

        for (int j = 0; j < arr.length - 1; j++) {
            //因为比较过一次后 最后一个元素一定是最大的了
            for (int i = 0; i < arr.length - 1 - j; i++) {

                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 从数列中取出一个数作为基准数
     * 分区过程，将比它大的数全放到它的右边，小于或等于它的数全放到它的左边
     * 再对左右区间重复第二步，直到各区间只有一个数
     *
     * @param arr
     */
    public static void fast(int[] arr) {
        if (arr.length <= 1) {
            //只有一个元素 结束
            return;
        }

        //随机取出一个数
        int mid = arr[RANDOM.nextInt(arr.length)];
        List<Integer> left = new ArrayList<Integer>(arr.length / 2);
        List<Integer> right = new ArrayList<Integer>(arr.length / 2);

        for (int i = 0; i < arr.length; i++) {
            //把自己去掉

            if (arr[i] < mid) {
                left.add(arr[i]);
            } else if(arr[i]>mid){
                right.add(arr[i]);
            }else{

            }
        }
        int[] leftArr = new int[left.size()];
        int[] rightArr = new int[right.size()];


        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = left.get(i);
        }

        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = right.get(i);
        }

        fast(leftArr);
        fast(rightArr);


    }

}
