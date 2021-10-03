package com.atguigu.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Description 自建hashTab测试：数组联立链表
 * @Author 顾遥
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);
        // 写一个简单菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add : 添加雇员");
            System.out.println("list : 显示雇员");
            System.out.println("find : 查找雇员");
            System.out.println("delete : 删除雇员");
            System.out.println("exit : 退出");

            key = scanner.next();
            int id;
            switch (key) {
                case "add":
                    System.out.println("输入id : ");
                    id = scanner.nextInt();
                    System.out.println("输入name : ");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id : ");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id : ");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    return;

            }
        }

    }
}

/**
 * 创建哈希表
 */
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    /**
     * 构造器
     *
     * @param size init数组长度
     */
    public HashTab(int size) {
        this.size = size;
        // 初始化empLinkedListArray数组
        empLinkedListArray = new EmpLinkedList[size];
        // ?留个坑
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 根据id查找雇员
     */
    public void findEmpById(int id) {
        int hashFunNo = hashFun(id);
        Emp emp = empLinkedListArray[hashFunNo].findEmpById(id);
        if (emp != null) {
            System.out.println("第" + hashFunNo + "链表找到雇员为：" + emp);
        } else {
            System.out.println("在哈希表中没有该雇员");
        }

    }

    /**
     * 添加雇员
     */
    public void add(Emp emp) {
        // 根据员工id，得到该员工应该添加到那条链表
        int empLinkedListArrayNo = hashFun(emp.getId());
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListArrayNo].add(emp);

    }
    public void delete(int id) {
        int empLinkedListArrayNo = hashFun(id);
        int delete = empLinkedListArray[empLinkedListArrayNo].delete(id);
        if (delete == 1){
            System.out.println("已删除：第" + empLinkedListArrayNo + "条链表数据");
        }else {
            System.out.println("哈希表没有该id的雇员");
        }
    }

    /**
     * 遍历所有链表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 编写散列函数，取模法
     */
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 创建EmpLinkedList，表示链表
 */
class EmpLinkedList {
    /**
     * 头指针，执行第一个Emp，head直接指向第一个Emp
     * 默认为null
     */
    private Emp head;

    /**
     * 根据id查找雇员
     */
    public Emp findEmpById(int id) {

        // 先检验是否为空
        if (head == null) {
            System.out.println("链表为空");
        }
        Emp curEmp = head;
        while (curEmp != null) {
            if (curEmp.getId() == id) {
                return curEmp;
            } else {
                curEmp = curEmp.next;
            }
        }
        return null;
    }

    public int delete(int id) {
        if (head == null) {
            System.out.println("没有要删除的id");
            return -1;
        }
        Emp headEmp = new Emp();
        headEmp.next = head;
        Emp curEmp = headEmp;
        while (curEmp.next != null) {
            if(curEmp.next.getId() == id){
                System.out.println("已删除：" + curEmp.next);
                curEmp.next = curEmp.next.next;
                head = headEmp.next;
                return 1;
            }
            curEmp = curEmp.next;
        }
        System.out.println("没有要删除的id");
        return -1;
    }

    /**
     * 添加雇员
     * <p>
     * 添加雇员时，id自增，即id分配总是从小到大
     * 因此将雇员直接加入最后即可
     *
     * @param emp 雇员对象
     */
    public void add(Emp emp) {
        // 如果添加第一个
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个，则使用辅助指针，帮助定位
        Emp curEmp = head;
        while (curEmp.next != null) {
            // 后移
            curEmp = curEmp.next;
        }
        // 到最后,将emp加入链表最后
        curEmp.next = emp;


    }

    /**
     * 遍历链表雇员信息
     */
    public void list(int id) {
        if (head == null) {
            // 说明链表为空
            System.out.println("第 " + (id + 1) + " 条链表为空");
            return;
        }
        System.out.println("第 " + (id + 1) + " 条链表的信息为");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf("=> id=%d name=%s\t", curEmp.getId(), curEmp.getName());
            curEmp = curEmp.next;
        }
        System.out.println();

    }

}

/**
 * 创建Emp
 */
class Emp {
    private int id;
    private String name;

    public Emp() {
    }

    /**
     * 默认为null
     */
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
