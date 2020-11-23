package com.t9d.tech.org.web;

/*
 * @author chenxi
 * @since 2020-11-10
 */

import com.t9d.tech.org.beans.ComerDetails;
import com.t9d.tech.org.beans.GlobalResponse;
import com.t9d.tech.org.beans.Monitor;
import com.t9d.tech.org.utils.DatabaseUtils;
import com.t9d.tech.org.utils.QueueUtils;
import com.t9d.tech.org.utils.RobotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/t9d")
public class QueueController {

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

    @Autowired
    private DatabaseUtils databaseUtils;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    private static AtomicLong add = new AtomicLong();

    @PostMapping(value = "/xxoo")
    public GlobalResponse<String> request(@RequestBody ComerDetails req)  {

        if (databaseUtils.isCorrectPW(req.getCommunity(),req.getPassWord())) {
            HashMap<Integer, List<QueueUtils.User>> res = databaseUtils.inQueue(req);

            if (res.containsKey(0)) {            if (res.containsKey(1)){

                return new GlobalResponse<>("申请成功");
            }

                return new GlobalResponse<>("队列已经满员");
            }

            return new GlobalResponse<>("权限问题，请联系管理员");
        }else {
            return new GlobalResponse<>("无权限进行相关操作");
        }

    }
}
