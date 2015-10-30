public static class ListNode{
	int val;
	ListNode next;

	public ListNode(int val){
		this.val = val;
	}
}
//1.求链表中结点个数
public static int getListLength(ListNode head){
	if(head == null){
		return 0;
	}
	int length = 0;
	while(node != null){
		length ++;
		node = node.next;
	}
	return length;
}

//2. 反转链表
public staic ListNode reverse(ListNode head){
	if(head == null || head.next == null){
		return head;
	}
	ListNode pre = null;
	while(head != null){
		ListNode temp = head.next;
		head.next = pre;
		pre = head;
		head = temp;
	}
	return pre;
}

//recursion 反转链表

  public ListNode reverseList(ListNode head) {
        //recursion
        if(head == null || head.next == null){
            return head;
        }
        ListNode rehead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rehead;
        }

//3. 查找单链表中的倒数第k个点
 public static ListNode nthToLast(ListNode head, int n) {
        // write your code here
        if(head == null){
            return head;
        }
        ListNode slow = head;
        for(int i = 1; i < n; i++){
            if(head == null){
                return head;
            }
            head = head.next;
        }
        while(head != null && head.next != null){
            head = head.next;
            slow = slow.next;
        }
      
        return slow;
    }



// 4.查找中间结点

    public static ListNode findMid(ListNode head){
    	if(head == null || head.next == null){
    		return head;
    	}
    	ListNode slow = head;
    	ListNode fast = head.next;
    	while(fast != null && fast.next != null){
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	return slow;
    }
//5.从尾到头打印单链表: 对于这种颠倒顺序的问题，我们就想到用stack。这题要么自己写stack，要么就让系统使用stack，
    //也就是利用recursion

    public void print(ListNode head){
    	if(head == null){
    		return;
    	}
    	Stack<ListNode> stack = new Stack<ListNode>();
    	while(head != null){
    		stack.push(head);
    		head = head.next;
    	}
    	while(!stack.isEmpty()){
    		ListNode node = stack.pop();
    		System.out.println(node.val+ “ ”);
    	}
    }

    //递归从尾到头打印单链表
    public static void print(ListNode head){
    	if(head == null){
    		return;
    	}
    	print(head.next);
    	System.out.println(head.val + " ");
    }

// 6. merge两个链表：注意两个链表都为空，或者其中一个为空的情况
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode copy = result;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                copy.next = l1;
                l1 = l1.next;
                copy = copy.next;
            }
            else{
                copy.next = l2;
                l2 = l2.next;
                copy = copy.next;
            }
        }
        if(l1 != null){
            copy.next = l1;
        }
        else{
            copy.next = l2;
        }
        return result.next;
    }
//7. 判断一个单链表中是否有环: hasCycle 
       public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != slow){
            if(fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
    //找出环的起始点： 注意最后一定是slow = head, 然后while中是fast.next != slow 然后最后return slow
     public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = head;
        while(fast.next != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }



 //* 8. 判断两个单链表是否相交: isIntersect 

//求两个链的交点，注意首先判断两个链的长度，然后diff = abs(a, b),然后长的那个先走diff，然后两个人再一起走，这样
  // 就能找到交叉点。但是要考虑没有交叉点的情况,即首先在遍历两个list的时候要先看结尾是否为同一个点。还有就是遍历完了，
    //实际我们的headA,headB都变了，然后下面的操作就不能用headA，而是要用之前存过的东西~~
       public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        int countA = 1;
        int countB = 1;
        ListNode heada = headA;
        ListNode headb = headB;
        while(headA != null && headA.next != null){
           countA++;
           headA = headA.next;
        }

        while(headB != null && headB.next != null){
            countB++;
            headB = headB.next;
        }
        if(headA != headB){
        	//no intersection
          return null;
        }
        int diff = Math.abs(countA - countB);
        if(countA > countB){
            heada = walkFirst(heada, diff);
        }
        else{
            headb = walkFirst(headb, diff);
        }
        while(heada != headb){
            heada = heada.next;
            headb = headb.next;
        }
        return heada;
    }
    private ListNode walkFirst(ListNode head, int n){
        if(n <= 0){
            return head;
        }
        if(head == null){
            return head;
        }
        for(int i = 0; i < n; i++){
            head = head.next;
        }
        return head;
    }
 * 9. 求两个单链表相交的第一个节点: getFirstCommonNode 
 * 10. 已知一个单链表中存在环，求进入环中的第一个节点: getFirstNodeInCycle, getFirstNodeInCycleHashMap 

类似的方法可以用来find duplicate number in an array：
 其实那道题还有个解法，假设一个数是i，那就找nums[i]，然后把nums[i]乘以-1，然后一直这样做，
 当你发现你的nums[i]是负数的时候就说明以有duplicate
 但是leetcode上要求不能更改array
   public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while(fast != slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }








 * 11. 给出一单链表头指针pHead和一节点指针pToBeDeleted，O(1)时间复杂度删除节点pToBeDeleted: delete 
 * 12. swap nodes in pairs: 利用recursion，就是先把前两个node给swap好，然后后面的直接recursion做
    
     public ListNode swapPairs(ListNode head) {
       if(head == null || head.next == null){
           return head;
       } 
       //consider only 2 nodes exist in the list
       ListNode second = head.next;
       if(head.next.next == null){
           second.next = head;
           head.next = null;
           return second;
       }
       //when more than 2 nodes exist, solve problem by using recursion
       ListNode newHead = swapPairs(head.next.next);
       second.next = head;
       head.next = newHead;
       return second;
    }
    *13 看linkedlist是不是palindrom 那就把中点找到，reverse后面的部分，然后比较两个list
    *14 merge k linkedlist

class ListNodeComparator implements Comparator<ListNode>{
	public int compare(ListNode n1, ListNode n2){
         return n1.val- n2.val;
	}
}

public ListNode mergeKLists(ListNode[] lists){
	if(lists == null || lists.length == 0){
return null;
	}
	PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new ListNodeComparator());

	for(ListNode n : lists){
    if(n != null){
heap.offer(n);
    }
	}
	ListNode result = new ListNode(-1);
	ListNode resultCopy = result;
	while(head.size() != 0){              注意priorityqueue没有isEmpty()函数，所以得看size，而且offer, poll
     ListNode node = heap.poll();
     ListNode node1 = node.next;
    if(node1 != null){
heap.offer(node1);
result.next = node;
result = node;
    }
	}
	result.next = null;      别忘了
	return resultCopy.next;
}

