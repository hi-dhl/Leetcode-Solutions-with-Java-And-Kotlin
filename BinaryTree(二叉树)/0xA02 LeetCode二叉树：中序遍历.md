# LeetCode二叉树：中序遍历

题目来源于 LeetCode 上第 94号（Binary Tree Inorder Traversal）问题：二叉树的中序遍历。题目难度为 Medium。

* [英文地址: https://leetcode.com/problems/binary-tree-inorder-traversal/](https://leetcode.com/problems/binary-tree-inorder-traversal/)
* [中文地址: https://leetcode-cn.com/problems/binary-tree-postorder-traversal/](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

## 题目描述
 
Given a binary tree, return the inorder traversal of its nodes' values
 
**Example:**
  
 ```
 Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
 ```

 **二叉树的中序遍历过程: 左节点 -> 根节点 -> 右节点，如下图所示：**
 ![](http://cdn.51git.cn/2020-04-18-二叉树.png)

**结果为：1 2 3 4 5 6 7**

## 思路一：非递归
 
1. 循环检测栈是否为空，或者根节点是否为空
2. 循环检测左节点，保存在栈中
3. 弹出栈顶元素，再将当前指针移到其右子节点上，若存在右子节点，在次执行步骤2

### Java实现

```
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList<>();
        // 循环检测栈是否为空，或者根节点是否为空
        while (!stack.isEmpty() || root != null) {

            // 循环检测左节点，保存在栈中
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 弹出栈顶元素
            root = stack.pop();
            list.add(root.val);

            // 再将当前指针移到其右子节点上，若存在右子节点，在次执行步骤2
            root = root.right;
        }
        return list;
    }
}
```

## kotlin 实现

```
class Solution {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        var current = root
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current)
                current = current.left
            }
            stack.pop()?.let { item ->
                list.add(item.`val`)
                current = item.right
            }
        }
        return list;
    }
}
```

## 思路二：递归

中序遍历递归思路和前序遍历大概一致，根据递归的两个特点去思考

**递归的两个特点：**

**1. 问题与子问题的递归关系，采用自上而下的思考方式**
**2. 递归的终止条件是当结点为叶子结点时终止（因为叶子节点没有左右结点）**

### java 实现

```
class Solution {
    List<Integer> list = new LinkedList<Integer>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return list;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }
}
```

### kotlin 尾递归实现

```
class Solution {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        tailrecInorder(root, list)
        return list;
    }

    tailrec fun tailrecInorder(root: TreeNode?, list: MutableList<Int> = mutableListOf()) {
        if (root == null) {
            return
        }
        tailrecInorder(root.left, list)
        list.add(root.`val`)
        tailrecInorder(root.right, list)
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)，一起来学习，期待与你一起成长


