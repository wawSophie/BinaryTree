package com.DS.List;

import java.awt.font.TextHitInfo;

/**
 * Author:Sophie
 * Created: 2019/7/16
 */
public class DoubleLinkedList {
    class Node{
        private int data;
        private Node pre;
        private Node next;
        public Node(int data){
            this.data=data;
        }
    }
    private Node head;
    private Node last;//标志尾巴
    public DoubleLinkedList(){
        this.head=null;
    }

    //头插,凡是没有头结点的都需要注意第一次插入
    public void addFirst(int data){
        Node node=new Node(data);
        if (this.head==null){
            this.head=node;
            this.last=node;
        }else {
            head.pre=node;
            node.next=head;
//            node.pre=null;
            head=node;
        }
    }

    //尾插
    public void addLast(int data){
        Node node=new Node(data);
        if (this.head==null){
            this.head=node;
            this.last=node;
        }else {
            this.last.next=node;
            node.pre=this.last;
            this.last=node;
        }
    }

    //指定位置插:1、对index进行合法性判断  2、找到index对于的node
    public boolean addIndex(int index,int data){
        Node node=new Node(data);
        Node cur=this.head;
        if (index<0 || index>getLength()){
            throw  new UnsupportedOperationException("下标不合法");
        }
        while (index>0){
            cur=cur.next;
            index--;
        }
        node.pre=cur.pre;
        node.next=cur;
        cur.pre.next=node;
        cur.pre=node;
        return true;
    }


    //得到双向链表的长度：1、若head为空，则为0；2、若head==last，则为1 ；3、否则，直到cur==last，count++
    private int getLength() {
        Node node=this.head;
        int count=1;
        if (node==null){
            return 0;
        }
        while (node!=last){
            node=node.next;
            count++;
        }
        return count;
    }

    //是否包含key
    public boolean contains(int key){
        Node cur=this.head;
        if (head==null){
            return false;
        }
        while (cur!=null){
            if (cur.data==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

    //删除指定元素key,要注意考虑，如果删除的是头结点的话，pre是null；尾结点的话，next为null
    public int remove(int key){
        Node cur=this.head;
        if (head==null){
            return -1;
        }
        while (cur!=null){
            if (cur.data==key){
                if (cur==this.head){
                    this.head=cur.next;
                    return cur.data;
                }else {
                   if (cur.next==null){
                       this.last=cur.pre;
                       return cur.data;
                   }else {
                       cur.pre.next=cur.next;
                       cur.next.pre=cur.pre;
                       return cur.data;
                   }
                }
            }
            cur=cur.next;
        }
        return -1;
    }

    //删除全部的key
    public void removeAll(int key){
//        Node pre=this.head;
//        Node cur=this.head.next;
//        while (cur!=null){
//            if (cur.data==key){
//                if (cur.next==null){
//                    this.last=pre;
//                    cur=cur.next;
//                }else {
//                    pre.next=cur.next;
//                    cur.next.pre=pre;
//                    cur=cur.next;
//                }
//            } else {
//                pre=pre.next;
//                cur=cur.next;
//            }
//        }
//        if (head.data==key){
//            this.head=this.head.next;
//        }
        Node cur=this.head;
        if (head==null){
            return;
        }
        while (cur!=null){
            if (cur.data==key){
                if (cur==this.head){
                    this.head=cur.next;

                }else {
                    if (cur.next==null){
                        this.last=cur.pre;

                    }else {
                        cur.pre.next=cur.next;
                        cur.next.pre=cur.pre;

                    }
                }
            }
            cur=cur.next;
        }

    }

    //打印
    public void display(){
        Node cur=this.head;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur=cur.next;
        }
    }

    //清空链表元素
    public void clear(){
        while (this.head!=null){
            Node cur=this.head.next;
            this.head.pre=null;
            this.head.next=null;
            this.head=cur;
        }
        //尾结点需要释放
        this.last=null;
    }


    //测试用例
    public static void main(String[] args) {
        DoubleLinkedList list=new DoubleLinkedList();
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.display();
        System.out.println(list.getLength());
        list.addIndex(1,60);
        list.display();
        System.out.println();
        System.out.println(list.contains(40));;
        System.out.println(list.contains(30));;
        System.out.println(list.remove(30));
        list.display();
        System.out.println();
        list.removeAll(10);
        list.display();
        System.out.println();
        System.out.println("=============");
        list.clear();
        list.display();
    }






}
