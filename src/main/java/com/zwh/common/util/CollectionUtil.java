package com.zwh.common.util;

import java.util.*;

/**
 * Created by a on 14-12-5.
 */
public class CollectionUtil {

    public static String[] listToArray(List<Long> list) {
        int size = list.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = "" + list.get(i);
        }
        return strings;
    }

    public static String[] setToArray(Set<Long> set) {
        int size = set.size();
        String[] strings = new String[size];
        int i = 0;
        for (Long str : set) {
            strings[i] = "" + str;
            i++;
        }
        return strings;
    }

    /**
     * 倒序排
     *
     * @param oriMap
     * @return
     */
    public static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> oriMap) {
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<Integer, Integer>>() {
                        public int compare(Map.Entry<Integer, Integer> entry1,
                                           Map.Entry<Integer, Integer> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = entry1.getValue();
                                value2 = entry2.getValue();
                            } catch (NumberFormatException e) {
                                value1 = 0;
                                value2 = 0;
                            }
                            return value2 - value1;
                        }
                    });
            Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
            Map.Entry<Integer, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    /**
     * 倒序排
     *
     * @param oriMap
     * @return
     */
    public static Map<Long, Long> sortMapByValueLong(Map<Long, Long> oriMap) {
        Map<Long, Long> sortedMap = new LinkedHashMap<Long, Long>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<Long, Long>> entryList = new ArrayList<Map.Entry<Long, Long>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<Long, Long>>() {
                        public int compare(Map.Entry<Long, Long> entry1,
                                           Map.Entry<Long, Long> entry2) {

                            return entry2.getValue().compareTo(entry1.getValue());
                        }
                    });
            Iterator<Map.Entry<Long, Long>> iter = entryList.iterator();
            Map.Entry<Long, Long> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    public static List<Long> SetStringToListLong(Set<String> set) {
        if (set == null || set.size() == 0) {
            return null;
        }
        List<Long> list = new ArrayList<Long>();
        for (String string : set) {
            list.add(Long.parseLong(string));
        }
        return list;
    }

    public static String mapConvertUrlParamString(Map<String, String> map) {
        if (map == null || map.size() == 0) return "";
        StringBuilder sb = new StringBuilder("?");
        for (Map.Entry<String, String> m : map.entrySet()) {
            sb.append(m.getKey()).append("=").append(m.getValue()).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * @param all
     * @param part
     * @param <T>
     * @return 返回不在all中不在part的元素集合
     */
    public static <T> List<T> listNotIn(List<T> all, List<T> part) {
        if (all == null || all.size() == 0) {
            return null;
        }
        if (part == null || part.size() == 0) {
            return all;
        }
        List<T> result = new LinkedList<T>();
        for (T t : all) {
            if (part.contains(t)) {
                continue;
            }
            result.add(t);
        }
        return result;
    }

//    public static void main(String[] args) {
//        List<Long> all = new LinkedList<Long>();
//        all.add(1L);
//        all.add(2L);
//        all.add(3L);
//        List<Long> part = new LinkedList<Long>();
//        part.add(1L);
//        System.out.println(listNotIn(all, part));
//    }

    /**
     * start end 都是索引
     *
     * @param T
     * @param start
     * @param end
     * @return
     */
    public List<?> subList(List<?> T, int start, int end) {
        if (start >= T.size()) {
            return null;
        }
        return null;
    }
}
