package com.dream.algorithmsdemo.java.tree;


import com.dream.algorithmsdemo.java.queue.Queue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/16
 */
public class BinaryTree<Key extends Comparable<Key>,Value> {


    private Node root;
    private int N;

    private class Node {

        public Key key;
        public Value value;
        public Node left;
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public BinaryTree() {

    }

    public void put(Key key,Value value){
        root = put(root,key,value);
    }

    private Node put(Node x,Key key,Value value){
        if(x == null){
            //如果x子树为空
            N++;
            return new Node(key, value, null, null);
        }else{
            //如果x子树不为空
            //比较x节点的键和key的大小
            int compareTo = key.compareTo(x.key);
            if(compareTo > 0){
                //如果key大于x节点的键，则继续找x节点的右子树
                x.right = put(x.right,key,value);
            }else if(compareTo < 0){
                //如果key小于x节点的键，则继续找x节点的左子树
                x.left = put(x.left,key,value);
            }else {
                //如果key等于x节点的键，则证明找到了，我们将值进行替换即可
                x.value = value;
            }
        }
        return x;
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        if(x == null) return null;
        int compareTo = key.compareTo(x.key);
        if(compareTo > 0){
            //key 大于 x 节点的键，继续找 x 节点的右子树
            return get(x.right, key);
        }else if(compareTo < 0){
            //key 小于 x 节点的键，继续找 x 节点的左子树
            return get(x.left,key);
        }else {
            return x.value;
        }
    }

    public Key minKey(){
        return minNode(root).key;
    }

    private Node minNode(Node node){
        if(node.left != null){
            return minNode(node.left);
        }else {
            return node;
        }
    }

    public void delete(Key key){
        delete(root, key);
    }

    public Key maxKey(){
        return maxNode(root).key;
    }

    private Node maxNode(Node node){
        if(node.right != null){
            return maxNode(node.right);
        }else {
            return node;
        }
    }

    private Node delete(Node x,Key key){
        if(x == null)return null;
        int compareTo = key.compareTo(x.key);
        if(compareTo > 0){
            //key 大于 x 节点的键，继续找 x 节点的右子树
            x.right = delete(x.right,key);
        }else if(compareTo < 0){
            //key 小于 x 节点的键，继续找 x 节点的左子树
            x.left =  delete(x.left,key);
        }else {
            N--;
            if(x.right == null && x.left == null){
                return null;
            }else if(x.right == null){
                return x.left;
            }else if(x.left == null){
                return x.right;
            }else {
                Node minNode = x.right;
                while (minNode.left != null){
                    minNode = minNode.left;
                }
                //删除右子树的最小节点
                Node n = x.right;
                while (n.left != null){
                    if(n.left.left == null){
                        n.left = null;
                    }
                    n = n.left;
                }

                //让 x 节点的左子树成为 minNode 的左子树
                minNode.left = x.left;
                //让 x 节点的右子树成为 minNode 的右子树
                minNode.right = x.right;
                //让 x 节点的父节点指向 minNode
                x = minNode;
            }
        }
        return x;
    }

    public int size(){
        return N;
    }

    //前序遍历
    public Queue<Key> preErgodic(){
        Queue<Key> keys = new Queue<>();
        preErgodic(root,keys);
        return keys;
    }

    private void preErgodic(Node x,Queue<Key> keys){
        if(x == null)return;
        //根左右
        keys.enQueue(x.key);

        if(x.left != null){
            preErgodic(x.left, keys);
        }

        if(x.right != null){
            preErgodic(x.right,keys);
        }
    }

    //中序遍历
    public Queue<Key> middleErgodic(){
        Queue<Key> keys = new Queue<>();
        middleErgodic(root,keys);
        return keys;
    }

    private void middleErgodic(Node x,Queue<Key> keys){
        if(x == null)return;
        if(x.left != null){
            middleErgodic(x.left,keys);
        }

        keys.enQueue(x.key);

        if(x.right != null){
            middleErgodic(x.right,keys);
        }
    }

    //后序遍历
    public Queue<Key> afterErgodic(){
        Queue<Key> keys = new Queue<>();
        afterErgodic(root,keys);
        return keys;
    }

    private void afterErgodic(Node x,Queue<Key> keys){
        if(x == null)return;
        if(x.left != null){
            afterErgodic(x.left,keys);
        }

        if(x.right != null){
            afterErgodic(x.right,keys);
        }

        keys.enQueue(x.key);
    }

    //层序遍历
    public Queue<Key> layerErgodic(){
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();
        //默认往队列放入根节点
        nodes.enQueue(root);
        while (!nodes.isEmpty()){
            //从队列中弹出一个节点
            Node x = nodes.deQueue();
            keys.enQueue(x.key);
            if(x.left != null){
                nodes.enQueue(x.left);
            }

            if(x.right != null){
                nodes.enQueue(x.right);
            }
        }
        return keys;
    }

    //最大深度
    public int maxDepth(){
        return maxDepth(root);
    }

    private int maxDepth(Node x){
        if(x == null)return 0;
        int max = 0;
        int maxL = 0;
        int maxR = 0;

        if(x.left != null){
            maxL = maxDepth(x.left);
        }

        if(x.right != null){
            maxR = maxDepth(x.right);
        }

        max = maxL > maxR ? maxL + 1 : maxR + 1;
        return max;
    }

}
