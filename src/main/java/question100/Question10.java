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
//        System.out.println(q.convert("0123456789", 3));
//        System.out.println(q.reverse(901000));
//        System.out.println(q.myAtoi("4193 with words"));
        System.out.println(q.isPalindrome(112211));
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


    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        char[] newChars = new char[chars.length];
        int j = 0, k;
        boolean flag;
        if (chars[0] == '-') {
            newChars[0] = chars[0];
            k = 1;
            j++;
            flag = true;
        } else {
            k = 0;
            flag = false;
        }
        for (int i = chars.length - 1; i >= k; i--) {
            if (chars[i] == '0' && j < k + 1) {
                continue;
            }
            newChars[j] = chars[i];
            j++;
        }
        while (j < chars.length) {
            newChars[j] = ' ';
            j++;
        }

        String newNum = String.valueOf(newChars).trim();
        if ("".equals(newNum)) {
            return 0;
        }
        String max = String.valueOf(Integer.MAX_VALUE);
        String min = String.valueOf(Integer.MIN_VALUE);
        if (flag) {
            return min.length() > newNum.length() || min.compareTo(newNum) > 0 ? Integer.valueOf(newNum) : 0;
        }
        return max.length() > newNum.length() || max.compareTo(newNum) > 0 ? Integer.valueOf(newNum) : 0;

    }

    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     *
     * 函数 myAtoi(string s) 的算法如下：
     *
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/string-to-integer-atoi
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        // 直接面向用列编程 ，我直接吐了呀
        // 还是参照题解吧
        // 以下出自  https://leetcode.cn/u/sweetiee/
        char[] chars = s.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative ? -ans : ans;
    }


    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 例如，121 是回文，而 123 不是。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/palindrome-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int y = 10;
        if (x < y) {
            return true;
        }
        if (x % y == 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        int m = x % y;
        list.add(m);
        int n = x / y;
        while (n > 0) {
            list.add(n % y);
            n = n / y;
        }
        m = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            m = m + (list.get(i) * ((Double) Math.pow(y, list.size() - 1 - i)).intValue());
        }
        return m == x;

    }
}
