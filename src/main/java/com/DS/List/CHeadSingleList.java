package com.DS.List;

/**
 * Author:Sophie
 * Created: 2019/7/16
 */

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

import java.nio.file.Path;

/**
 * 带头循环单链表
 */
public class CHeadSingleList {
    class Node{
        private int data;
        private Node next;
        public Node(){
            this.data=-1;
        }
        public Node(int data){
            this.data=data;
        }
    }
    private Node head;

    public CHeadSingleList(){
        this.head=new Node();
        this.head.next=this.head;
    }

    //头插
    public void addFirst(int data){
        Node node=new Node(data);
        node.next=this.head.next;
        this.head.next=node;

    }
    //尾插
    public void addLast(int data){
        Node cur=this.head;
        while (cur.next!=this.head){
            cur=cur.next;
        }
        Node node=new Node(data);
        node.next=cur.next;
        cur.next=node;
    }
    private void checkIndex(int index){
        if (index<0 || index>getLength()){
            throw  new IndexOutOfBoundsException("下标不合法");
        }
    }

    private int getLength() {
        Node cur=this.head;
        int lenght=0;
        while (cur.next!=head){
            cur=cur.next;
            lenght++;
        }
        return lenght;
    }

    //在index处插入结点
    public boolean addIndex(int index,int data){
        Node node=new Node(data);
        Node cur=this.head;
        checkIndex(index);
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        node.next=cur.next;
        cur.next=node;
        return true;
    }

    //是否包含结点key
    public boolean contains(int key){
        Node cur=this.head.next;
        while (cur!=this.head){
            if (cur.data==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

    //找到返回前一个结点
    private Node searchPre(int key){
        Node pre=this.head;
        while (pre.next!=this.head){
            if (pre.next.data==key){
                return pre;
            }
            pre=pre.next;
        }
        return null;
    }
    //删除值为key的结点
    public int remove(int key){
        Node pre=searchPre(key);
        if (pre==null){
            throw  new UnsupportedOperationException("key不存在");
        }
        Node delNode=pre.next;
        int oLdData=delNode.data;
        pre.next=delNode.next;
        return oLdData;
    }

    //删除所有key
    public void  removeAll(int key){
        Node pre=this.head;
        Node cur=this.head.next;
        while (cur!=this.head){
            if (cur.data==key){
                pre.next=cur.next;
                cur=cur.next;
            }else {
                pre=pre.next;
                cur=cur.next;
            }
        }
    }

    //打印单链表
    public void display(){
        Node cur=this.head.next;
        while (cur!=this.head){
            System.out.print(cur.data+" ");
            cur=cur.next;
        }
        System.out.println();
    }

    //清空单链表
    public void clear(){
        while (this.head.next!=head){
            Node cur=this.head.next;
            head.next=cur.next;
            cur=null;
        }
        this.head=null;
    }

    public static void main(String[] args) {
        CHeadSingleList list=new CHeadSingleList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.display();
        list.clear();
        list.display();
    }
}
