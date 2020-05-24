package com.github.howardli.algo.base;

import java.util.*;

/**
 * 0、树，每个节点有零个或多个子结点；没有父节点的节点成为根结点；每一个非根结点有且只有一个父结点；除了根结点以外，每个子结点可以分为多个不相交的子树。
 * 1、二叉树，每个节点最多有两个子树，常被用于实现二叉查找树和二叉堆
 * 2、二叉树分类
 * 完全二叉树 - 若设二叉树的高度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第h层有叶子结点，并且叶子结点都是从左到右依次排布
 * 满二叉树 - 除了叶结点外每一个结点都有左右子叶且叶子结点都处在最底层的二叉树
 * 平衡二叉树 - AVL树，它是一棵二叉排序树，且具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 * 3、二叉树接口
 * 顺序存储 - 适合于完全二叉树和满二叉树，
 * 链表存储 - 是否存储父节点
 * 最大深度
 * 最小深度
 * 前序遍历 - node left right
 * 中序遍历 - left node right
 * 后序遍历 - left right node
 * 层次遍历 -
 * 层次遍历自下而上 -
 *
 */
public class BinaryTree {

    public static void main(String[] args){
//        String input = "1,2,null,3,null,4,null,5";
//        String input = "1,3,2,5,3,null,9";
//        TreeNode root = TreeNode.fromString(input);
//        List<List<Integer>> res = LevelOrderTraveral.solveV2(root);
//        System.out.println(res);
        SeDesV2.test();

    }


    /**
     *
     */
    static class SeDesV2{
        /**
         *    1
         *  2   3
         *    4
         * 1 2 null 4 null null 3 null null
         * @param root
         * @return
         */
        public static String serializePreOrder(TreeNode root) {
            Stack<TreeNode> todo = new Stack<>();
            List<Integer> resList = new ArrayList<>();
            if(root!=null){
                todo.push(root);
                while(!todo.isEmpty()){
                    TreeNode cur = todo.pop();
                    if(cur!=null){
                        resList.add(cur.val);
                        todo.push(cur.right);
                        todo.push(cur.left);
                    }else{
                        resList.add(null);
                    }
                }
            }
            return resList.toString();
        }

        /**
         * 当前节点不null，构造新节点
         * 上个节点不null, 当前节点必是上个节点的左儿子
         * 上个节点为null, 当前节点必是栈顶的右儿子
         * 当前节点非空则入栈
         *
         * @param data
         * @return
         */
        public static TreeNode deserializePreOrderNoRecursive(String data) {
            data = data
                    .replaceAll("\\[","")
                    .replaceAll("]","")
                    .replaceAll(" ","");
            if(data==null || data.trim().length()<=0) return null;
            String[] dataArr = data.split(",");
            Queue<String> dataList = new LinkedList<String>(Arrays.asList(dataArr));
            Stack<TreeNode> todo = new Stack<>();
            String curStr = dataArr[0].trim();
            TreeNode root = new TreeNode(Integer.valueOf(curStr));
            todo.push(root);
            int i = 1;
            TreeNode lastNode = root;
            while(i<dataArr.length-1){
                curStr = dataArr[i].trim();
                TreeNode curNode = null;
                if(!"null".equals(curStr)) curNode = new TreeNode(Integer.valueOf(curStr));
                if(null!=lastNode)lastNode.left = curNode;
                else todo.pop().right = curNode;
                if(null!=curNode) todo.push(curNode);
                lastNode = curNode;
                i++;
            }
            return root;
        }

        public static TreeNode deserializePreOrder(String data) {
            data = data
                    .replaceAll("\\[","")
                    .replaceAll("]","")
                    .replaceAll(" ","");
            if(data==null || data.trim().length()<=0) return null;
            String[] dataArr = data.split(",");
            Queue<String> dataList = new LinkedList<String>(Arrays.asList(dataArr));
            TreeNode root = rDeserializePreOrder(dataList);
            return root;
        }

        private static TreeNode rDeserializePreOrder(Queue<String> dataList) {
            String curStr = dataList.poll().trim();
            if("null".equals(curStr)){
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(curStr));
            root.left = rDeserializePreOrder(dataList);
            root.right = rDeserializePreOrder(dataList);
            return root;
        }

