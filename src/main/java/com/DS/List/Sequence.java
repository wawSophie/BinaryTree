package com.DS.List;

/**
 * Author:Sophie
 * Created: 2019/7/16
 */
import java.util.Arrays;

/**
 * 静态顺序表的属性：一个数组，有效数据长度
 */
public class Sequence {
    private Object[] elem;
    private int usedSize;
    private static int FAULT_SIZE=10;
    public Sequence(){
        this.elem=new Object[FAULT_SIZE];
        this.usedSize=0;
    }

    private boolean isFull(){
        return this.usedSize==this.elem.length;
    }
    private boolean isEmpty(){
        return this.usedSize==0;
    }
    /**
     * 在pos位置插入元素data
     * 1、判断pos的合法性
     *          小于0，满了，不连续，都不可以插
     *
     * @param pos
     * @param data
     * @return
     */
    boolean add(int pos,Object data) {
        //如果pos<0，或者不连续，则不能插入数据
        if ( pos < 0 || pos > this.usedSize) {
            return false;
        }
        //扩容,2倍扩容
        //拷贝数组：System.arraycopy(),本地方法
        if (isFull()){
            this.elem=Arrays.copyOf(this.elem,2*this.elem.length);
        }
        for (int i=this.usedSize-1;i>=pos;i--){
                this.elem[i+1]=this.elem[i];
        }
        elem[pos]=data;
        this.usedSize++;
        return true;
    }

    /**
     * 查找关键字key，找到返回key的下标，没有返回null
     * @param key
     * @return
     */
    int search(Object key) {
        if (isEmpty()){
            return -1;
        }
        for (int i=0;i<this.usedSize;i++){
            if (key.equals(this.elem[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找是否包含关键字key，是否在顺序表当中（和search有点冲突）
     * @param key
     * @return
     */
    boolean contains(Object key){
        for (int i=0;i<this.elem.length;i++){
            if (key==this.elem[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * 得到pos位置的值
     * @param pos
     * @return
     */
    Object getPos(int pos){
        if (pos<0 || pos>this.usedSize-1){
            return null;
        }
         return elem[pos];
    }

    /**
     * 删除第一次出现的关键字key
     * @param key
     * @return
     */
    Object remove(Object key){
        int index=search(key);
        if (index==-1){
            return null;
        }
        Object data=this.elem[index];
        int i;
        for ( i=index;i<this.usedSize-1;i++){
            this.elem[i]=this.elem[i+1];
        }
        this.usedSize--;
        this.elem[i+1]=null;
        return data;
    }

    /**
     * 得到顺序表的长度
     * @return
     */
    int size(){
        return this.usedSize;
    }

    /**
     * 打印顺序表
     */
    void dispaly(){
        for (int i=0;i<this.usedSize;i++){
            System.out.print(this.elem[i]+" ");
        }
    }
    /**
     * 清空顺序表以防内存泄漏
     */
    void clear(){
        for (int i=0;i<this.usedSize;i++){
            this.elem[i]=null;
        }
        this.usedSize=0;
    }
    public static void main(String[] args) throws InterruptedException {
        Sequence sequence=new Sequence();
//        for (int i=0;i<10;i++){
//            sequence.add(i,i);
//        }
//        sequence.dispaly();
//        System.out.println();
//        System.out.println(sequence.searchPre(3));;
//        sequence.remove(2);
        sequence.add(0,new Person());
        sequence.add(1,new Person());
        sequence.add(2,new Person());
//        sequence.dispaly();
        sequence.clear();
        Thread.sleep(1000);
    }
}
class Person{

}
