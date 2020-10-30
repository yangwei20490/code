package com.yw.springbootdemo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangwei
 * @date 2019-12-16 13:47
 */
public class array {
    public static int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 1; i< nums.length; i++) {
            if (nums[i] != nums[count]) {
                count++;
                nums[count] = nums[i];
            }
        }
        return count+1;
    }

    public static void main(String[] args) {
//        int[] a = {1, 1, 2};
//        removeDuplicates(a);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//        int[] a = {0, 0, 0, 1};
//        System.out.println(dominantIndex(a));
        String s = "1";
        for (int i = 0; i < 2; i++) {
            s += "s";
        }
        System.out.println(s);
    }

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                leftSum += nums[i - 1];
            }
            int rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public static int dominantIndex(int[] nums) {
        int max1 = -1;
        int max2 = -1;
        int maxIndex = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > max1){
                max2 = max1;
                max1 = nums[i];
                maxIndex = i;
            }else if(nums[i] > max2){
                max2 = nums[i];
            }
        }
        return max1 >= 2 * max2 ? maxIndex : -1;
    }
}
