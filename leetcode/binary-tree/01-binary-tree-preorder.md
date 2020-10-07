题目来源于 LeetCode 上第 144号（Binary Tree Preorder Traversal）问题：二叉树的前序遍历，题目难度为 Medium。

* [英文地址: https://leetcode.com/problems/binary-tree-preorder-traversal](https://leetcode.com/problems/binary-tree-preorder-traversal)

* [中文地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal)

## 题目描述
 
给定一个二叉树，返回它的 前序 遍历。

**示例:**

```
Input: [1,null,2,3]
   1
    \
     2
    /
   3
Output: [1,2,3]
```

**二叉树的前序遍历过程: 根节点 -> 左节点 -> 右节点，如图所示：**
![](http://cdn.51git.cn/2020-04-18-二叉树.png)

**结果为: 4 2 1 3 6 5 7**

## 思路一：非递归

可以利用栈的特性来解这道题，主要步骤如下：

1. 循环检测栈是否为空，或者根节点是否为空
2. 循环检测左节点，保存到栈中
3. 当左节点遍历结束之后，取出栈顶的右节点，再次执行步骤2

**复杂度分析：**

* 时间复杂度：O(n)，n 为节点的数量
* 空间复杂度：O(n)，n 为节点的数量

### Java 实现

```
class Solution {
    
    // 方法一
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList();

        // 循环检测栈是否为空，或者根节点是否为空
        while (!stack.isEmpty() || root != null) {

            // 循环检测左节点，保存到栈中
            while (root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }

            // 取出栈顶的右节点，再次执行步骤2
            if (!stack.isEmpty()) {
                root = stack.pop().right;
            }
        }
        return list;
    }

    // 方法二
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        if (root == null) return output;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return output;
    }
}
```

### Kotlin 实现

```
class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        stack.push(root);
        while (!stack.isEmpty()) {
            stack.pop()?.let { root ->
                list.add(root.`val`)
                if (root.right != null) stack.push(root.right)
                if (root.left != null) stack.push(root.left)
            }
        }
        return list
    }
}
```

## 思路二：递归

**递归的两个特点：**

1. 问题和子问题都会调用函数自身，所以要找寻问题与子问题的递归关系，采用自上而下的思考方式
2. 有个终止条件（临界点）结束当前递归

因此二叉树前序遍历按照上面两步进行分解

**1. 问题与子问题的递归关系，采用自上而下的思考方式**

![](http://cdn.51git.cn/2020-04-18-二叉树.png)

按照二叉树的前序遍历过程: 根节点 -> 左节点 -> 右节点

* 对于根节点4，它的左右节点分别是2、6，继续往下递推
* 假设根节点是2，它的左右节点是分别1、3，继续往下递推
* 假设根节点是1，它没有左右节点，此时递推结束了，然后往上回溯，以右节点3作为根节点，以此类推

因此问题与子问题的递归关系：
preorder(root) = preorder(root->left) + preorder(root->right)

**2. 递归的终止条件是当结点为叶子结点时终止（因为叶子节点没有左右子结点）**

**复杂度分析：**

* 时间复杂度：O(n)，n 为节点的数量
* 空间复杂度：O(n)，n 为节点的数量，最坏情况下需要空间O(n)，平均情况为O(log2^n)

### Java 实现

```
class Solution {
    List<Integer> list = new LinkedList<Integer>();

    public List<Integer> preorderTraversal(TreeNode root) {
        // 叶子节点没有左右子结点
        if (root == null) return list;
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }
}
```

### Kotlin 实现

```
class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        tailrecPreorder(root, list)
        return list
    }

    tailrec fun tailrecPreorder(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) {
            return
        }
        list.add(root.`val`)
        tailrecPreorder(root.left, list)
        tailrecPreorder(root.right, list)
    }
}
```

