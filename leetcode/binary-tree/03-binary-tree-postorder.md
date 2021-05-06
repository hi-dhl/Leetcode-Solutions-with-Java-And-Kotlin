题目来源于 LeetCode 上第 145号（Binary Tree Postorder Traversal）问题：二叉树的后序遍历。题目难度为 Medium。

* [英文地址: https://leetcode.com/problems/binary-tree-postorder-traversal](https://leetcode.com/problems/binary-tree-postorder-traversal)
* [中文地址: https://leetcode-cn.com/problems/binary-tree-postorder-traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal)
 
## 题目描述
 
给定一个二叉树，返回它的 后序 遍历。

**示例:**

```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```

**二叉树的后序遍历过程: 左节点 -> 右节点 -> 根节点，如下图所示：**
![](http://cdn.51git.cn/2020-04-18-二叉树.png)

**结果为：1 3 2 5 7 6 4**

## 思路一：非递归

一般分析问题我们需要采用自上而下的思维，而解决问题有时候采用自下而上的方式，正如上图后续遍历的结果：1 3 2 5 7 6 4，我们采用逆向思维，4是2、6的根节点，而6是5、7的根节点，依次类推逆向输出的过程：根节点 -> 右节点 -> 左节点, 我可以结合栈的特性，以及前序遍历的过程：根节点 -> 左节点 -> 右节点，去思考，大概过程如下：

1. 先将根节点入栈
2. 循环判断判断栈不为空
3. 取出栈顶元素，并添加到list集合（逆序添加）
4. 如果左节点不为空，并将左节点入栈
5. 如果右节点不为空，并将右节点入栈

**复杂度分析：**

* 时间复杂度：O(n)，n 为节点的数量
* 空间复杂度：O(n)，n 为节点的数量

<!-- tabs:start -->

### **Java 实现**

```
class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList<>();
        if(root == null) return list;
        // 先将根节点入栈
        stack.push(root);
        // 循环判断判断栈不为空
        while(!stack.isEmpty()){
            root = stack.pop();
            // 取出栈顶元素，并添加到list集合（逆序添加）
            list.add(0,root.val);
            //如果左节点不为空，并将左节点入栈
            if(root.left!=null) stack.push(root.left);
            // 如果右节点不为空，并将右节点入栈
            if(root.right!=null) stack.push(root.right);
        }
        return list;
    }
    
    // 方法二
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> data = new LinkedList<>();
        if (root == null) return data;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (true) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.peek();
                    if (root.right != null && root.right != pre) {
                        root = root.right;
                    } else {
                        break;
                    }
                }
            }

            pre = stack.pop();
            data.add(pre.val);
            root = null;
        }
        return data;
    }
}
```

### **Kotlin 实现**

```
class Solution {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        root?.let {
            val stack = Stack<TreeNode>()
            stack.push(it)
            while (!stack.isEmpty()) {
                val current = stack.pop()
                list.add(0, current.`val`)
                if (current.left != null) stack.push(current.left)
                if (current.right != null) stack.push(current.right)
            }
        }
        return list
    }
}
```

<!-- tabs:end -->

## 思路二：递归

后序遍历的递归思路和中序遍历、前序遍历大概一致，根据递归的两个特点去思考

**递归的两个特点：**

**1. 问题与子问题的递归关系，采用自上而下的思考方式**
**2. 递归的终止条件是当结点为叶子结点时终止（因为叶子节点没有左右结点）**

**复杂度分析：**

* 时间复杂度：O(n)，n 为节点的数量
* 空间复杂度：O(n)，n 为节点的数量，最坏情况下需要空间O(n)，平均情况为O(log2^n)

<!-- tabs:start -->

### **Java 实现**

```
class Solution {
    List<Integer> list = new LinkedList<Integer>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return list;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);
        return list;
    }
}
```

### **Kotlin 实现**

```
class Solution {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        tailrecPreorder(root, list)
        return list
    }

    tailrec fun tailrecPreorder(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) {
            return
        }
        tailrecPreorder(root.left, list)
        tailrecPreorder(root.right, list)
        list.add(root.`val`)
    }
}
```

<!-- tabs:end -->

