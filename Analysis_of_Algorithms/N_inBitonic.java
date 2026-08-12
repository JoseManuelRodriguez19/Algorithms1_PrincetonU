
public class N_inBitonic{

     public boolean isinbitonic(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;


        while( left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } 
            
            if (target < nums[mid]){
                return ascsearch(nums, target, left, mid-1) || descsearch(nums, target, mid+1, right);
            }else{

                boolean up = (mid< nums.length-1 && nums[mid] < nums[mid+1]);

                if (up){
                    left = mid + 1;
                }else{
                    
                    right = mid - 1;
                }

            }
            
        }
        return false;

    }

        private boolean ascsearch(int[] nums, int target, int left, int right){
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return false;
        }

        private boolean descsearch(int[] nums, int target, int left, int right){
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (target < nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        }
    
}


    