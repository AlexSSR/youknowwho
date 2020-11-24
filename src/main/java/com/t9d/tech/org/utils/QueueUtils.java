package com.t9d.tech.org.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class QueueUtils {

    private static DateTimeFormatter fmTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LinkedList<User> search(String key) {

        LinkedList<User> list = new LinkedList<>();

        queue.forEach(ele -> {
            if (ele.getName().equals(key)) {
                list.add(ele);
            }
        });
        return list;
    }

    public List<User> getAllUsers() {

        LocalDateTime now = LocalDateTime.now();

        AtomicInteger i = new AtomicInteger(0);
        return queue.stream().map(x -> {
            int xx = i.getAndIncrement();
            int i1 = 2 * xx;
            x.setYTime(now.plusMinutes(i1).format(fmTime));
            return x;
       }).collect(Collectors.toList());
    }

    @Data
    public static class User implements Comparable<User> {
        private String name;
        private Integer priority;
        private int mins;
        private int x;
        private int y;
        private String titel;
        private String yTime;

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

    public User getUser(String name, Integer priority, int mins) {
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

    public List<User> outOffQueue() {

        HashSet<String> set = new HashSet<>();
        LinkedList<User> list = new LinkedList<>();

        while (hashNext()) {
            User user = queue.poll();
            if (set.contains(user.getTitel())) {
                break;
            } else {
                list.add(user);
                set.add(user.getTitel());
            }
        }

        return list;
    }

    public boolean goToQueue(User u) {
        return queue.offer(u);
    }

    public void main(String[] args) {

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
