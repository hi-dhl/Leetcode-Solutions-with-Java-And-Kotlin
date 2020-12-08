1题目来源于 `LeetCode` 上 第 `707` 号问题：设计链表。题目难度为 `Medium`。

* [英文地址：https://leetcode.com/problems/design-linked-list](https://leetcode.com/problems/design-linked-list) 
* [中文地址：https://leetcode-cn.com/problems/design-linked-list](https://leetcode-cn.com/problems/design-linked-list) 

## 题目描述

设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性： `val` 和 `next`。 `val` 是当前节点的值， `next` 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 `prev` 以指示链表中的上一个节点。假设链表中的所有节点都是 `0-index` 的

在链表类中实现这些功能：

* `get(index)`：获取链表中第 `index` 个节点的值。如果索引无效，则返回 `-1`。
* `addAtHead(val)`：在链表的第一个元素之前添加一个值为 `val` 的节点。插入后，新节点将成为链表的第一个节点。
* `addAtTail(val)`：将值为 `val` 的节点追加到链表的最后一个元素。
* `addAtIndex(index,val)`：在链表中的第 `index` 个节点之前添加值为 `val`  的节点。如果 `index` 等于链表的长度，则该节点将附加到链表的末尾。如果 `index` 大于链表长度，则不会插入节点。如果 `index` 小于 `0`，则在头部插入节点。
* `deleteAtIndex(index)`：如果索引 `index` 有效，则删除链表中的第 `index` 个节点。

**示例：**

```
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
```

**提示：**

* 所有 `val` 值都在 `[1, 1000]` 之内。
* 操作次数将在 `[1, 1000]`  之内。
* 请不要使用内置的 `LinkedList` 库。

链表是一个包含零个或多个元素的数据结构。每个元素都包含一个值和到另一个元素的链接。根据链接数的不同，可以分为单链表，双链表和多重链表。

## 思路一：单链表

![](http://cdn.51git.cn/2020-08-15-15974650110207.jpg)


**参数的含义：**

* `head` ：表示的队列的头指针
* `size` ：表示链表的长度，每插入一个元素 +1，删除一个元素 -1

**哨兵节点**

* 哨兵节点在树和链表中被广泛用作 **伪头**、**伪尾** 等，通常不保存任何数据。
* 单链表中可以使用 **伪头** 简化插入和删除，可以避免空指针的处理

**在指定位置插入元素**

* 查找指定位置的前驱节点

```
ListNode pre = head;
// 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
for (int i = 0; i < index; i++) pre = pre.next;
```

* 插入元素到前驱节点尾部（伪头不存放任何数据，伪头也是前驱节点）。

```
ListNode newNode = new ListNode(val);
// 插入元素到前驱节点尾部
newNode.next = pre.next;
pre.next = newNode;
```

示意图如下，来自 LeetCode 官网

![](http://cdn.51git.cn/2020-08-15-15974653886461.jpg)

![](http://cdn.51git.cn/2020-08-15-15974653963194.jpg)

**删除指定位置的节点**

* 查找指定位置的前驱节点

```
ListNode pre = head;
// 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
for (int i = 0; i < index; i++) pre = pre.next;
```

* 删除当前节点

```
pre.next = pre.next.next;
```

示意图如下，来自 LeetCode 官网

![](http://cdn.51git.cn/2020-08-15-15974654085371.jpg)


**复杂度分析：**

* 时间复杂度：
    * addAtHead： `O(1)`
    * addAtInder，get，deleteAtIndex: `(k)`，其中 `k` 指的是元素的索引。
    * addAtTail：`O(N)`，其中 `N` 指的是链表的元素个数。
* 空间复杂度 `O(1)`：所有的操作都是 `O(1)`

<!-- tabs:start -->

### **Java 实现**

```
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
}

public class MyLinkedList1 {

    ListNode head;
    int size;

    /**
     * 初始化
     */
    public MyLinkedList1() {
        size = 0;
        head = new ListNode(0);
    }

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        ListNode cur = head;
        // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
        for (int i = 0; i < index + 1; i++) cur = cur.next;
        return cur.val;
    }

    /**
     * 在头部添加元素
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * 在尾部添加元素
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * 在指定位置插入元素
     */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;

        ListNode pre = head;
        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        for (int i = 0; i < index; i++) pre = pre.next;

        ListNode newNode = new ListNode(val);
        // 插入元素到前驱节点尾部
        newNode.next = pre.next;
        pre.next = newNode;
        size++;
    }

    /**
     * 删除指定位置的元素
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        ListNode pre = head;
        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        for (int i = 0; i < index; i++) pre = pre.next;

        pre.next = pre.next.next;
        size--;
    }
}
```

### **Kotlin 实现**

```
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
}

class MyLinkedList1() {

    /**
     * 初始化
     */

    var head: ListNode = ListNode(0)
    var size: Int = 0

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    fun get(index: Int): Int {
        val k = index
        if (k < 0 || k >= size) return -1
        // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
        var cur: ListNode? = head
        for (i in 0 until (k + 1)) cur = cur?.next

        return cur?.`val` ?: -1
    }

    /**
     * 在头部添加元素
     */
    fun addAtHead(`val`: Int) {
        addAtIndex(0, `val`)
    }

    /**
     * 在尾部添加元素
     */
    fun addAtTail(`val`: Int) {
        addAtIndex(size, `val`)
    }

    /**
     * 在指定位置插入元素
     */
    fun addAtIndex(index: Int, `val`: Int) {
        var k = index
        if (k > size) return
        if (k < 0) k = 0

        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        var pre: ListNode? = head
        for (i in 0 until k) pre = pre?.next

        val node = ListNode(`val`)
        node.next = pre?.next
        pre?.next = node
        size++
    }

    /**
     * 删除指定位置的元素
     */
    fun deleteAtIndex(index: Int) {
        var k = index
        if (k < 0 || k >= size) return
        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        var pre: ListNode? = head
        for (i in 0 until k) pre = pre?.next

        pre?.next = pre?.next?.next
        size--
    }

}
```

<!-- tabs:end -->

## 思路二：双向链表

双链表比单链表快得多，测试用例花费的时间比单链表快了两倍。但是它更加复杂，它包含了 size，记录链表元素个数，和伪头伪尾

![](http://cdn.51git.cn/2020-08-15-15974660396295.jpg)

**参数的含义：**

* `head` ：表示的队列的头指针
* `last` ：表示的队列的尾指针
* `size` ：表示链表的长度，每插入一个元素 +1，删除一个元素 -1

**哨兵节点**

* 哨兵节点在树和链表中被广泛用作 **伪头**、**伪尾** 等，通常不保存任何数据。
* 双向链表中可以使用  **伪头**、**伪尾** 简化插入和删除，可以避免空指针的处理

**在指定位置插入元素**

* 找到要插入节点的前驱节点和后继节点

```
LinkedNode pre;
LinkedNode succ;
// 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
if (index < size - index) {
    pre = head;
    for (int i = 0; i < index; i++) pre = pre.next;
    succ = pre.next;
} else {
    succ = last;
    for (int i = 0; i < size - index; i++) succ = succ.pre;
    pre = succ.pre;
}
```


* 通过改变前驱结点和后继节点的链接关系插入元素

```
LinkedNode newNode = new LinkedNode(val);
newNode.next = succ;
newNode.pre = pre;
succ.pre = newNode;
pre.next = newNode;
```

以下示意图，来自 LeetCode 官网

![](http://cdn.51git.cn/2020-08-15-15974663136882.jpg)

**在指定位置删除元素**

* 找到要插入节点的前驱节点和后继节点

```
LinkedNode pre;
LinkedNode succ;
// 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
if (index < size - index) {
    pre = head;
    for (int i = 0; i < index; i++) pre = pre.next;
    succ = pre.next.next;
} else {
    succ = last;
    // 从尾部查找元素，需要 -1
    for (int i = 0; i < size - index - 1; i++) succ = succ.pre;
    pre = succ.pre.pre;
}
```

* 删除元素

```
pre.next = succ;
succ.pre = pre;
```

![](http://cdn.51git.cn/2020-08-15-15974664608193.jpg)

**复杂度分析：**

* 时间复杂度：
    * addAtHead，addAtTail： `O(1)`
    * get，addAtIndex，delete：`O(min(k,N−k))`，其中 `k` 指的是元素的索引
* 空间复杂度 `O(1)`：所有的操作都是 `O(1)`

<!-- tabs:start -->

### **Java 实现**

```
public class LinkedNode {
    public int val;
    public LinkedNode pre;
    public LinkedNode next;

    public LinkedNode(int x) {
        this.val = x;
    }
}


class MyLinkedList2 {

    LinkedNode head;
    LinkedNode last;
    int size = 0;

    /**
     * 初始化
     */
    public MyLinkedList2() {
        size = 0;
        head = new LinkedNode(0);
        last = new LinkedNode(0);
        head.next = last;
        last.pre = head;
    }

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        LinkedNode cur;
        if (index + 1 < size - index) {
            cur = head;
            // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
            for (int i = 0; i < index + 1; i++) cur = cur.next;
        } else {
            cur = last;
            for (int i = 0; i < size - index; i++) cur = cur.pre;
        }

        return cur.val;
    }

    /**
     * 在头部添加元素
     */
    public void addAtHead(int val) {
        LinkedNode pre = head;
        LinkedNode succ = head.next;

        LinkedNode node = new LinkedNode(val);
        node.next = succ;
        node.pre = pre;
        pre.next = node;
        succ.pre = node;
        size++;
    }

    /**
     * 在尾部添加元素
     */
    public void addAtTail(int val) {
        LinkedNode succ = last;
        LinkedNode pre = last.pre;

        LinkedNode node = new LinkedNode(val);
        node.pre = pre;
        node.next = succ;
        succ.pre = node;
        pre.next = node;
        size++;
    }

    /**
     * 在指定位置插入元素
     */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;

        LinkedNode pre;
        LinkedNode succ;
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (index < size - index) {
            pre = head;
            for (int i = 0; i < index; i++) pre = pre.next;
            succ = pre.next;
        } else {
            succ = last;
            for (int i = 0; i < size - index; i++) succ = succ.pre;
            pre = succ.pre;
        }

        LinkedNode newNode = new LinkedNode(val);
        newNode.next = succ;
        newNode.pre = pre;
        succ.pre = newNode;
        pre.next = newNode;
        size++;
    }

    /**
     * 删除指定位置的元素
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        LinkedNode pre;
        LinkedNode succ;
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (index < size - index) {
            pre = head;
            for (int i = 0; i < index; i++) pre = pre.next;
            succ = pre.next.next;
        } else {
            succ = last;
            // 从尾部查找元素，需要 -1
            for (int i = 0; i < size - index - 1; i++) succ = succ.pre;
            pre = succ.pre.pre;
        }

        pre.next = succ;
        succ.pre = pre;
        size--;
    }
}
```

### **Kotlin 实现**

```
class MyLinkedList2() {

    var head: LinkedNode = LinkedNode(0)
    var last: LinkedNode = LinkedNode(0)
    var size: Int = 0

    init {
        head.next = last
        last.pre = head
    }

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    fun get(index: Int): Int {
        val k = index
        if (k < 0 || k >= size) return -1

        var cur: LinkedNode? = head
        if ((k + 1) < (size - k)) {
            // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
            for (i in 0 until k + 1) cur = cur?.next
        } else {
            cur = last
            for (i in 0 until size - k) cur = cur?.pre
        }

        return cur?.`val` ?: -1
    }

    /**
     * 在头部添加元素
     */
    fun addAtHead(`val`: Int) {
        val pre: LinkedNode? = head
        val succ: LinkedNode? = head.next

        val node: LinkedNode = LinkedNode(`val`)
        node.next = succ
        node.pre = pre
        succ?.pre = node
        pre?.next = node
        size++

    }

    /**
     * 在尾部添加元素
     */
    fun addAtTail(`val`: Int) {
        val pre = last.pre
        val succ = last
        val node = LinkedNode(`val`)
        node.next = succ
        node.pre = pre
        pre?.next = node
        succ?.pre = node
        size++
    }

    /**
     * 在指定位置插入元素
     */
    fun addAtIndex(index: Int, `val`: Int) {
        var k = index
        if (k > size) return
        if (k < 0) k = 0

        var pre: LinkedNode? = head
        var succ: LinkedNode? = last
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (k < size - k) {
            for (i in 0 until k) pre = pre?.next
            succ = pre?.next
        } else {
            succ = last;
            for (i in 0 until size - k) succ = succ?.pre
            pre = succ?.pre
        }

        val node = LinkedNode(`val`)
        node.next = succ
        node.pre = pre
        pre?.next = node
        succ?.pre = node
        size++
    }

    /**
     * 删除指定位置的元素
     */
    fun deleteAtIndex(index: Int) {
        val k = index
        if (k < 0 || k >= size) return

        var pre: LinkedNode? = head
        var succ: LinkedNode? = last
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (k < size - k) {
            for (i in 0 until k) pre = pre?.next
            succ = pre?.next?.next
        } else {
            succ = last;
            // 从尾部查找元素，需要 -1
            for (i in 0 until size - k - 1) succ = succ?.pre
            pre = succ?.pre?.pre
        }

        pre?.next = succ
        succ?.pre = pre
        size--
    }

}
```

<!-- tabs:end -->

