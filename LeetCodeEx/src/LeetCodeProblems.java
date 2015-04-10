import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import structs.ListNode;

public class LeetCodeProblems {

	public static void main(String[] args) {

		int a = reverse(1534236469);
		System.out.println(a);
	}

	public static int[] twoSum(int[] numbers, int target) {

		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {

			Integer diff = (Integer) (target - numbers[i]);
			if (hash.containsKey(diff)) {
				int toReturn[] = { hash.get(diff) + 1, i + 1 };
				return toReturn;
			}

			hash.put(numbers[i], i);

		}

		return null;

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		double n1 = 0, n2 = 0;
		double i = 1;

		while (l1 != null) {
			n1 += i * l1.val;
			i = i * 10;
			l1 = l1.next;
		}

		i = 1;
		while (l2 != null) {
			n2 += i * l2.val;
			i = i * 10;
			l2 = l2.next;
		}

		double sum = n2 + n1;

		int h = (int) (sum % 10);
		ListNode head = new ListNode(h);
		head.next = null;
		ListNode dum = new ListNode(0);
		dum.next = head;
		sum = sum / 10;

		while (sum >= 1) {

			int q = (int) (sum % 10);
			ListNode tmp = new ListNode(q);
			tmp.next = null;
			head.next = tmp;
			head = tmp;
			sum = sum / 10;

		}

		return dum.next;

	}

	public int lengthOfLongestSubstring(String s) {
		boolean[] flag = new boolean[256];

		int result = 0;
		int start = 0;
		char[] arr = s.toCharArray();

		for (int i = 0; i < arr.length; i++) {
			char current = arr[i];
			if (flag[current]) {
				result = Math.max(result, i - start);
				for (int k = start; k < i; k++) {
					if (arr[k] == current) {
						start = k + 1;
						break;
					}
					flag[arr[k]] = false;
				}
			} else {
				flag[current] = true;
			}
		}

		result = Math.max(arr.length - start, result);

		return result;
	}

	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;

		if ((m + n) % 2 != 0) // odd
			return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
		else { // even
			return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) + findKth(A,
					B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
		}
	}

	public static int findKth(int A[], int B[], int k, int aStart, int aEnd,
			int bStart, int bEnd) {

		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;

		if (aLen == 0)
			return B[bStart + k];
		if (bLen == 0)
			return A[aStart + k];
		if (k == 0)
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];

		int aMid = aLen * k / (aLen + bLen);
		int bMid = k - aMid - 1;

		aMid = aMid + aStart;
		bMid = bMid + bStart;

		if (A[aMid] > B[bMid]) {
			k = k - (bMid - bStart + 1);
			aEnd = aMid;
			bStart = bMid + 1;
		} else {
			k = k - (aMid - aStart + 1);
			bEnd = bMid;
			aStart = aMid + 1;
		}

		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}

	public String longestPalindrome(String s) {
		if (s.isEmpty()) {
			return null;
		}

		if (s.length() == 1) {
			return s;
		}

		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			String tmp = helper(s, i, i);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
			tmp = helper(s, i, i + 1);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
		}

		return longest;
	}

	public String helper(String s, int begin, int end) {
		while (begin >= 0 && end <= s.length() - 1
				&& s.charAt(begin) == s.charAt(end)) {
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}

	public static String convert(String s, int nRows) {

		int len = s.length();

		if (nRows <= 1 || nRows >= len)
			return s;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < nRows; i++) {
			int gap1 = 2 * (nRows - 1 - i);
			int gap2 = 2 * i;

			int j = i;
			int prevPos = 0;

			sb.append(s.charAt(j));

			while (j < s.length()) {
				prevPos = j;
				j += gap1;
				if (j != prevPos && j < s.length()) {
					sb.append(s.charAt(j));
				}
				prevPos = j;
				j += gap2;
				if (j != prevPos && j < s.length()) {
					sb.append(s.charAt(j));
				}
			}
		}

		return sb.toString();
	}

	public static int reverse(int x) {

		long res = 0;
		String r = Integer.toString(x);

		if (x >= 0) {

			String resultS = new StringBuilder(r).reverse().toString();
			res = Long.parseLong(resultS);

		} else {

			String resultS = new StringBuilder(r.substring(1, r.length()))
					.reverse().toString();
			String bla = new StringBuilder("-").append(resultS).toString();
			res = Long.parseLong(bla);

		}
		if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
			return 0;

		return (int) res;

	}

}
