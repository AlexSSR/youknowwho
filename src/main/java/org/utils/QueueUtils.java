package org.utils;

import lombok.Data;

import java.util.PriorityQueue;

public class QueueUtils {

    @Data
    private static class User implements Comparable<User> {
        private String name;
        private Integer priority;

        @Override
        public int compareTo(User o) {
            return this.getPriority().compareTo(o.getPriority());
        }
    }

    private static PriorityQueue<User> queue = new PriorityQueue<User>();

    public static User getUser(String name, Integer priority) {
        User user = new User();
        user.setName(name);
        user.setPriority(priority);
        return user;
    }

    public static User outOffQueue() {
        return queue.poll();
    }

    public static boolean goToQueue(User u) {
        return queue.offer(u);
    }

    public static void main(String[] args) {

        User user_133 = getUser("133", 5);
        User user_t9d = getUser("t9d", 1);
        User user_yg = getUser("yg", 3);
        User user_t7d = getUser("t7d", 5);

        queue.offer(user_133);
        queue.offer(user_t9d);
        queue.offer(user_yg);
        queue.offer(user_t7d);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