        public static void test(){
            System.out.println(serializePreOrder(SeDesV1.deserialize("[1,2,3,null,4,null,null,null,null]")));
//            System.out.println(serializePreOrder(deserializePreOrder("[1, 2, null, 4, null, null, 3, null, null]")));
//            System.out.println(serializePreOrder(deserializePreOrderNoRecursive("[1, 2, null, 4, null, null, 3, null, null]")));
//            System.out.println(serializePreOrder(deserializePreOrderNoRecursive("[1,2,3,null,null,4,5]")));
//            System.out.println(serializePreOrder(deserializePreOrder(serializePreOrder(SeDesV1.deserialize("[1,2,3,null,null,4,5]")))));
            System.out.println(serializePreOrder(SeDesV1.deserialize("[1,2,3,null,null,4,5]")));
            System.out.println(serializePreOrder(deserializePreOrderNoRecursive(serializePreOrder(SeDesV1.deserialize("[1,2,3,null,null,4,5]")))));
        }
    }

    /**
     *      a
     *    b   c
     *   f       d
     *             e
     *  a b c null null null null d null e null null
     */
    static class SeDesV1{
        public static String serialize(TreeNode root) {
            Queue<TreeNode> todo = new LinkedList<>();
            List<Integer> resList = new ArrayList<>();
            if(root!=null){
                todo.offer(root);
                while(!todo.isEmpty()){
                    TreeNode cur = todo.poll();
                    if(cur!=null){
                        resList.add(cur.val);
                        todo.offer(cur.left);
                        todo.offer(cur.right);
                    }else{
                        resList.add(null);
                    }
                }
            }
            return resList.toString();
        }

        public static TreeNode deserialize(String data) {
            data = data
                    .replaceAll("\\[","")
                    .replaceAll("]","")
                    .replaceAll(" ","");
            if(data==null || data.trim().length()<=0) return null;
            String[] dataArr = data.split(",");
            Queue<TreeNode> todo = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.valueOf(dataArr[0]));
            todo.offer(root);
            int i = 1;
            while(!todo.isEmpty()){
                TreeNode cur = todo.poll();
                if(i>dataArr.length-1) break;
                if(!"null".equals(dataArr[i])){
                    TreeNode left = new TreeNode(Integer.valueOf(dataArr[i]));
                    cur.left = left;
                    todo.offer(left);
                }
                i++;
                if(i>dataArr.length-1) break;
                if(!"null".equals(dataArr[i])){
                    TreeNode right = new TreeNode(Integer.valueOf(dataArr[i]));
                    cur.right = right;
                    todo.offer(right);
                }
                i++;
            }
            return root;
        }

