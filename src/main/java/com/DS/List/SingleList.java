package com.DS.List;

/**
 * Author:Sophie
 * Created: 2019/7/16
 */

/**
 * 不带头单向链表:
 *      1、需要一个结点
 *              属性：data，next
 *      2、head，头引用
 */
public class SingleList {

    private Node head;
    //节点类
    class Node{
        private int data;
        private Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    public SingleList(){
        this.head=null;
    }

    /**
     * 头插
     * @param data
     */
    void addFirst(int data) {
        Node node = new Node(data);//此时拿到一个结点，next为null，值为data
        if (this.head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    /**
     * 尾插
     * @param data
     */
    void addLast(int data){
        Node node=new Node(data);
        Node cur=this.head;
        if (cur==null){
            this.head=node;
        }else {
            while (cur.next!=null){
                cur=cur.next;
            }
            cur.next=node;
        }
    }

    //判断下标是否合法
    private void checkIndex(int index){
        if (index<0 || index> getLength()){
            throw new IndexOutOfBoundsException("下标不合法");
        }
    }
    //返回index-1的Node
    private Node searchIndex(int index){
        checkIndex(index);
        if (index==0){
            return null;
        }
        int count=0;
        Node cur=this.head;
        while (cur.next!=null && count<index-1){
            cur=cur.next;
            count++;
        }
        return cur;
    }
    /**
     * 任意一个位置插入,要去找它的前一个位置
     * 如果前一个位置是null，说明是要头插了，那么就将node的next置为head，将head执行node；
     * 否则，将当前结点的next指向前一个结点的next，将前一个结点的next指向当前结点
     * @param index
     * @param data
     * @return
     */
    boolean addIndex(int index,int data){

        Node node=new Node(data);
        Node preNode=searchIndex(index);
        if (preNode==null){
            node.next=this.head;
            this.head=node;
        }else {
            node.next=preNode.next;
            preNode.next=node;
        }
        return true;
    }

    /**
     * 判断是否包含关键字key在单链表中
     * @param key
     * @return
     */
    boolean contains(int key){
        Node cur=this.head;
        while (cur!=null){
            if (cur.data==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }
    //如果包含这个节点，则返回这个节点的前一个Node
     Node searchPre(int key){
        Node cur=this.head;
        if (cur.data==key){
            return this.head;
        }
        while (cur.next!=null){
            if (cur.next.data==key){
                return cur;
            }
            cur=cur.next;
        }
        return null;
    }

    /**
     * 删除第一次出现关键字为key的结点
     * 1、先判断，这个关键字在不在单链表中，如果不在，则返回-1
     * 2、在单链表中，则得到这个关键字的前一个node，将node的next置为node.next.next;
     * @param key
     * @return
     */
    int remove(int key){
        int oldData=0;
        Node pre=searchPre(key);
        //没有找到key
        if (pre==null){
            return -1;
        }
        //说明第一个结点是要删除的结点
        if (pre==this.head && this.head.data==key){
            oldData=this.head.data;
            this.head=this.head.next;
            return oldData;
        }
        Node delNode=pre.next;
        oldData=delNode.data;
        pre.next=delNode.next;
        return oldData;
    }

    /**
     * 删除所有值为key的节点:
     * 1、可以判断是否包含，然后一直删除
     * 2、也可以从第二个结点开始，
     *      当结点不为null，并且如果结点值相等的话，就删除，pre.next=cur.next;pre=cur;cur=cur.next;
     *      最后再判断head是否与key相同，相同的话就将head=head.next;
     *      要注意，如果相等，删除了的话，pre是不动的，cur=cur.next；
     * @param key
     */
    void removeAllKey(int key){
//        while (contains(key)){
//            remove(key);
//        }
        Node pre=this.head;
        Node cur=this.head.next;
        while (cur!=null){
            if (cur.data==key){
                pre.next=cur.next;
                cur=cur.next;
            }else {
                pre=cur;
                cur=cur.next;
            }
        }
        if (this.head.data==key){
            this.head=this.head.next;
        }
    }

    /**
     * 得到单链表的长度
     * @return
     */
    int getLength(){
        Node cur=this.head;
        int size=0;
        while (cur!=null){
         size++;
        }
        return size;
    }

    /**
     * 打印单链表
     */
    void display(){
        Node cur=this.head;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur=cur.next;
        }
        System.out.println();
    }

    /**
     * 清空单链表
     */
    void clear(){
        while (this.head!=null){
            Node cur=this.head.next;
            head.next=null;
            head=cur;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SingleList list=new SingleList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.display();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.display();
        list.remove(1);
        list.display();
        list.remove(30);
        list.display();
        list.remove(3);
        list.display();
        list.addLast(20);
        list.addLast(20);
        list.display();
        System.out.println("=============");
        list.removeAllKey(20);
        list.display();
        Thread.sleep(1000);
        System.out.println("===清空单链表===");
//        list.clear();

    }
}
