public class Sort
{
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 9, 7, 34, 97, 87};
        int n = arr.length;

        bubbleSort(arr, n);
        for(int i : arr){
            System.out.print(i + " ");
        }

        System.out.println();

        int arr1[] = {2, 5, 8, 9, 7, 34, 97, 87};
        insertionSort(arr1, n);
        for(int i : arr1){
            System.out.print(i + " ");

        }

        System.out.println();

        int arr2[] = {2, 5, 8, 9, 7, 34, 97, 87};
        selectionSort(arr2, n);
        for(int i : arr2){
            System.out.print(i + " ");

        }

        System.out.println();

        int arr3[] = {2, 5, 8, 9, 7, 34, 97, 87};
        mergeSort(arr3, new int[arr3.length], 0, arr3.length-1);
        for(int i : arr3){
            System.out.print(i + " ");

        }

        System.out.println();

        int arr4[] = {2, 5, 8, 9, 7, 34, 97, 87};
        quickSort(arr4, 0, arr4.length-1);
        for(int i : arr4){
            System.out.print(i + " ");

        }
    }

    public static void bubbleSort(int[] arr, int n){
        boolean isSwapped;
        for(int i = 0; i < n; i++){
            isSwapped = false;
            for(int j = 0; j < n-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwapped = true;
                }
            }
            if(isSwapped == false){
                break;
            }
        }
    }

    public static void selectionSort(int arr[], int n){
        //n-1 as after all swaps, the last element will be the max element
        for(int i = 0; i < n-1; i++){
            int min = i;
            //j = i+1 as we need to compare with the next value to ith index
            for(int j = i+1; j < n; j++){
                //storing the index of the minimum value
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            //swapping
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int arr[], int n){
        //starts from 1 as 0th index is considered sorted
        for(int i = 1; i < n; i++){
            //temp stores the value to be compared from the 'unsorted part'
            int temp = arr[i];
            int j = i-1;
            //shifting elements to right by 1 step if greater
            while(j >= 0 && arr[j] > temp){
                arr[j+1] = arr[j];
                j = j-1;
            }
            //inserting it in sorted part
            arr[j+1] = temp;
        }
    }

    public static void mergeSort(int arr[], int temp[], int low, int high){
        if(low < high){
            int mid = low + (high - low) / 2;
            mergeSort(arr, temp, low, mid);
            mergeSort(arr, temp, mid + 1, high);
            merge(arr, temp, low, mid, high);
        }
    }

    private static void merge(int arr[], int temp[], int low, int mid, int high){
        //copy all elements from array to temp till i <= high from i = low
        for(int i = low; i <= high; i++){
            temp[i] = arr[i];
        }
        int i = low, j = mid+1, k = low;
        //comparing values in temp and inserting in arr in the proper place
        while(i <= mid && j <= high){
            if(temp[i] <= temp[j]){
                arr[k] = temp[i];
                i++;
            } else{
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        //insert leftover element to the array (larger elements that arent inserted in array in the above while loop)
        //look video if u don't understand
        while(i <= mid){
            arr[k] = temp[i];
            i++;
            k++;
        }
    }

    public static void quickSort(int[] arr, int low, int high){
        //exit condition (if only 1 element then low = high so exit)
        if(low < high){
            //gets the pivot value returned and uses it to divide the array to two parts
            int partitionPoint = partition(arr, low, high);
            //the value at pivot position isnt touched as after the swapping, the pivot value will be at correct position
            //i.e the pivot value is sorted
            //like that each value is made pivot and hence gets put in correct position after swapping (sorted)
            quickSort(arr, low, partitionPoint - 1);
            quickSort(arr, partitionPoint + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high){
        int i = low;
        int j = low;
        int pivot = arr[high];
        while(i <= high){
            if(arr[i] <= pivot){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
            i++;
        }
        //return j-1 as after final swap, j will be pointing to element after the pivot
        return j - 1;
    }
}
