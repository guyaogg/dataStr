package com.atguigu.linkedlist;


import org.junit.jupiter.api.Test;

/**
 * @author guyao
 * @create 2021-06-28 20:52
 */
public class ListNodeDome {
    @Test
    public void testR(){
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        reverseBetween(head,2,4);
        show(head);

    }
    public void show(ListNode head){
        while(head != null){
            System.out.print(head.val + "  ");
            head = head.next;
        }
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)
            return head;
        ListNode temp = head;
        for(int i = 1;i < left - 1;i++){
            temp = temp.next;
        }
        ListNode tempTop = temp;
        ListNode tempWei = temp.next;
        temp = temp.next;
        ListNode temp1 = temp.next;
        for(int i = left;i < right;i++){

            ListNode temp2 = temp1.next;
            temp1.next = temp;
            temp = temp1;
            temp1 = temp2;
            if(i == right - 1){
                tempTop.next = temp;
                tempWei.next = temp1;
            }else
                temp2 = temp2.next;
        }
        return head;

    }
}