        public static void test(){
            System.out.println(serialize(deserialize("[1,2,3,null,null,4,5]")));
        }
    }

    /**
     * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     * 这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     * 此实现超出内存限制
     */
    static class SeDes{
        public static String serialize(TreeNode root) {
            Map<TreeNode, Integer> node2Index = new HashMap<>();
            Queue<TreeNode> todo = new LinkedList<>();
            List<String> resList = new ArrayList<>();
            if(root!=null){
                todo.offer(root);
                resList.add(String.valueOf(root.val));
                node2Index.put(root,0);
                while(!todo.isEmpty()){
                    TreeNode cur = todo.poll();
                    int parentIndex = node2Index.get(cur);
                    if(cur.left!=null){
                        todo.offer(cur.left);
                        int toIndex = 2*(parentIndex+1)-1;
                        insertInternal(cur.left, toIndex, resList);
                        node2Index.put(cur.left, toIndex);
                    }
                    if(cur.right!=null){
                        todo.offer(cur.right);
                        int toIndex = 2*(parentIndex+1);
                        insertInternal(cur.right, toIndex, resList);
                        node2Index.put(cur.right, toIndex);
                    }
                }
            }
            return String.join(",",resList);
        }

        private static void insertInternal(TreeNode right, int toIndex, List<String> resList) {
            while(true){
                if(resList.size()-1<toIndex) resList.add(null);
                else  break;
            }
            resList.set(toIndex, String.valueOf(right.val));
        }

        public static TreeNode deserialize(String data) {
            if(data==null||data.trim().length()<=0) return null;
            String[] dataArr = data.split(",");
            Map<Integer, TreeNode> index2Node = new HashMap<>();
            TreeNode root = new TreeNode(Integer.valueOf(dataArr[0]));
            index2Node.put(0, root);
            // [1,2,3,null,null,4,5]
            //  0,1,2,3   ,4   ,5,6
            for(int i=1;i<dataArr.length;i++){
                if(!"null".equals(dataArr[i])){
                    TreeNode cur = new TreeNode(Integer.valueOf(dataArr[i]));
                    index2Node.put(i,cur);
                    if(i%2==0){ // right node
                        int parentIdx = i/2-1;
                        index2Node.get(parentIdx).right= cur;
                    }else{ // left node
                        int parentIdx = i/2;
                        index2Node.get(parentIdx).left= cur;
                    }
                }
            }
            return root;
        }

        public static void test(){
            System.out.println(String.format("empty tree serial : %s", SeDes.serialize(null)));
            System.out.println(serialize(deserialize("1,2,3,null,null,4,5")));
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-preorder-traversal/
     * 前序遍历，Node Left Right
     * @param root
     */
    private static List<Integer> preOrderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> tmp = new Stack<>();
        if(root!=null) tmp.push(root);
        while(!tmp.isEmpty()){
            TreeNode cur = tmp.pop();
            res.add(cur.val);
            if(cur.right!=null) tmp.push(cur.right);
            if(cur.left!=null) tmp.push(cur.left);
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     * 中序遍历，Left Node Right
     * @param root
     * @return
     */
    private static List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> tmp = new Stack<>();
        TreeNode cur = root;
        while(!tmp.isEmpty()||cur!=null){
            for(;cur!=null;cur=cur.left){
                tmp.push(cur);
            }
            cur = tmp.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/
     * 后序遍历，Left Right Node
     * @param root
     * @return
     */
    private static List<Integer> postOrderTraversal(TreeNode root){
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> tmp = new Stack<>();
        if(root!=null) tmp.push(root);
        while(!tmp.isEmpty()){
            TreeNode cur = tmp.pop();
            res.addFirst(cur.val);
            if(cur.left!=null) tmp.push(cur.left);
            if(cur.right!=null) tmp.push(cur.right);
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        int res = 0;
        if(root!=null){
            int left = maxDepth(root.left)+1;
            int right = maxDepth(root.right)+1;
            res = Math.max(left,right);
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root){
        int res = 0;
        if(root!=null){
            if(root.left!=null&&root.right!=null) res = Math.min(minDepth(root.left)+1,minDepth(root.right)+1);
            else if (root.left==null&&root.right!=null) res = minDepth(root.right)+1;
            else if (root.left!=null&&root.right==null) res = minDepth(root.left)+1;
            else res = 1;
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/print-binary-tree/
     */
    static class PrintTree{
        public static List<List<String>> solveV1(TreeNode root) {
            List<List<String>> res = new ArrayList<>();
            int height = maxDepth(root);
            int n = (1<<height);
            for(int i=0;i<height;i++){
                List<String> tmp = new ArrayList<>(n-1);
                for(int j=0;j<n-1;j++)tmp.add("");
                res.add(tmp);
            }
            solveV1Sub(root, res, 0, 0, n-2);
            return res;
        }

        private static void solveV1Sub(TreeNode root, List<List<String>> res , int row, int left, int right){
            if(root!=null){
                List<String> curRow = res.get(row);
                int mid = (left+right)/2;
                curRow.set(mid, String.valueOf(root.val));
                solveV1Sub(root.left, res, row+1, left, mid-1);
                solveV1Sub(root.right, res, row+1, mid+1, right);
            }
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     *
     */
    static class LevelOrderTraveral2{
        public static List<List<Integer>> solveV1(TreeNode root) {
            Queue<TreeNode> toDos = new LinkedList<>();
            Queue<TreeNode> toDosLevel = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            if(root!=null) toDos.offer(root);
            int level = 1;
            while(true){
                while(!toDos.isEmpty()) {
                    TreeNode tmp = toDos.poll();
                    if(res.size()<level){
                        res.add(new ArrayList<>());
                    }
                    List<Integer> curRes = res.get(level - 1);
                    curRes.add(tmp.val);
                    if(tmp.left!=null) toDosLevel.offer(tmp.left);
                    if(tmp.right!=null) toDosLevel.offer(tmp.right);
                }
                if(toDosLevel.isEmpty()) break;
                else{
                    toDos.addAll(toDosLevel);
                    toDosLevel.clear();
                    level++;
                }
            }
            Collections.reverse(res);
            return res;
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal/
     *
     */
    static class LevelOrderTraveral{

        /**
         * time - ???
         * space -
         * @param root
         * @return
         */
        public static List<List<Integer>> solveV1(TreeNode root){
            List<List<Integer>> res = new ArrayList<>();
            solveV1Sub(root, 1, res);
            return res;
        }

        private static void solveV1Sub(TreeNode root, int level, List<List<Integer>> res) {
            if(root!=null) {
                if(res.size()<level){
                    res.add(new ArrayList<>());
                }
                List<Integer> curRes = res.get(level - 1);
                curRes.add(root.val);
                solveV1Sub(root.left, level+1, res);
                solveV1Sub(root.right, level+1, res);
            }
        }


        /**
         * time - ???
         * space -
         * @param root
         * @return
         */
        public static List<List<Integer>> solveV2(TreeNode root){
            Queue<TreeNode> toDos = new LinkedList<>();
            Queue<TreeNode> toDosLevel = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            if(root!=null) toDos.offer(root);
            int level = 1;
            while(true){
                while(!toDos.isEmpty()) {
                    TreeNode tmp = toDos.poll();
                    if(res.size()<level){
                        res.add(new ArrayList<>());
                    }
                    List<Integer> curRes = res.get(level - 1);
                    curRes.add(tmp.val);
                    if(tmp.left!=null) toDosLevel.offer(tmp.left);
                    if(tmp.right!=null) toDosLevel.offer(tmp.right);
                }
                if(toDosLevel.isEmpty()) break;
                else{
                    toDos.addAll(toDosLevel);
                    toDosLevel.clear();
                    level++;
                }
            }
            return res;
        }

    }


    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     *
     */
    private static void levelOrderTraveral2(TreeNode root){

    }





    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        /**
         *
         * @param input
         * @return
         */
        public static TreeNode fromString(String input){
            String[] strs = input.split(",");
            List<Integer> is = new ArrayList<>();
            for (String str : strs) {
                if("null".equalsIgnoreCase(str)) is.add(null);
                else is.add(Integer.parseInt(str));

            }
            return fromList(is);
        }

        /**
         *
         * leetcode上二叉树数组存储方式，转换为链表存储
         * 定义猜测 - 每层不满，用null表示新一层。null也用来表示子节点为空。
         * 例子：
         * [1,2,null,3,null,4,null,5]
         * [3,9,20,null,null,15,7]
         * [1,1,1,1,1,null,1]
         * [2,2,2,5,2]
         * [1,3,2,5,3,null,9]
         * [1,2,3,null,5,null,4]
         * @param input
         * @return
         */
        public static TreeNode fromList(List<Integer> input){
            TreeNode root = null;
            if(input==null || input.size()==0) return root;
            Queue<TreeNode> toSetSubs = new ArrayDeque<>();
            root = new TreeNode(input.get(0));
            toSetSubs.add(root);
            for(int i=1;i<input.size();){
                TreeNode parent = toSetSubs.poll();
                if(input.get(i)!=null) {
                    TreeNode leftNode = new TreeNode(input.get(i));
                    parent.left = leftNode;
                    toSetSubs.add(leftNode);
                }
                i++;
                if(i<input.size()){
                    if(input.get(i)!=null) {
                        TreeNode rightNode = new TreeNode(input.get(i));
                        parent.right = rightNode;
                        toSetSubs.add(rightNode);
                    }
                }
                i++;
            }
            return root;
        }
    }


}

