package com.t9d.tech.org.utils;

import com.t9d.tech.org.beans.ComUser;
import com.t9d.tech.org.beans.ComerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DatabaseUtils {

    private static Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);

    @Autowired
    private QueueUtils queueUtils;

    private static HashMap<String, AtomicInteger> metaBase = new HashMap<>();

    private static HashMap<String, ComUser> comUserDatabase = new HashMap<String, ComUser>(){{
        put("t9d",new ComUser("t9d","246810",2,3));
    }};

    static {
        comUserDatabase.put("yg",new ComUser("yg","1357911",3,6));
        metaBase.put("t9d",new AtomicInteger());
    }

    public static void addComUser(String name,String passWord,int proority,int queueSize){
        ComUser comUser = new ComUser();
        comUser.setName(name);
        comUser.setPassWord(passWord);
        comUser.setProority(proority);
        comUser.setQueueSize(queueSize);
        comUserDatabase.put(name,comUser);
    }

    public boolean isCorrectPW(String name,String passWord){
        try {
            if (comUserDatabase.get(name).getPassWord().equals(passWord)) {
                return true;
            }
        }catch (NullPointerException e){

            logger.error(e.getMessage());
        }
        return false;
    }

    public HashMap<Integer, List<QueueUtils.User>> inQueue(ComerDetails details){
        //2 无资格操作
        //1 队列已经满了
        //0 加入队列成功
        HashMap<Integer, List<QueueUtils.User>> res = new HashMap<>();
        String key = details.getCommunity();
        LinkedList<QueueUtils.User> usersList = new LinkedList<>();

        try {

            if (metaBase.containsKey(key)) {

                ComUser comUser = comUserDatabase.get(key);

                synchronized (metaBase){
                    AtomicInteger atomicInteger = metaBase.get(key);
                    if (atomicInteger.get() < comUser.getQueueSize()) {

                        QueueUtils.User user = queueUtils.getUser(key,
                                comUser.getProority(),
                                1,
                                details.getX(),
                                details.getY());

                        boolean b = queueUtils.goToQueue(user);

                        if (b) {
                            atomicInteger.getAndAdd(1);
                            res.put(0,usersList);
                            return res;
                        }

                         usersList = queueUtils.search(key);

                        res.put(1,usersList);
                        return res;
                    }else {
                        res.put(1,usersList);
                        return res;
                    }
                }
            }
        }
        catch (NullPointerException e){
            logger.error(e.getMessage());
        }
        res.put(2,usersList);
        return  res;
    }
}
