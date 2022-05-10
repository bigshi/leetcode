package question100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lu Chao
 * @create 2022/4/23
 */
public class Question10 {

    public static void main(String[] args) {
        Question10 q = new Question10();
//        System.out.println(Arrays.toString(q.twoSum(new int[]{3, 3}, 6)));
//        System.out.println(q.addTwoNumbers(q.getListNode(9), q.getListNode(9999999991L)));
//        System.out.println(q.lengthOfLongestSubstring("aabaab!bb"));
//        System.out.println(q.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(q.longestPalindrome("0123456789"));
        System.out.println(q.convert("0123456789", 3));

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
            if (y == children.size()) {
                children.clear();
                children.add(chars[i]);
            } else {
                children = children.subList(y, children.size());
            }
        }
        if (max < children.size()) {
            max = children.size();
        }
        return max;
    }


    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1 == null ? 0 : nums1.length;
        int n = nums2 == null ? 0 : nums2.length;
        double d1 = 1.00000F;
        double d2 = 2.00000F;

        if (m == 0 && n == 0) {
            return 0F;
        }
        int[] nums3 = null;
        int x = 0;
        if (m == 0 || n == 0) {
            if (m == 0) {
                x = n;
                nums3 = nums2;
            } else {
                x = m;
                nums3 = nums1;
            }
        }
        // 有一个为空
        if (x != 0) {
            if (x % 2 == 0) {
                return (nums3[x / 2] + nums3[(x / 2) - 1]) / d2;
            }
            return nums3[x / 2] * d1;
        }
        // 都不为空
        x = m + n;
        // 串连的
        if (nums1[m - 1] <= nums2[0]) {
            nums3 = new int[x];
            System.arraycopy(nums1, 0, nums3, 0, m);
            System.arraycopy(nums2, 0, nums3, m, n);
        }
        if (nums2[n - 1] <= nums1[0]) {
            nums3 = new int[x];
            System.arraycopy(nums2, 0, nums3, 0, n);
            System.arraycopy(nums1, 0, nums3, n, m);
        }
        if (nums3 != null) {
            if (x % 2 == 0) {
                return (nums3[x / 2] + nums3[(x / 2) - 1]) / d2;
            }
            return nums3[x / 2] * d1;
        }
        // 非串连
        nums3 = new int[x];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                nums3[k] = nums1[i];
                i++;
            } else {
                nums3[k] = nums2[j];
                j++;
            }
            k++;
        }
        if (i < nums1.length) {
            System.arraycopy(nums1, i, nums3, k, m - i);
        }
        if (j < nums2.length) {
            System.arraycopy(nums2, j, nums3, k, n - j);
        }
        if (x % 2 == 0) {
            return (nums3[x / 2] + nums3[(x / 2) - 1]) / d2;
        }
        return nums3[x / 2] * d1;
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring/
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 单个字符
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        // 两个字符
        if (chars.length == 2) {
            return chars[0] == chars[1] ? s : String.valueOf(chars[0]);
        }
        int index = 0, last = 0, max = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = chars.length - 1; j >= i; j--) {
                int m = i;
                int n = j;
                while (m < n && chars[m] == chars[n]) {
                    m++;
                    n--;
                }
                if (m >= n) {
                    if (max < j - i) {
                        max = j - i;
                        index = i;
                        last = j;
                    }
                    break;
                }
            }
        }
        return s.substring(index, last + 1);
    }

    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<List<String>> cols = new ArrayList<>();
        char[] chars = s.toCharArray();
        int i = 0, j = 0;
        List<String> col = new ArrayList<>(numRows);
        // 将每个元素一个个放进去 我们先考虑横过来放
        while (i < chars.length) {
            // 如果是小于行数的，直接放
            if (j < numRows) {
                col.add(String.valueOf(chars[i]));
                i++;
                j++;
                continue;
            }
            // 一行放满，考虑非满行的 放法
            if (j < 2 * (numRows - 1)) {
                cols.add(col);
                col = new ArrayList<>(numRows);
                for (int k = 0; k < 2 * (numRows - 1) - j; k++) {
                    col.add("");
                }
                col.add(String.valueOf(chars[i]));
                for (int k = 2 * (numRows - 1) - j + 1; k < numRows; k++) {
                    col.add("");
                }
                i++;
                j++;
                continue;
            }
            cols.add(col);
            col = new ArrayList<>(numRows);
            j = 0;
        }

        for (int k = 0; k < numRows - j; k++) {
            col.add("");
        }
        cols.add(col);

        StringBuilder content = new StringBuilder();
        for (int m = 0; m < numRows; m++) {
            for (List<String> strings : cols) {
                if ("".equals(strings.get(m))) {
                    continue;
                }
                content.append(strings.get(m));
            }
        }
        return content.toString();
    }
}
