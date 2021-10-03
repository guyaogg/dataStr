package com.atguigu.linkedlist;

import org.junit.jupiter.api.Test;

/**
 * @author guyao
 * @Description
 * @create 2021-07-01 18:14
 */
public class DoubleLinkedlistDome {
    @Test
    public void test1(){
        //建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero2New = new HeroNode2(2, "卢俊er", "xiao麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "李逵", "黑旋风");
        HeroNode2 hero6 = new HeroNode2(6, "燕青", "bzd");
        DoubleLinkedList dL = new DoubleLinkedList();
        dL.addS(hero6);
        dL.addS(hero1);
        dL.addS(hero4);
        dL.addS(hero3);
        dL.addS(hero2);
        dL.addS(hero5);
        dL.list();

    }


}
//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化一个头节点
    private HeroNode2 head = new HeroNode2();

    //顺序添加
    public void addS(HeroNode2 node){
        HeroNode2 temp = head;
        if(temp.next == null) {
            head.next = node;
            node.pre = head;
            return;
        }
        while(temp.next != null){
            temp = temp.next;
            if(temp.no > node.no){
                temp.pre.next = node;
                node.pre = temp.pre;
                node.next = temp;
                temp.pre = node;
                return;
            }

        }
        node.pre = temp;
        temp.next = node;



    }
    //返回头节点
    public HeroNode2 getHead() {

        return head;
    }
    //删除节点(int//双向链表只要找到自己就可以不需要前一个链表，直接自我删除
    public void remove(int no){
        HeroNode2 temp = head.next;
        if(temp == null) {
            System.out.println("空链表");
            return;
        }
        while(temp != null){
            if(temp.no == no) {
                temp.pre.next = temp.next;
                if(temp.next != null)
                    temp.next.pre = temp.pre;//注意可能在末尾
                temp.next = null;//虽然自动回收还是要切断他的next值
                temp.pre = null;
                return;//一定要结束函数;
            }
            temp = temp.next;
        }
        System.out.printf("编码%d未找到\n",no);

    }
    //修改节点信息根据编号no来修改，内容修改和单向链表一样
    public void update(HeroNode2 newNode){
        if(head.next == null){
            System.out.println("链表空");
            return;
        }
        HeroNode2 temp = head.next;
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
    //添加节点到单链表
    public void add(HeroNode2 node){//未能自动排序
        //head不能动，因此需要辅助链表temp
        HeroNode2 temp = head;
        //遍历链表到最后
        while(temp.next != null)
            temp = temp.next;
//        形成一个双向链表
        temp.next = node;
        node.pre = temp;

    }
    //遍历
    public void list(){
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            System.out.println(temp);
            //将next后移,一定小心
            temp = temp.next;
        }
    }
}


    //定义双向链表
class HeroNode2{
    public int no;
    public String name;
    public String nikename;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向上一个节点默认null

        public HeroNode2(int no, String name, String nikename) {
            this.no = no;
            this.name = name;
            this.nikename = nikename;
        }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nikename='" + nikename + '\'' +

                    '}';
        }

        public HeroNode2(int no, String name, String nikename, HeroNode2 next, HeroNode2 pre) {
        this.no = no;
        this.name = name;
        this.nikename = nikename;
        this.next = next;
        this.pre = pre;
    }

    public HeroNode2() {
    }
}