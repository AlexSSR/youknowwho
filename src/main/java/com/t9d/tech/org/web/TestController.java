package com.t9d.tech.org.web;


import com.t9d.tech.org.beans.Monitor;
import com.t9d.tech.org.utils.QueueUtils;
import com.t9d.tech.org.utils.RobotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.concurrent.atomic.AtomicLong;

/*
 * @author chenxi
 * @since 2020-11-10
 */

@RestController
@RequestMapping(value = "/alchemy")
public class TestController {

    @Autowired
    private RobotUtils robotUtils;

    @Autowired
    private QueueUtils queueUtils;

    @Autowired
    private Monitor monitor;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "";
    private static final String QUERY_SQL = "";


    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    private static AtomicLong add = new AtomicLong();

    @RequestMapping(value = "/test")
    public String test() throws AWTException {

        QueueUtils.User user1 = queueUtils.getUser("133", 2, 2);
        queueUtils.goToQueue(user1);

        synchronized (add) {

            System.out.println(add.get());
            if (add.addAndGet(1) <= 10) {
                new Thread(() -> {

                    while (true) {
                        try {
                            if (queueUtils.hashNext()) {
                                //QueueUtils.User user = queueUtils.outOffQueue();



                                robotUtils.tapWithNoCer(monitor.getMapButton());
                                Thread.sleep(500);
                                robotUtils.tapWithNoCer(monitor.getMapButton());
                                robotUtils.tapWithNoCer(monitor.getSearchButton());

                                robotUtils.tap(monitor.getXInput());
                                robotUtils.printWord(123);
                                robotUtils.tap(monitor.getXInput());

                                Thread.sleep(500);

                                robotUtils.tap(monitor.getYInput());
                                robotUtils.printWord(321);
                                robotUtils.tap(monitor.getYInput());

                                robotUtils.tap(monitor.getSearchConformButton());
                            } else {
                                Thread.sleep(120000);
                            }

                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }
                }).start();
            }
        }


        return "ok";
    }

}
