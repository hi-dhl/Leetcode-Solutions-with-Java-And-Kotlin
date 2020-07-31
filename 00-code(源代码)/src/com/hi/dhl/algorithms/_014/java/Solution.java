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
        if(strs == null || strs.length <= 0){
            return "";
        }
        String str0 = strs[0];
        int len = str0.length();
        int count = strs.length;
        for(int i = 0; i < len; i++){
            char c1 = str0.charAt(i);
            for(int j = 1; j < count; j++){
                if(i == strs[j].length() || c1 != strs[j].charAt(i)){
                    return str0.substring(0, i);
                }
            }
        }
        return str0;
    }

    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length <= 0){
            return "";
        }
        int count = strs.length;
        String str0 = strs[0];
        for(int i=0;i<count;i++){
            int j = 0;
            for(; j < str0.length() && j<strs[i].length(); j++){
                if(str0.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            str0 = str0.substring(0,j);
        }
        return str0;
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"falower", "f", "fbight"});
        list.add(new String[]{"", "cba"});
        list.add(new String[]{"a"});
        list.add(new String[]{"aa","a"});
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(solution.longestCommonPrefix((String[]) it.next()));
        }

    }
}
