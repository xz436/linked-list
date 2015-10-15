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


















