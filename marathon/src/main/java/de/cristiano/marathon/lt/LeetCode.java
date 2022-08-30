package de.cristiano.marathon.lt;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

record Pair(int column, int soldiers) {
}

record Element(int value, int column, int row) {

}

public class LeetCode {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) intersect(nums2, nums1);

        var result = new ArrayList<Integer>();

        var index1 = 0;
        var index2 = 0;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] > nums2[index2]) index2++;
            else if (nums2[index2] > nums1[index1]) index1++;
            else {
                result.add(nums2[index2]);

                index1++;
                index2++;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public int firstUniqChar(String s) {
        var frequency = new int[26];

        for (char c : s.toCharArray()) {
            frequency[c - 'a']++;
        }

        for (int index = 0; index < s.length(); index++) {
            if (frequency[s.charAt(index) - 'a'] == 1) return index;
        }

        return -1;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Integer currentMinSum = null;
        var response = new HashSet<String>();
        var map = new HashMap<String, Integer>();

        for (int index = 0; index < list1.length; index++) {
            map.put(list1[index], index);
        }

        for (int index = 0; index < list2.length; index++) {
            var word = list2[index];
            var pairIndex = map.get(word);

            if (pairIndex != null) {
                var indexSum = index + pairIndex;

                if (currentMinSum == null) currentMinSum = indexSum;
                if (currentMinSum == indexSum) response.add(word);
                if (currentMinSum > indexSum) {
                    response.clear();
                    response.add(word);
                    currentMinSum = indexSum;
                }
            }
        }

        return response.toArray(new String[0]);
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        var map = new HashMap<Character, Character>();

        for (int index = 0; index < s.length(); index++) {
            char sc = s.charAt(index);
            char tc = t.charAt(index);

            if (map.containsKey(sc) && map.get(sc) != tc) return false;
            else if (map.containsValue(sc)) return false;
            else if (!map.containsKey(sc)) map.put(sc, tc);
        }

        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer, List<Integer>>();

        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];

            var list = map.getOrDefault(num, new ArrayList<>());
            list.add(index);

            map.put(num, list);
        }

        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];
            var complement = target - num;

            if (map.containsKey(complement)) {
                List<Integer> complementPositions = map.get(complement);

                if (complement == num && complementPositions.size() > 1) {
                    return new int[]{complementPositions.get(0), complementPositions.get(1)};
                } else if (complement != num) return new int[]{index, complementPositions.get(0)};

            }
        }

        return null;
    }

    public boolean isHappy(int n) {
        var numbers = new HashSet<Integer>();
        var number = n;

        while (number != 1) {
            var newNumber = 0;
            for (char digit : String.valueOf(number).toCharArray()) {
                newNumber += Math.pow(digit - '0', 2);
            }
            if (!numbers.add(newNumber)) return false;

            number = newNumber;
        }

        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        var set1 = new HashSet<Integer>();
        var set2 = new HashSet<Integer>();
        var result = new int[nums1.length];
        var resultPosition = 0;

        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);

        for (Integer num : set1) {
            if (set2.contains(num)) {
                result[resultPosition] = num;
                resultPosition++;
            }
        }

        return Arrays.copyOf(result, resultPosition);
    }

    public boolean containsDuplicate(int[] nums) {
        var set = new HashSet<Integer>();

        for (int num : nums) {
            if (!set.add(num)) return true;
        }

        return false;
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        var usedBricks = 0;
        var usedLadders = 0;
        var currentBuilding = 0;
        var minHeap = new PriorityQueue<Integer>();

        for (int nextBuilding = 1; nextBuilding < heights.length; nextBuilding++) {
            if (heights[currentBuilding] < heights[nextBuilding]) {
                minHeap.offer(heights[nextBuilding] - heights[currentBuilding]);

                if (usedLadders < ladders) usedLadders++;
                else {
                    var smallestHeightDiff = minHeap.poll();

                    usedBricks += smallestHeightDiff;

                    if (usedBricks > bricks) return currentBuilding;
                }
            }

            currentBuilding++;
        }

        return currentBuilding;
    }

    public int connectSticks(int[] sticks) {
        if (sticks.length < 2) return 0;

        var totalCost = 0;
        var minHeap = new PriorityQueue<Integer>();

        for (int stick : sticks) {
            minHeap.offer(stick);
        }

        while (minHeap.size() > 1) {
            var cost = minHeap.poll() + minHeap.poll();
            totalCost += cost;

            if (!minHeap.isEmpty()) minHeap.offer(cost);
        }

        if (minHeap.size() == 1) totalCost += minHeap.poll();

        return totalCost;
    }

    public int[][] kClosest(int[][] points, int k) {
        var maxHeap = new PriorityQueue<int[]>(k + 1, Collections.reverseOrder(Comparator.comparingInt(
                point -> point[0] * point[0] + point[1] * point[1]
        )));

        for (int[] point : points) {
            maxHeap.offer(point);

            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.toArray(new int[][]{});
    }

    public int minMeetingRooms(int[][] intervals) {
        var rooms = new PriorityQueue<Integer>();
        Arrays.sort(intervals, Comparator.comparingInt(meeting -> meeting[0]));

        for (int[] meeting : intervals) {
            if (rooms.peek() != null && meeting[0] >= rooms.peek()) {
                rooms.poll();
                rooms.offer(meeting[1]);
            } else rooms.offer(meeting[1]);
        }

        return rooms.size();
    }

    public int kthSmallest(int[][] matrix, int k) {
        var position = 0;
        var minHeap = new PriorityQueue<>(Comparator.comparingInt(Element::value));

        for (int column = 0; column < matrix.length; column++) {
            minHeap.offer(new Element(matrix[column][0], column, 0));
        }

        while (position < k - 1) {
            var element = minHeap.poll();
            int row = element.row() + 1;

            if (row < matrix.length) {
                int column = element.column();
                var newElement = new Element(matrix[column][row], column, row);

                minHeap.offer(newElement);
            }

            position++;
        }

        return minHeap.peek().value();
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        var result = new int[k];

        var maxHeap = new PriorityQueue<Pair>(k + 1, Collections.reverseOrder((o1, o2) -> {
            var diff = o1.soldiers() - o2.soldiers();

            return diff == 0 ? o1.column() - o2.column() : diff;
        }));

        for (int column = 0; column < mat.length; column++) {
            maxHeap.add(new Pair(column, Arrays.stream(mat[column]).sum()));

            if (maxHeap.size() > k) maxHeap.poll();
        }

        for (int position = result.length - 1; position >= 0; position--) result[position] = maxHeap.poll().column();

        return result;
    }

    public int lastStoneWeight(int[] stones) {
        var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (int stone : stones) maxHeap.add(stone);

        while (maxHeap.size() > 1) {
            var result = maxHeap.poll() - maxHeap.poll();

            if (result > 0) maxHeap.add(Math.abs(result));
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public int[] topKFrequent(int[] nums, int k) {
        var frequency = new HashMap<Integer, Integer>();
        var minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(k, Comparator.comparingInt(Map.Entry::getValue));

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> numFreq : frequency.entrySet()) {
            if (minHeap.size() < k) minHeap.add(numFreq);
            else if (numFreq.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.add(numFreq);
            }
        }

        return minHeap.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    public int findKthLargest(int[] nums, int k) {
        var minHeap = new PriorityQueue<Integer>(k);

        for (int index = 0; index < k; index++) {
            minHeap.add(nums[index]);
        }

        for (int index = k; index < nums.length; index++) {
            if (nums[index] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[index]);
            }
        }

        return minHeap.peek();
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        var roomsVisited = new boolean[rooms.size()];
        var toVisit = new ArrayDeque<Integer>();

        toVisit.offer(0);

        while (!toVisit.isEmpty()) {
            var room = toVisit.pop();

            if (!roomsVisited[room]) {
                rooms.get(room).stream().filter(roomKey -> !roomsVisited[roomKey]).forEach(toVisit::add);

                roomsVisited[room] = true;
            }
        }

        for (boolean visited : roomsVisited) {
            if (!visited) return false;
        }

        return true;
    }

    public int[][] updateMatrix(int[][] mat) {
        var zeroDistance = new int[mat.length][mat[0].length];

        for (int[] ints : zeroDistance) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        for (int row = 0; row < zeroDistance.length; row++) {
            for (int column = 0; column < zeroDistance[row].length; column++) {
                if (mat[row][column] == 0) updateMinDistanceBFS(mat, zeroDistance, new Point(row, column));
            }
        }

        return zeroDistance;
    }

    private void updateMinDistanceBFS(int[][] mat, int[][] zeroDistance, Point startingPoint) {
        var distance = 0;
        var queue = new ArrayDeque<Point>();
        var visited = new int[mat.length][mat[0].length];

        queue.add(startingPoint);

        while (!queue.isEmpty()) {
            var levelElements = queue.size();

            for (int execution = 0; execution < levelElements; execution++) {
                var point = queue.pop();
                var row = point.row();
                var column = point.column();

                visited[row][column] = 1;
                zeroDistance[row][column] = Math.min(zeroDistance[row][column], distance);

                if (row - 1 >= 0 && visited[row - 1][column] == 0) queue.offer(new Point(row - 1, column));
                if (row + 1 < mat.length && visited[row + 1][column] == 0) queue.offer(new Point(row + 1, column));
                if (column - 1 >= 0 && visited[row][column - 1] == 0) queue.offer(new Point(row, column - 1));
                if (column + 1 < mat[row].length && visited[row][column + 1] == 0)
                    queue.offer(new Point(row, column + 1));
            }

            distance++;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        var startingPixel = image[sr][sc];
        var queue = new ArrayDeque<Point>();

        queue.offer(new Point(sr, sc));

        while (!queue.isEmpty()) {
            var levelElements = queue.size();

            for (int execution = 0; execution < levelElements; execution++) {
                var point = queue.pop();
                var row = point.row();
                var column = point.column();

                if (row >= 0 && row < image.length && column >= 0 && column < image[row].length && image[row][column] != color && image[row][column] == startingPixel) {
                    image[row][column] = color;

                    queue.offer(new Point(row + 1, column));
                    queue.offer(new Point(row + -1, column));
                    queue.offer(new Point(row, column + 1));
                    queue.offer(new Point(row, column - 1));
                }
            }
        }

        return image;
    }

    public String decodeString(String s) {
        var closingExpression = ']';
        var openingExpression = '[';
        var stack = new Stack<Character>();
        var decodedString = new StringBuilder();

        for (Character character : s.toCharArray()) {
            if (character != closingExpression) stack.push(character);
            else {
                var sb = new StringBuilder();

                while (stack.peek() != openingExpression) {
                    sb.append(stack.pop());
                }

                var word = sb.reverse().toString();
                sb = new StringBuilder();
                stack.pop();

                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.append(stack.pop());
                }

                int times = Integer.parseInt(sb.reverse().toString());

                for (char c : word.repeat(times).toCharArray()) {
                    stack.push(c);
                }
            }
        }

        stack.forEach(decodedString::append);

        return decodedString.toString();
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        var curr = root;
        var stack = new Stack<TreeNode>();
        var result = new ArrayList<Integer>();

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }

    public int findTargetSumWays(int[] nums, int target) {
        var depth = 0;
        var expressions = 0;
        var leafNode = depth == nums.length;
        var deque = new ArrayDeque<Integer>();
        var uniqueCombinations = new HashSet<String>();

        do {
            int num = nums[depth];


            deque.offer(num);
            deque.offer(num * -1);

            uniqueCombinations.add("+" + num);
            uniqueCombinations.add("-" + num);

            depth++;

        } while (!deque.isEmpty());


        return expressions;
    }

    public Node cloneGraph(Node root) {
        var stack = new Stack<Node>();
        var newRoot = new Node(root.val);
        var cloneNodesById = new HashMap<Integer, Node>();

        stack.add(root);
        cloneNodesById.put(newRoot.val, newRoot);

        while (!stack.isEmpty()) {
            var node = stack.pop();
            var cloneNode = cloneNodesById.get(node.val);

            cloneNode.neighbors = node.neighbors.stream().map(neighbor -> {
                if (!cloneNodesById.containsKey(neighbor.val)) {
                    stack.add(neighbor);
                    cloneNodesById.put(neighbor.val, new Node(neighbor.val));
                }

                return cloneNodesById.get(neighbor.val);
            }).collect(Collectors.toList());
        }

        return newRoot;
    }

    public int numIslands(char[][] grid) {
        var islands = 0;
        var stack = new Stack<int[]>();

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == '1') {
                    islands++;
                    stack.add(new int[]{row, column});

                    while (!stack.isEmpty()) {
                        var point = stack.pop();
                        var left = point[0];
                        var right = point[1];


                        if (left < grid.length && left >= 0 && right < grid[left].length && right >= 0 && grid[left][right] == '1') {
                            grid[left][right] = '0';

                            stack.add(new int[]{left + 1, right});
                            stack.add(new int[]{left - 1, right});
                            stack.add(new int[]{left, right + 1});
                            stack.add(new int[]{left, right - 1});
                        }
                    }
                }
            }
        }

        return islands;
    }

    public int evalRPN(String[] tokens) {
        var stack = new Stack<Integer>();
        var operations = new HashMap<String, BiFunction<Integer, Integer, Integer>>();

        operations.put("+", Math::addExact);
        operations.put("-", Math::subtractExact);
        operations.put("*", Math::multiplyExact);
        operations.put("/", (x, y) -> x / y);


        for (String token : tokens) {
            if (operations.containsKey(token)) {
                var y = stack.pop();
                var x = stack.pop();

                var result = operations.get(token).apply(x, y);

                stack.push(result);
            } else {
                var number = Integer.parseInt(token);
                stack.push(number);
            }
        }

        return stack.pop();
    }

    public int[] dailyTemperatures(int[] temperatures) {
        var monoStack = new Stack<Integer>();
        var waitingDays = new int[temperatures.length];

        for (int index = 0; index < temperatures.length; index++) {
            while (!monoStack.isEmpty() && temperatures[index] > temperatures[monoStack.peek()]) {
                Integer elementPosition = monoStack.pop();
                waitingDays[elementPosition] = index - elementPosition;
            }

            monoStack.add(index);
        }

        return waitingDays;
    }

    public boolean isValid(String s) {
        var validCombinations = new HashMap<Character, Character>();
        var stack = new ArrayList<Character>();

        validCombinations.put('(', ')');
        validCombinations.put('{', '}');
        validCombinations.put('[', ']');

        for (char element : s.toCharArray()) {
            if (validCombinations.containsKey(element)) stack.add(element);
            else if (stack.isEmpty()) return false;
            else {
                Character toClose = stack.remove(stack.size() - 1);

                if (validCombinations.get(toClose) != element) return false;
            }
        }

        return stack.isEmpty();
    }

    public int numSquares(int n) {
        var leastNumbers = 0;
        var perfectSquares = getPerfectSquares(n);
        Set<Integer> reminders = new HashSet<>();

        reminders.add(n);

        while (!reminders.isEmpty()) {
            leastNumbers++;

            reminders = reminders.stream().flatMap(reminder -> perfectSquares.stream().map(perfectSquare -> reminder - perfectSquare)).filter(reminder -> reminder >= 0).collect(Collectors.toSet());

            if (reminders.contains(0)) return leastNumbers;
        }

        return leastNumbers;
    }

    private List<Integer> getPerfectSquares(int n) {
        var seed = 2;
        var lastValue = 1;
        var perfectSquares = new ArrayList<Integer>();

        while (lastValue <= n) {
            perfectSquares.add(lastValue);
            lastValue = seed * seed;
            seed++;
        }

        return perfectSquares;
    }

    public int openLock(String[] deadends, String target) {
        var turns = 0;
        var queue = new ArrayList<String>();
        var deadEndsHash = Arrays.stream(deadends).collect(Collectors.toSet());

        queue.add("0000");

        while (!queue.isEmpty()) {
            var iterationSize = queue.size();

            for (int i = 0; i < iterationSize; i++) {
                String sequence = queue.remove(0);

                if (sequence.equalsIgnoreCase(target)) return turns;
                else if (deadEndsHash.contains(sequence)) continue;
                else {
                    queue.add(turnAt(sequence, 0, +1));
                    queue.add(turnAt(sequence, 0, -1));
                    queue.add(turnAt(sequence, 1, +1));
                    queue.add(turnAt(sequence, 1, -1));
                    queue.add(turnAt(sequence, 2, +1));
                    queue.add(turnAt(sequence, 2, -1));
                    queue.add(turnAt(sequence, 3, +1));
                    queue.add(turnAt(sequence, 3, -1));
                }

                deadEndsHash.add(sequence);
            }

            turns++;
        }

        return -1;
    }

    private String turnAt(String string, int position, int toAdd) {
        int value = ((string.charAt(position) - '0') + toAdd + 10) % 10;

        return string.substring(0, position) + ("" + value) + string.substring(position + 1);
    }

    public int numIslandsRecursive(char[][] grid) {
        var islands = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == '1') {
                    islands++;
                    sinkIsland(grid, row, column);
                }
            }
        }

        return islands;
    }

    private void sinkIsland(char[][] grid, int row, int column) {
        if (row < 0 || column < 0 || row >= grid.length || column >= grid[row].length || grid[row][column] == '0')
            return;

        grid[row][column] = '0';

        sinkIsland(grid, row + 1, column);
        sinkIsland(grid, row - 1, column);
        sinkIsland(grid, row, column + 1);
        sinkIsland(grid, row, column - 1);
    }

    private static final int[][] DIRECTIONS = {{-1, 0}, {+1, 0}, {0, -1}, {0, +1}};

    public void wallsAndGates(int[][] rooms) {
        var GATE = 0;
        var WALL = -1;
        var EMPTY = Integer.MAX_VALUE;
        var cells = new ArrayList<int[]>();

        for (var row = 0; row < rooms.length; row++) {
            for (var column = 0; column < rooms[row].length; column++) {
                if (rooms[row][column] == GATE) cells.add(new int[]{row, column});
            }
        }

        while (!cells.isEmpty()) {
            var cell = cells.remove(0);

            for (var direction : DIRECTIONS) {
                var row = cell[0] + direction[0];
                var column = cell[1] + direction[1];
                var distance = rooms[cell[0]][cell[1]] + 1;

                if (row >= rooms.length || row < 0 || column >= rooms[row].length || column < 0 || rooms[row][column] == WALL || rooms[row][column] != EMPTY)
                    continue;

                rooms[row][column] = distance;

                cells.add(new int[]{row, column});
            }
        }
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class MyStack {
    private final Queue<Integer> queue;
    private final Queue<Integer> backQueue;

    public MyStack() {
        queue = new ArrayDeque<>();
        backQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        while (!queue.isEmpty()) {
            backQueue.offer(queue.poll());
        }

        queue.add(x);

        while (!backQueue.isEmpty()) {
            queue.offer(backQueue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

class MyQueue {
    private final Stack<Integer> stack;
    private final Stack<Integer> backStack;

    public MyQueue() {
        stack = new Stack<>();
        backStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        while (!stack.isEmpty()) {
            backStack.push(stack.pop());
        }

        var result = backStack.pop();

        while (!backStack.isEmpty()) {
            stack.push(backStack.pop());
        }

        return result;
    }

    public int peek() {
        while (!stack.isEmpty()) {
            backStack.push(stack.pop());
        }

        var result = backStack.peek();

        while (!backStack.isEmpty()) {
            stack.push(backStack.pop());
        }

        return result;
    }

    public boolean empty() {
        return stack.isEmpty() && backStack.isEmpty();
    }
}

record Point(int row, int column) {

}

class KthLargest {
    private final Integer element;
    private final PriorityQueue<Integer> minHeap;

    public KthLargest(int k, int[] nums) {
        element = k;
        minHeap = new PriorityQueue<>(k);

        for (int num : nums) minHeap.offer(num);
    }

    public int add(int val) {
        minHeap.offer(val);

        while (minHeap.size() > element) minHeap.poll();

        return minHeap.peek();
    }
}

class MedianFinder {
    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) return maxHeap.peek();

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}