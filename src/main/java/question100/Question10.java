package question100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lu Chao
 * @create 2022/4/23
 */
public class Question10 {

    public static void main(String[] args) {
        Question10 q = new Question10();
        System.out.println(Arrays.toString(q.twoSum(new int[]{3,3}, 6)));
    }


    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums.length <= 2 || nums.length> 10000) {
            return new int[]{0, 1};
        }
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];
            if (map.containsKey(a)) {
                return new int[]{i, map.get(a)};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{0, 1};
    }
}
