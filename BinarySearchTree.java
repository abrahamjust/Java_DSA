public class BinarySearchTree 
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

    //we call this method to insert value for easiness.
    //this method then calls the other insert mthod for the actual operation
    public void insert(int value){
        root = insert(root, value);
    }

    //method to actually insert the values
    public TreeNode insert(TreeNode root, int value){
        if(root == null){
            root = new TreeNode(value);
            return root;
        }
        if(value < root.data){
            root.left = insert(root.left, value);
        } else{
            root.right = insert(root.right, value);
        }
        return root;
    }

    //inorder traversal of BST (gives values in ascending order)
    public void inOrder(TreeNode root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    //we actually call this method instead of the other one for easiness
    public TreeNode search(int key){
        return search(root, key);
    }

    //called by the other method
    //method to search for a key in the BST
    public TreeNode search(TreeNode root, int key){
        if(root == null || root.data == key){
            return root;
        }
        if(key < root.data){
            return search(root.left, key);
        } else{
            return search(root.right, key);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        //inserting values into the BST
        bst.insert(5);
        bst.insert(6);
        bst.insert(2);
        bst.insert(7);
        bst.insert(3);

        //inorder traversal of BST (gives values in ascending order)
        bst.inOrder(bst.root);
        System.out.println();

        //searching for a key in the BST (using recursion)
        if(null != bst.search(5)){
            System.out.println("key found !!!");
        } else{
            System.out.println("key not found");
        }

        if(null != bst.search(50)){
            System.out.println("key found !!!");
        } else{
            System.out.println("key not found");
        }
    }    
}
