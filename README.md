# Leetcode-Solutions-with-Java-And-Kotlin

**仓库状态：持续更新中**

数据结构和算法是每个程序的都必须掌握的，也是面试的入门门槛之一，在大学期间经常参加一些比赛如蓝桥杯、ACM等等，因此在工作的时候，无论在面试还是工作都带来很多帮助，去寻找最优解，Google这几年一直在强力推荐Kotlin，自从用了Kotlin了之后爱不释手，工作效率提高了很多，现在我打算重拾 LeetCode 之 Algorithm，语言选择Java、Kotlin，题库会一点点去完善，我会根据算法的类型、题目的难易程度去排序，期待和大家一起成长

## 排序（未编辑）

排序：冒泡排序、插入排序、选择排序、希尔排序、归并排序、快速排序、堆排序

## 查找（未编辑）

查找：线性查找、树结构查找、散列表查找

## 线性表

线性表：数组、链表、栈、队列等等

### 数组以及数字（未编辑）

| 题号 | 题解 | 题目地址 | Lanuage | Difficulty |
| :-: | :-:  |   :-:  |   :-:    |   :-:      |

### 链表（未编辑）

链表：单链表、双向链表、循环链表、双向循环链表、静态链表

| 题号 | 题解 | 题目地址 | Lanuage | Difficulty |
| :-: | :-: | :-: | :-: | :-: |

### 栈（未编辑）

栈：顺序栈、链式栈

| 题号 | 题解 | 题目地址 | Lanuage | Difficulty |
| :-: | :-: | :-: | :-: | :-: |

### 队列（未编辑）

队列：普通队列、双端队列、阻塞队列、并发队列、阻塞并发队列

| 题号 | 题解 | 题目地址 | Lanuage | Difficulty |
| :-: | :-: | :-: | :-: | :-: |

## 二叉树

**完全二叉树：** 若二叉树的高度是h，除第h层之外，其他（1~h-1）层的节点数都达到了最大个数，并且第h层的节点都连续的集中在最左边。想到点什么没？实际上，完全二叉树和堆联系比较紧密哈~~~

**满二叉树：** 除最后一层外，每一层上的所有节点都有两个子节点，最后一层都是叶子节点。

**哈夫曼树：** 给定n个权值作为n的叶子结点，构造一棵二叉树，若带权路径长度达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman tree)。

**二叉排序树：** 又称二叉查找树（Binary Search Tree），亦称二叉搜索树。二叉排序树或者是一棵空树，或者是具有下列性质的二叉树：

* 若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
* 若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
* 左、右子树也分别为二叉排序树；
* 没有键值相等的节点

二分查找的时间复杂度是O(log(n))，最坏情况下的时间复杂度是O(n)（相当于顺序查找）

**平衡二叉树：** 又称 AVL 树。平衡二叉树是二叉搜索树的进化版，所谓平衡二叉树指的是，左右两个子树的高度差的绝对值不超过 1。

**红黑树：**红黑树是每个节点都带颜色的树，节点颜色或是红色或是黑色，红黑树是一种查找树。红黑树有一个重要的性质，从根节点到叶子节点的最长的路径不多于最短的路径的长度的两倍。对于红黑树，插入，删除，查找的复杂度都是O（log N）。


| 题号 | 题解 | 题目地址 | Lanuage | Difficulty |
| :-: | :-: | :-: | :-: | :-: |
| 144 | [Binary Tree Preorder Traversal<br/>二叉树前序遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA01%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%20%E5%89%8D%E5%BA%8F%E9%81%8D%E5%8E%86.md)| [英文地址](https://leetcode.com/problems/binary-tree-preorder-traversal/) / [中文地址](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)| Java / Kotlin | Medium |
| 94 | [Binary Tree Inorder Traversal<br/>二叉树中序遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA02%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E4%B8%AD%E5%BA%8F%E9%81%8D%E5%8E%86.md) | [英文地址](https://leetcode.com/problems/binary-tree-inorder-traversal/) / [中文地址](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) | Java / Kotlin | Medium |
| 145 | [Binary Tree Postorder Traversal<br/>二叉树后序遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA03%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86.md) | [英文地址](https://leetcode.com/problems/binary-tree-postorder-traversal/) / [中文地址](https://leetcode.com-cn/problems/binary-tree-postorder-traversal/) | Java / Kotlin | Medium |
| 145 | [Binary Tree Level Order Traversal<br/>二叉树层次遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA04%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E5%B1%82%E6%AC%A1%E9%81%8D%E5%8E%86.md) | [英文地址](https://leetcode.com/problems/binary-tree-level-order-traversal/) / [中文地址](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | Java / Kotlin | Medium |
| 107 | [Binary Tree Level Order Traversal II<br/>自下而上分层遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA09%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E8%87%AA%E4%B8%8B%E8%80%8C%E4%B8%8A%E5%88%86%E5%B1%82%E9%81%8D%E5%8E%86.md) | [英文地址](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/) / [中文地址](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/) | Java / Kotlin | Easy |
| 105 | [Construct Binary Tree from Preorder and Inorder Traversal<br/>从前序与中序遍历序列构造二叉树](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA05%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E4%BB%8E%E5%89%8D%E5%BA%8F%E4%B8%8E%E4%B8%AD%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97%E6%9E%84%E9%80%A0%E4%BA%8C%E5%8F%89%E6%A0%91.md) | [英文地址](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) / [中文地址](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | Java / Kotlin | Medium |
| 104 | [Maximum Depth of Binary Tree<br/>计算二叉树的最大深度 ](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA06%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E8%AE%A1%E7%AE%97%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%A4%A7%E6%B7%B1%E5%BA%A6.md) | [英文地址](https://leetcode.com/problems/maximum-depth-of-binary-tree/) / [中文地址](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) | Java / Kotlin | Easy |
| 111 | [Minimum Depth of Binary Tree<br/>计算二叉树的最小深度](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA07%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E8%AE%A1%E7%AE%97%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E6%B7%B1%E5%BA%A6.md) | [英文地址](https://leetcode.com/problems/minimum-depth-of-binary-tree/) / [中文地址](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) | Java / Kotlin | Easy |
| 110 | [Balanced Binary Tree<br/>判断二叉树是不是平衡二叉树](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA08%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E5%88%A4%E6%96%AD%E4%BA%8C%E5%8F%89%E6%A0%91%E6%98%AF%E4%B8%8D%E6%98%AF%E5%B9%B3%E8%A1%A1%E4%BA%8C%E5%8F%89%E6%A0%91.md) | [英文地址](https://leetcode.com/problems/balanced-binary-tree/) / [中文地址](https://leetcode-cn.com/problems/balanced-binary-tree/) | Java / Kotlin | Easy |
| 222 | [Count Complete Tree Nodes<br/>求二叉树中的节点个数](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA10%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E6%B1%82%E4%BA%8C%E5%8F%89%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9%E4%B8%AA%E6%95%B0.md) | [英文地址](https://leetcode.com/problems/count-complete-tree-nodes/) / [中文地址](https://leetcode-cn.com/problems/count-complete-tree-nodes/) | Java / Kotlin | Medium |
| 100 | [same-tree<br/>判断两棵二叉树是否结构相同](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA11%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E5%88%A4%E6%96%AD%E4%B8%A4%E6%A3%B5%E4%BA%8C%E5%8F%89%E6%A0%91%E6%98%AF%E5%90%A6%E7%BB%93%E6%9E%84%E7%9B%B8%E5%90%8C.md) | [英文地址](https://leetcode.com/problems/same-tree/) / [中文地址](https://leetcode-cn.com/problems/same-tree/) | Java / Kotlin | Easy |


