package com.jcwx.game.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 生成随机数Service
 * 
 * @author derek
 * @version 1.0
 */
public class RandomService {

    public static Random random = new Random();

    /**
     * 生成随机数字数组(大于等于minNum且小于等于maxNum)
     * 
     * @param minNum
     * @param maxNum
     * @param length
     * @return int数组(按照从小到大自动排序)
     */
    public static int[] generateNumberArray(int minNum, int maxNum, int length) {
	int[] randomArray = new int[length];
	Set<Integer> set = new TreeSet<Integer>();
	int min = minNum < maxNum ? minNum : maxNum;
	int offset = Math.abs(maxNum - minNum);
	int i = 0;
	while (true) {
	    int tmp = offset == 0 ? min : random.nextInt(offset) + min;
	    if (set.add(tmp)) {
		randomArray[i++] = tmp;
	    }
	    if (set.size() >= length) {
		break;
	    }
	}
	return randomArray;
    }

    /**
     * 生成随机数字数组
     * 
     * @param maxNum
     *            最大数字范围(0~maxNum)
     * @param length
     *            长度
     * @return int数组(按照从小到大自动排序)
     */
    public static int[] generateRandomNumberArray(int maxNum, int length) {
	int[] randomArray = new int[length];
	Set<Integer> set = new TreeSet<Integer>();
	while (true) {
	    set.add(random.nextInt(maxNum + 1));
	    if (set.size() >= length) {
		break;
	    }
	}
	Iterator<Integer> iterator = set.iterator();
	int i = 0;
	while (iterator.hasNext()) {
	    randomArray[i] = iterator.next();
	    i++;
	}
	return randomArray;
    }

    /**
     * 生成随机数字数组(大于等于minNum且小于等于maxNum)
     * 
     * @param minNum
     * @param maxNum
     * @param length
     * @return int数组(按照从小到大自动排序)
     */
    public static int[] generateRandomNumberArray(int minNum, int maxNum,
	    int length) {
	int[] randomArray = new int[length];
	Set<Integer> set = new TreeSet<Integer>();
	int offset = maxNum - minNum;
	while (true) {
	    set.add(random.nextInt(offset) + minNum + 1);
	    if (set.size() >= length) {
		break;
	    }
	}
	Iterator<Integer> iterator = set.iterator();
	int i = 0;
	while (iterator.hasNext()) {
	    randomArray[i] = iterator.next();
	    i++;
	}
	return randomArray;
    }

    /**
     * 在已存在数据中随机选取数字
     * 
     * @param existingArray
     *            已存在数组
     * @param num
     *            数量
     * @return
     */
    public static int[] generateRandomNumberArrayFromExistingArray(
	    Integer[] existingArray, int num) {
	int[] randomArray = new int[num];
	Set<Integer> set = new TreeSet<Integer>();
	while (true) {
	    set.add(existingArray[random.nextInt(existingArray.length)]);
	    if (set.size() >= num) {
		break;
	    }
	}
	Iterator<Integer> iterator = set.iterator();
	int i = 0;
	while (iterator.hasNext()) {
	    randomArray[i] = iterator.next();
	    i++;
	}
	return randomArray;
    }

    public static <T> T getOneRandomElement(Collection<T> coll) {
	int length = coll.size();
	int sel = getRandomNumber(length);
	int i = 0;
	for (T one : coll) {
	    if (sel == i)
		return one;
	    // sel++;
	    i++;
	}
	return null;
    }

    public static int getRandomNumber(int len) {
	return random.nextInt(len);
    }

    public static boolean isInArray(int[] array, int e) {
	for (int i : array) {
	    if (i == e) {
		return true;
	    }
	}
	return false;
    }

    /**
     * 是否在范围内 如(5/100)则有5%几率返回值为true
     * 
     * @param minPercent
     *            分子
     * @param maxPercent
     *            分母
     * @return 是否在范围内
     */
    public static boolean isInTheLimits(Integer minPercent, Integer maxPercent) {
	if (random.nextInt(maxPercent) + 1 <= minPercent) {
	    return true;
	} else {
	    return false;
	}
    }

    public static void main(String[] args) {
	// int a = 0;
	// for ( int i=0;i<100;i++) {
	// if ( isInTheLimits(1,10) ) a++;
	// }
	// System.out.println(a);
	int[] a = generateNumberArray(16, 14, 9);
	for (int b : a)
	    System.out.print(b + ",");
    }
}
