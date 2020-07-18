package com.hi.dhl.algorithms._014.java;


import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * <pre>
 *     author: dhl
 * </pre>
 */
public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) return "";
        if (strs.length == 1) return strs[0];
        String src = strs[0];
        for (int i = 1; i < strs.length; i++) {
            src = findPrefixCommonStr(src, strs[i]);
        }
        return src;
    }

    public String findPrefixCommonStr(String src, String dest) {
        if (src.isEmpty() || src.length() == 0) return "";
        if (dest.indexOf(src) == 0) return src;
        src = src.substring(0, src.length() - 1);
        return findPrefixCommonStr(src, dest);
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"falower", "f", "fbight"});
        list.add(new String[]{"", "cba"});
        list.add(new String[]{"a"});
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(solution.longestCommonPrefix((String[]) it.next()));
        }

    }
}
