import java.util.Arrays;
public class SUM3{

    public int counttriplets(int[] nums) {
        Arrays.sort(nums);
        int Tvalid = 0;

        for(int i = 0; i< nums.length -2 ;i++ ){
            int j = i+1;
            int k = nums.length -1;

                while(j < k){

                    int sum = nums[i]+ nums[j] + nums[k];

                    if(sum == 0){
                        Tvalid++;

                        j++;
                        k--;

                    }else if (sum < 0){
                        j++;
                    }else if ( sum > 0){
                        k--;
                    }
                }
        }
        return Tvalid;
    }

}