package com.t9d.tech.org.service;

import com.t9d.tech.org.utils.QueueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class getNextUser {

    @Autowired
    QueueUtils queueUtils;

    public QueueUtils.User getNextUser(){



       return queueUtils.outOffQueue();
    }
}
