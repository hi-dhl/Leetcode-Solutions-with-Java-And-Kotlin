package com.hi.dhl.algorithms._100.java;

import com.hi.dhl.algorithms.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> pWrapQueue = new LinkedList<>();
        Queue<TreeNode> qWrapQueue = new LinkedList();
        pWrapQueue.offer(p);
        qWrapQueue.offer(q);
        while (!pWrapQueue.isEmpty() && !qWrapQueue.isEmpty()) {
            TreeNode pNode = pWrapQueue.poll();
            TreeNode qNode = qWrapQueue.poll();

            if (pNode == null && qNode == null) {
                continue;
            }

            if (pNode == null || qNode == null) {
                return false;
            }

            if (pNode.val != qNode.val) {
                return false;
            }

            pWrapQueue.offer(pNode.left);

            qWrapQueue.offer(qNode.left);

            pWrapQueue.offer(pNode.right);

            qWrapQueue.offer(qNode.right);

        }
        return pWrapQueue.size() == qWrapQueue.size();
    }
}
