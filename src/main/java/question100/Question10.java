package question100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @author Lu Chao
 * @create 2022/4/23
 */
public class Question10 {

    public static void main(String[] args) {
        Question10 q = new Question10();
//        System.out.println(Arrays.toString(q.twoSum(new int[]{3, 3}, 6)));
//        System.out.println(q.addTwoNumbers(q.getListNode(9), q.getListNode(9999999991L)));
        System.out.println(q.lengthOfLongestSubstring("aabaab!bb"));
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
        if (nums.length <= 2 || nums.length > 10000) {
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

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进1标记
        boolean flag = false;
        ListNode last = null, root = null;
        while (l1 != null && l2 != null) {
            int x = l1.val;
            int y = l2.val;
            int z = flag ? (x + y + 1) : (x + y);
            flag = (z >= 10);
            // 存不存在上一个节点
            if (last == null) {
                // 不存在 创建根节点
                root = new ListNode();
                last = root;
                last.val = z % 10;
            } else {
                // 存在 创建当前节点，并将上一节点指向当前节点
                ListNode now = new ListNode();
                now.val = z % 10;
                last.next = now;
                last = now;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (last == null) {
            return new ListNode(0, null);
        }
        ListNode l = l1 == null ? l2 : l1;
        while (l != null) {
            int z = flag ? (l.val + 1) : l.val;
            flag = (z >= 10);
            ListNode now = new ListNode();
            now.val = z % 10;
            last.next = now;
            last = now;
            l = l.next;
        }
        if (flag) {
            ListNode now = new ListNode(1, null);
            last.next = now;
        }
        return root;
    }

    public ListNode getListNode(long z) {
        if (z == 0) {
            return new ListNode(0, null);
        }
        List<Character> numbers = Stream.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').collect(Collectors.toList());
        char[] chars = String.valueOf(z).toCharArray();
        ListNode result = new ListNode();
        ListNode t = result;
        for (int k = chars.length - 1; k >= 0; k--) {
            t.val = numbers.indexOf(chars[k]);
            if (k != 0) {
                ListNode s = new ListNode();
                t.next = s;
                t = s;
            }
        }
        return result;
    }

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        // 转成数组
        char[] chars = s.toCharArray();
        int max = 1;
        List<Character> children = new ArrayList<>();
        int i = 0, j = 0;
        while (j < chars.length) {
            if (i == j) {
                children.add(chars[i]);
                j++;
                continue;
            }
            // 判断子串是否包含当前字符
            int x = children.indexOf(chars[j]);
            if (x == -1) {
                children.add(chars[j]);
                j++;
                continue;
            }
            // 包含当前元素
            if (max < children.size()) {
                max = children.size();
            }
            int y = x + 1;
            // i 移动到原数组下一个位置
            i = i + y;
            // j 移动到i的下个位置 或者不动
            j = i >= j ? (i + 1) : j;
            // 子串保留之后的内容
            if (y==children.size()){
                children.clear();
                children.add(chars[i]);
            }else {
                children = children.subList(y, children.size());
            }
        }
        if (max < children.size()) {
            max = children.size();
        }
        return max;
    }


}
