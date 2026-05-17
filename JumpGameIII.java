class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length]; //keeps track of visited to avoid infinite loops
        return dfs(arr, start, visited);
    }
    private boolean dfs(int[] arr, int start, boolean[] visited) {
        if (start < 0 || start >= arr.length || visited[start]) { // start should be in between 0 and n-1, and not visited
            return false;
        }
        if (arr[start] == 0) {
            return true; // since 0 is found return true
        }
        visited[start] = true;
        return dfs(arr, start + arr[start], visited) ||
               dfs(arr, start - arr[start], visited); //since two paths can be chosen call 2 functions to go in two paths, it returns true if one path exists
    }
}
