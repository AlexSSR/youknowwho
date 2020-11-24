package com.t9d.tech.org.service;

import com.t9d.tech.org.beans.Req;
import com.t9d.tech.org.utils.QueueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    QueueUtils queueUtils;

    public List<Req> getNextUsers() {

        List<QueueUtils.User> users = queueUtils.outOffQueue();

        return users.stream().map(x -> {
            Req req = new Req();
            req.setX(x.getX());
            req.setY(x.getY());
            req.setT(req.toInt(x.getTitel()));
            return req;
        }).collect(Collectors.toList());
    }
}
