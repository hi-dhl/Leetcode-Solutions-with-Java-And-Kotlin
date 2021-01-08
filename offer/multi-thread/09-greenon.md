题目来源于 LeetCode 多线程 第 1279 号问题：红绿灯路口。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/traffic-light-controlled-intersection](https://leetcode-cn.com/problems/traffic-light-controlled-intersection)

## 题目描述

这是两条路的交叉路口。第一条路是 A 路，车辆可沿 1 号方向由北向南行驶，也可沿 2 号方向由南向北行驶。第二条路是 B 路，车辆可沿 3 号方向由西向东行驶，也可沿 4 号方向由东向西行驶。

![](http://img.hi-dhl.com/16095742206544.jpg)

每条路在路口前都有一个红绿灯。红绿灯可以亮起红灯或绿灯。

* 绿灯表示两个方向的车辆都可通过路口。
* 红灯表示两个方向的车辆都不可以通过路口，必须等待绿灯亮起。

两条路上的红绿灯不可以同时为绿灯。这意味着，当 A 路上的绿灯亮起时，B 路上的红灯会亮起；当 B 路上的绿灯亮起时，A 路上的红灯会亮起.

开始时，A 路上的绿灯亮起，B 路上的红灯亮起。当一条路上的绿灯亮起时，所有车辆都可以从任意两个方向通过路口，直到另一条路上的绿灯亮起。不同路上的车辆不可以同时通过路口。

给这个路口设计一个没有死锁的红绿灯控制系统。

实现函数 void carArrived(carId, roadId, direction, turnGreen, crossCar) :

* carId 为到达车辆的编号。
* roadId 为车辆所在道路的编号。
* direction 为车辆的行进方向。
* turnGreen 是一个函数，调用此函数会使当前道路上的绿灯亮起。
* crossCar 是一个函数，调用此函数会允许车辆通过路口。

**示例 1**

```
输入: cars = [1,3,5,2,4], directions = [2,1,2,4,3], arrivalTimes = [10,20,30,40,50]
输出: [
"Car 1 Has Passed Road A In Direction 2",    // A 路上的红绿灯为绿色，1 号车可通过路口。
"Car 3 Has Passed Road A In Direction 1",    // 红绿灯仍为绿色，3 号车通过路口。
"Car 5 Has Passed Road A In Direction 2",    // 红绿灯仍为绿色，5 号车通过路口。
"Traffic Light On Road B Is Green",          // 2 号车在 B 路请求绿灯。
"Car 2 Has Passed Road B In Direction 4",    // B 路上的绿灯现已亮起，2 号车通过路口。
"Car 4 Has Passed Road B In Direction 3"     // 红绿灯仍为绿色，4 号车通过路口。
]
```

**示例 2**

```
输入: cars = [1,2,3,4,5], directions = [2,4,3,3,1], arrivalTimes = [10,20,30,40,40]
输出: [
"Car 1 Has Passed Road A In Direction 2",    // A 路上的红绿灯为绿色，1 号车可通过路口。
"Traffic Light On Road B Is Green",          // 2 号车在 B 路请求绿灯。
"Car 2 Has Passed Road B In Direction 4",    // B 路上的绿灯现已亮起，2 号车通过路口。
"Car 3 Has Passed Road B In Direction 3",    // B 路上的绿灯现已亮起，3 号车通过路口。
"Traffic Light On Road A Is Green",          // 5 号车在 A 路请求绿灯。
"Car 5 Has Passed Road A In Direction 1",    // A 路上的绿灯现已亮起，5 号车通过路口。
"Traffic Light On Road B Is Green",          // 4 号车在 B 路请求绿灯。4 号车在路口等灯，直到 5 号车通过路口，B 路的绿灯亮起。
"Car 4 Has Passed Road B In Direction 3"     // B 路上的绿灯现已亮起，4 号车通过路口。
]
解释: 这是一个无死锁的方案。注意，在 A 路上的绿灯亮起、5 号车通过前让 4 号车通过，也是一个正确且可被接受的方案。
```

**提示：**

* 1 <= cars.length <= 20
* cars.length = directions.length
* cars.length = arrivalTimes.length
* cars 中的所有值都是唯一的。
* 1 <= directions[i] <= 4
* arrivalTimes 是非递减的。


## 思路：

题意总结如下：

* 十字路口由两条路组成 Road1 和 Road2
* 只有路灯才允许车辆通过，红灯则不行

我们可以构建一个临界区，每次只允许在一条路上通过，设置一个布尔变量 road1GreenOn 

* 当 `road1GreenOn == true` 表示 Road1 是路灯
* 当 `road1GreenOn == false` 表示 Road2 是路灯


**这道题有多种实现方式：**

* 通过 Synchronized 实现
* 通过 Lock 实现
* 通过 CAS 实现
* 通过 Semaphore（信号量） 实现
* 更多

<!-- tabs:start -->

### **CAS 实现**

CAS 是项乐观锁技术，当多个线程尝试使用 CAS 同时更新同一个变量时，只有其中一个线程能更新变量的值，而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试。

> 乐观锁是一种思想。CAS是这种思想的一种实现方式。

CAS 操作包含三个操作数 —— 内存中的值（V）、预期原值（A）和新值(B)。如果内存中的值与预期原值相匹配，会自动更新内存中的值为新值，否则一直在原地循环等待


```
class TrafficLightCAS {

    private final AtomicInteger atomic = new AtomicInteger();
    private boolean road1GreeOn = false;

    public TrafficLightCAS() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        try {
            while (!atomic.compareAndSet(0, 1)) Thread.sleep(1);

            if (roadId == 1 && road1GreeOn) {
                turnGreen.run();
                road1GreeOn = false;
            } else if (roadId == 2 && !road1GreeOn) {
                turnGreen.run();
                road1GreeOn = true;
            }
            crossCar.run();

            while (!atomic.compareAndSet(1, 0)) Thread.sleep(1);
        } catch (Exception e) {

        }
    }
}
```


### **ReentrantLock 实现**

```
class TrafficLightLock {
    private final Lock lock = new ReentrantLock();
    private boolean road1GreenOn = false;

    public TrafficLightLock() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        lock.lock();
        try {
            if (roadId == 1 && road1GreenOn) {
                turnGreen.run();
                road1GreenOn = false;
            } else if (roadId == 2 && !road1GreenOn) {
                turnGreen.run();
                road1GreenOn = true;
            }
            crossCar.run();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
```

### **Semaphore 实现**

```
class TrafficLightSemaphore {
    private final Semaphore semaphore = new Semaphore(1);
    private boolean road1GreenOn = false;

    public TrafficLightSemaphore() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        try {
            semaphore.acquire();
            if (roadId == 1 && road1GreenOn) {
                turnGreen.run();
                road1GreenOn = false;
            } else if (roadId == 2 && !road1GreenOn) {
                turnGreen.run();
                road1GreenOn = true;
            }
            crossCar.run();
        } catch (Exception e) {

        } finally {
            semaphore.release();
        }
    }
}
```

### **Synchronized 实现**

```
class TrafficLightSynchronized {
    private final Object LOCK = new Object();
    private boolean road1GreenOn = false;

    public TrafficLightSynchronized() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        synchronized (LOCK) {
            try {
                if (roadId == 1 && road1GreenOn) {
                    turnGreen.run();
                    road1GreenOn = false;
                } else if (roadId == 2 && !road1GreenOn) {
                    turnGreen.run();
                    road1GreenOn = true;
                }
                crossCar.run();
            } catch (Exception e) {

            }
        }
    }
}
```

<!-- tabs:end -->

