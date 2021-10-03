package com.atguigu.algorithm;

import java.util.*;
import java.util.stream.Stream;

/**
 * 贪心算法解决集合覆盖问题
 *
 * @author 顾遥
 */
public class Greedy {
    public static void main(String[] args) {
        // 创建广播电台，放入到 Map
        Map<String, String[]> broadcasts = new HashMap<>(5);

        // 放入数据
        String[] str1 = {"北京", "上海", "天津"};
        String[] str2 = {"广州", "北京", "深圳"};
        String[] str3 = {"成都", "上海", "杭州"};
        String[] str4 = {"上海", "天津"};
        String[] str5 = {"杭州", "大连"};
        broadcasts.put("K1", str1);
        broadcasts.put("K2", str2);
        broadcasts.put("K3", str3);
        broadcasts.put("K4", str4);
        broadcasts.put("K5", str5);

        List<String> selects = greedySelects(broadcasts);
        System.out.println("获取结果：" + selects);


    }

    /**
     * 使用贪心算法，一步步中最优拿最优解
     *  不一定是最优结果，但是比较接近最优结果
     *
     * @param broadcasts 广播集合
     * @return 最少选择广播点的 key 集合
     */
    public static List<String> greedySelects(Map<String, String[]> broadcasts) {
        // 存放所有的地区
        Set<String> allArea = new HashSet<>();
        for (String[] broadcast : broadcasts.values()) {
            allArea.addAll(Arrays.asList(broadcast));
        }

        // 创建 ArrayList 存放电台集合
        List<String> selects = new ArrayList<>();
        // 遍历容器
        Set<String> tempSet = new HashSet<>();
        String maxKey;
        int maxKenSize;
        while (allArea.size() != 0) {
            // 置空
            maxKey = null;
            maxKenSize = 0;
            // 如果不满足条件就一直做
            for (Map.Entry<String, String[]> entry : broadcasts.entrySet()) {
                tempSet.clear();

                tempSet.addAll(Arrays.asList(entry.getValue()));
                // 求交集，并装载到 tempSet
                tempSet.retainAll(allArea);

                // 贪心算法特点，每次选择最优
                boolean biggerMaxKey = maxKey == null || tempSet.size() > maxKenSize;
                if (tempSet.size() > 0 && biggerMaxKey) {
                    maxKey = entry.getKey();
                    maxKenSize = tempSet.size();
                }
            }
            // 遍历后处理
            if (maxKey != null) {
                selects.add(maxKey);
                Stream.of(broadcasts.remove(maxKey)).forEach(allArea::remove);
            }
        }
        return selects;
    }


}
