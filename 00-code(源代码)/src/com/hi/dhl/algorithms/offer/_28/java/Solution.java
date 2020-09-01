package com.hi.dhl.algorithms.offer._28.java;

import com.hi.dhl.algorithms.model.TreeNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/9/1
 *     desc  :
 * </pre>
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return symmetric(root.left, root.right);
    }

    public boolean symmetric(TreeNode L, TreeNode R) {
        if (L == null && R == null) return true;
        if (L == null || R == null || L.val != R.val) return false;

        return symmetric(L.left, R.right) && symmetric(L.right, R.left);
    }
}