// BFS Approach, T.C - O(n), S.C - O(n)
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int farthest = 0; // keeps track of the max point reached
        int n = s.length();
        while (!queue.isEmpty()) { // while new positions to jump to exit
            int current = queue.poll(); // current index
            int start = Math.max(current + minJump, farthest + 1); // far+1 is used to ignore already visited indexes
            int end = Math.min(current + maxJump, n - 1); 
            for (int i = start; i <= end; i++) {
                if (s.charAt(i) == '0') {
                    if (i == n - 1) return true; // if index reaches n-1 and char at index is '0' return true
                    queue.offer(i); // add possible positions to jump to
                }
            }
            farthest = end; // last max position reached
        }
        return n == 1; // returns true for the edge case when n =1 and false for the rest
    }
}
