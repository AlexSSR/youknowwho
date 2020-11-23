package com.t9d.tech.org.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.PriorityQueue;

@Component
public class QueueUtils {


    public LinkedList<User> search(String key) {

        LinkedList<User> list = new LinkedList<>();

        queue.forEach(ele -> {
            if (ele.getName().equals(key)) {
                list.add(ele);
            }
        });
        return list;
    }

    @Data
    public static class User implements Comparable<User> {
        private String name;
        private Integer priority;
        private int mins;
        private int x;
        private int y;

        @Override
        public int compareTo(User o) {
            return this.getPriority().compareTo(o.getPriority());
        }
    }

    private static PriorityQueue<User> queue = new PriorityQueue<User>();

    public static boolean hashNext() {
        return queue.size() > 0;
    }

    public static User getUser(String name, Integer priority) {
        User user = new User();
        user.setName(name);
        user.setPriority(priority);

        return user;
    }

    public  User getUser(String name, Integer priority, int mins) {
        User user = new User();
        user.setName(name);
        user.setPriority(priority);
        user.setMins(mins);
        return user;
    }

    public User getUser(String name, Integer priority, int mins, int x, int y) {
        User user = new User();
        user.setName(name);
        user.setPriority(priority);
        user.setMins(mins);
        user.setX(x);
        user.setY(y);
        return user;
    }

    public  User outOffQueue() {
        return queue.poll();
    }

    public  boolean goToQueue(User u) {
        return queue.offer(u);
    }

    public  void main(String[] args) {

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
