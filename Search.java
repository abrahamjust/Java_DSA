public class Search 
{
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 6, 7, 8, 94, 3, 53};
        int n = arr.length;

        int[] sortedArray = {10, 20, 30};
        int m = sortedArray.length;

        //linear search
        // -1 if element is not present
        System.out.println(linearSearch(arr, n, 94));
        System.out.println(linearSearch(arr, n, 83));

        System.out.println();

        //binary search
        // -1 if element is not found
        System.out.println(binarySearch(sortedArray, m, 30));
        System.out.println(binarySearch(sortedArray, m, 99));
    }    

    //linear search 
    public static int linearSearch(int[] arr, int n, int value){
        for(int i = 0; i < n; i++){
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int sortedArray[], int m, int value){
        int low = 0;
        int high = m-1;
        while(low <= high){
            //can also do 
            //int mid = low + (high - low) / 2
            //        = (2low + high - low) / 2
            //        =  (high + low) / 2
            /* reason why we do low + (high - low) / 2 is cause for large arrays, we may get overflow condition when we do low + high, so to prevent
             * that we do this formula
             */
            int mid = (high + low) / 2;
            if(sortedArray[mid] == value){
                return mid;
            }
            if(sortedArray[mid] > value){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return low;
    }
}
