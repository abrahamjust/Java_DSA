//use full extension, not util.* as the Stack is apparently getting shadowed
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree 
{
    private TreeNode root;
    
    private class TreeNode
    {
        private TreeNode left;
        private TreeNode right;
        private int data;

        public TreeNode(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //method to create the tree
    public void createBinaryTree(){
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode fourth = new TreeNode(4);
        TreeNode fifth = new TreeNode(5);
    
        //the first node is the root node, so root points to first
        //first node(i.e root) points to second and third
        root = first;
        first.left = second;
        first.right = third;
    
        //second node points to fourth and fifth nodes
        second.left = fourth;
        second.right = fifth;
    }

    //preorder traversal
    public void preOrder(TreeNode root){
        //if node points to null, return
        if(root == null){
            return;
        }
        //print data of the current 'root' node
        System.out.print(root.data + " ");
        //going left
        preOrder(root.left);
        //going right
        preOrder(root.right);
    }

    //preorder traversal using stack (no recursion)
    public void preOrder(){
        if(root == null){
            return;
        }
        Stack <TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            System.out.print(temp.data + " ");
            if(temp.right != null){
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
    }

    //method for inorder traversal
    public void inOrder(TreeNode root){
        //if null, return back to point of call
        if(root == null){
            return;
        }
        //going left
        inOrder(root.left);
        //printing the value
        System.out.print(root.data + " ");
        //going right
        inOrder(root.right);
    }

    //inorder traversal using stack (no recursion)
    public void inOrder(){
        if(root == null){
            return;
        }
        Stack <TreeNode> stack = new Stack<>();
        TreeNode temp  = root;
        while(!stack.isEmpty() || temp != null){
            if(temp != null){
                stack.push(temp);
                temp = temp.left;
            } else{
                temp = stack.pop();
                System.out.print(temp.data + " ");
                temp = temp.right;
            }
        }
    }

    public void postOrder(TreeNode root){
        //if null, return back to the call
        if(root == null){
            return;
        }
        //going left
        postOrder(root.left);
        //going right
        postOrder(root.right);
        //printing the data
        System.out.print(root.data + " ");
    }

    public void postOrder(){
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while(current != null || !stack.isEmpty()){
            if(current != null){
                stack.push(current);
                current = current.left;
            } else{
                TreeNode temp = stack.peek().right;
                if(temp == null){
                    temp = stack.pop();
                    System.out.print(temp.data + " ");
                    while(!stack.isEmpty() && temp == stack.peek().right){
                        temp = stack.pop();
                        System.out.print(temp.data + " ");
                    }
                } else{
                    current = temp;
                }
            }
        }
    }

    void levelOrder(){
        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.left != null){
                queue.offer(temp.left);
            }
            if(temp.right != null){
                queue.offer(temp.right);
            }
        }
    }

    public int findMax(TreeNode root){
        if(root == null){
            return Integer.MIN_VALUE;
        }
        int result = root.data;
        int left = findMax(root.left);
        int right = findMax(root.right);
        if(left > result){
            result = left;
        }
        if(right > result){
            result = right;
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        
        //creating the tree
        b.createBinaryTree();

        //preorder traversal of the tree
        //as the root variable is n instance of the class 'binary tree', use object to access it
        b.preOrder(b.root);
        System.out.println();

        //preorder traversal of tree using stack and no recursion(can use same name cause of polymorphism)
        b.preOrder();
        System.out.println();

        //inorder traversal of tree using recursion
        b.inOrder(b.root);
        System.out.println();

        //inorder traversal using stack (no recursion)
        b.inOrder();
        System.out.println();

        //postorder traversal using recursion
        b.postOrder(b.root);
        System.out.println();

        //postorder traversal using stack (no recursion)
        b.postOrder();
        System.out.println();

        //level order traversal
        b.levelOrder();
        System.out.println();

        //max value
        System.out.println("maximum value: " + b.findMax(b.root));
        System.out.println();
    }
}
