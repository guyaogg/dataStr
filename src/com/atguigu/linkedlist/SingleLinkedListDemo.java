package com.atguigu.linkedlist;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author guyao
 * @create 2021-06-14 16:47
 */
public class SingleLinkedListDemo {
    private int c = 0;
    public String getPermutation(int n, int k) {
        int len = 1;
        for(int i = 2;i <= n;i++){
            len *= i;
        }
        int[] ans = new int[len];

        fuzhi(ans,n,new HashSet<Integer>(),0);
        return String.valueOf(ans[k]);
    }
    void fuzhi(int[] ans,int n,HashSet<Integer> set,int zhi){
        if(set.size() == n){
            ans[c++] = zhi;
            return;
        }
        for(int i = 1;i <= n;i++){
            if(!set.contains(i)){
                set.add(i);
                fuzhi(ans,n,set,zhi * 10 + i);
                set.remove(i);

            }
        }
    }
    @Test
    public  void test() {
        System.out.println(getPermutation(3, 3));

//        int sw = 2,top=1;
//        System.out.println(top + sw);
//
//        //创建节点
//        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
//        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
//        HeroNode hero2New = new HeroNode(2, "卢俊er", "xiao麒麟");
//        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
//        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
//        HeroNode hero5 = new HeroNode(5, "李逵", "黑旋风");
//        HeroNode hero6 = new HeroNode(6, "燕青", "bzd");
//        //创建一个链表
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        //加入
////        singleLinkedList.add(hero1);
////        singleLinkedList.add(hero4);
////        singleLinkedList.add(hero3);
////        singleLinkedList.add(hero2);
//
//        //顺序加人
//        singleLinkedList.sortAdd(hero1);
//        singleLinkedList.sortAdd(hero4);
//        singleLinkedList.sortAdd(hero3);
////        singleLinkedList.sortAdd(hero2);
//        singleLinkedList.sortAdd(hero2);
//        singleLinkedList.sortAdd(hero5);
//        singleLinkedList.sortAdd(hero6);

        //替换
//        singleLinkedList.update(hero2New);

        //删除
//        singleLinkedList.remove(1);
//        singleLinkedList.remove(hero4);
//        singleLinkedList.reserRead();

//        singleLinkedList.list();
//        System.out.println(SingleLinkedList.getLength(singleLinkedList.getHead()));
//        singleLinkedList.add(hero1);
//        System.out.println(SingleLinkedList.findLastIndex(singleLinkedList.getHead(),2).name);
//        singleLinkedList.list();
//        ArrayList a = new ArrayList();
//        HashSet<Integer> s = new HashSet<>();


    }
}

//定义SingleLinkedList管理英雄
class SingleLinkedList{
    //先初始化一个头节点
    private HeroNode head = new HeroNode();
    private int size = 0;

    public void reserRead()  {
        //集合
        HeroNode temp = head.next;
//        List<HeroNode> arr = new ArrayList<>();
//        while (temp != null) {
//            arr.add(temp);
//            temp = temp.next;
//        }
//        for (int i = arr.size() - 1; i >= 0; i--) {
//            System.out.println(arr.get(i));
//        }
        //栈
        Stack<HeroNode> stack = new Stack<>();
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单链表
    public void add(HeroNode node){//未能自动排序
        //head不能动，因此需要辅助链表temp
        HeroNode temp = head;
        //遍历链表到最后
        while(temp.next != null)
            temp = temp.next;
        temp.next = node;
        size++;

    }
    //第二种顺序添加
    public void sortAdd(HeroNode node){
        //先创建辅助指针
        HeroNode temp = head;
        while(temp.next != null){
            int tempN = temp.next.no;
            if(tempN == node.no){//存在不再存
                System.out.printf("编号%d已有记录，不再添加\n",tempN);
                return;
            }else if(tempN > node.no){//找到插入位置，进行插入
                insert(temp,node);
                return;//插入后就不在遍历，注意结束；
            }else
                temp = temp.next;//小于不在便继续遍历

        }
        temp.next = node;

    }
    private void insert(HeroNode node1,HeroNode node2){
        node2.next = node1.next;//链表的插入可以不用辅助变量
        node1.next = node2;
    }
    //修改节点信息根据编号no来修改
    public void update(HeroNode newNode){
        if(head.next == null){
            System.out.println("链表空");
            return;
        }
        HeroNode temp = head.next;
        while(temp != null){
            if(temp.no == newNode.no){
                temp.name = newNode.name;
                temp.nikename = newNode.nikename;
                return;
            }
            temp = temp.next;
        }
        System.out.printf("没有编号为%d的猛将\n",newNode.no);

    }
    //删除节点(int
    public void remove(int no){
        if(head.next == null) {
            System.out.println("空链表");
            return;
        }
        HeroNode temp = head;
        while(temp.next != null){
            if(temp.next.no == no) {
                HeroNode temp2 = temp.next;
                temp.next = temp.next.next;//无引用的HeroNode会被回收机制自动删除
                temp2.next = null;//虽然自动回收还是要切断他的next值
                return;//一定要结束函数;
            }
            temp = temp.next;
        }
        System.out.printf("编码%d未找到\n",no);

    }
    //重载(HerNode
    public void remove(HeroNode node){
        remove(node.no);
    }

    //显示链表[遍历]
    public void list(){
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            //将next后移,一定小心
            temp = temp.next;
        }
    }
    //获取单链表的节点个数
    public static int getLength(HeroNode head){
        if(head.next == null)
            return 0;//空链表
        int length = 0;
        HeroNode temp = head.next;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }
    //查找链表倒数index的节点
    public static HeroNode findLastIndex(HeroNode head,int index){
        if(head.next == null)
            return null;
        HeroNode temp = head.next;
        HeroNode temp2 = head.next;
        int k = 1;
        while(k < index){
            if(temp.next == null)
                return null;
            temp = temp.next;
            k++;
        }
        while(temp.next != null){
            temp2 = temp2.next;
            temp = temp.next;
        }
        return temp2;
    }
    //链表反转
    public void fanZhuan(){
        HeroNode temp1 = head.next;
        if(temp1.next == null)
            return;
        HeroNode temp2 = temp1.next;
        HeroNode temp3 = temp2.next;
        temp1.next = null;
        while(temp2 != null){
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = temp3;
            if(temp3 == null)
                head.next = temp1;
            else
                temp3 = temp3.next;
        }

    }

}


class HeroNode{
    public int no;
    public String name;
    public String nikename;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nikename, HeroNode next) {
        this.no = no;
        this.name = name;
        this.nikename = nikename;
        this.next = next;
    }

    public HeroNode(int no, String name, String nikename) {
        this.no = no;
        this.name = name;
        this.nikename = nikename;
    }

    public HeroNode() {
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +

                '}';
    }
}