------------------------------------------------------------------------------------------
lintcode中的题
1. Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if(head == null){
            return null;
        }
        ListNode node = new ListNode(0);
        ListNode node1 = node;
        node.next = head;
        while(node != null && node.next != null){
            if(node.next.val != val){
                node = node.next;
            }
            else{
                node.next = node.next.next;
            }
        }
        return node1.next;
    }
2. remove duplicates from unsorted list
如果用set存的话一定要存val不能存listnode，而且为了删除方便，我们要每次看head.next的val
这样方便remove
 public ListNode removeDuplicates(ListNode head) { 
        // Write your code here
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = new ListNode(-1);
        pre.next = head;
        head = pre;
        ListNode result = head;
        HashSet<Integer> set = new HashSet<Integer>();
        while(head != null && head.next != null){
            if(!set.contains(head.next.val)){
                set.add(head.next.val);
                head = head.next;
                
            }
            else{
                head.next = head.next.next;
            }
            
        }
        return result.next;
    }  
不用set的话，可以sort，但是这样的话不能保证order
  private ListNode mergeSort(ListNode head){
         if(head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast !=null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = mergeSort(slow.next);
        slow.next = null;
        ListNode left = mergeSort(head);
        ListNode newHead = merge(left, right);
        return newHead;
    }
    private ListNode merge(ListNode left, ListNode right){
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        ListNode head = new ListNode(0);
        ListNode result = head;
        while(left != null && right != null){
            if(left.val < right.val){
                head.next = left;
                left = left.next;
                head = head.next;
            }
            else{
                head.next = right;
                right = right.next;
                head = head.next;
            }
        }
        if(left != null){
            head.next = left;
        }
        if(right != null){
            head.next = right;
        }
        return result.next;
    }
    要想保证order，就得o(n*n)


3. remove Nth node from end of list
linkedlist首先考虑头结点是否会改变，如果会，那么就要新建一个pre
最后要记得返回head.next
 ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if(head == null || n == 0){
            return null;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        head = pre;
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < n; i++){
            if(fast == null){
                return null;
            }
            fast = fast.next;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        if(slow.next == null){
            return null;
        }
        slow.next = slow.next.next;
        return head.next;
    }
3. partition list

 public ListNode partition(ListNode head, int x) {
        // write your code here
        if(head == null){
            return null;
        }
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode result = left;
        ListNode right1 = right;
        while(head != null){
            if(head.val < x){
                left.next = head;
                head = head.next;
                left = left.next;
            }
            else{
                right.next = head;
                right = right.next;
                head = head.next;
            }
        }
        left.next = right1.next;
        right.next = null;
        return result.next;
        
    }

4. palindrome linked list
 public boolean isPalindrome(ListNode head) {
        // Write your code here
        if(head == null || head.next == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = reverse(slow.next);
        slow.next = null;
        while(head != null && right != null){
            if(head.val != right.val){
                return false;
            }
            head = head.next;
            right = right.next;
        }
        return true;
    }
    private ListNode reverse(ListNode head){
        if(head == null){
            return null;
        }
        ListNode pre = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

5. swapPairs
   public ListNode swapPairs(ListNode head) {
        // Write your code here
        if(head == null || head.next == null){
            return head;
        }
       // ListNode newHead = new ListNode(0);
        ListNode newHead = head.next;
        ListNode right = swapPairs(head.next.next);
        head.next.next = head;
        head.next = right;
        return newHead;
    }


6. add two numbers II
给定6->1->7 + 2->9->5, that is 617 + 295
做加法肯定是从个位开始容易，所以我就把给定的list都reverse了
然后开始做加法·弄了个carry，每次x = (a.val + b.val + carry)如果a,b都不为null
然后还要记得a = a.next b = b.next 特别容易忘！
x>= 10，那就取余，然后carry = 1，*完事别忘了把carry置0
然后到循环结束的时候再判断一下carry==1?等于的话就加个1，然后把结果再reverse一下
public ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
     ListNode result = new ListNode(0);
     ListNode copy = result;
     ListNode a = reverse(l1);
     ListNode b = reverse(l2);
     int carry = 0;
     while(a != null || b != null){
         int x = 0;
         if(a == null){
             x = b.val + carry;
             b = b.next;
         }
         else if(b == null){
             x = a.val + carry;
             a = a.next;
         }
         else{
            x = (a.val + b.val + carry); 
            a = a.next;
            b = b.next;
         }
         carry = 0;
         if(x >= 10){
             x = x % 10;
             carry = 1;
         }
         result.next = new ListNode(x);
         result = result.next;
        
     }
     if(carry == 1){
        result.next = new ListNode(1); 
       // result = result.next;
     }
     return reverse(copy.next);
    }
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }




