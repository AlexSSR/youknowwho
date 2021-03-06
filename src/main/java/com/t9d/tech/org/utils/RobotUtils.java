package com.t9d.tech.org.utils;

import com.t9d.tech.org.beans.Monitor;
import com.t9d.tech.org.web.global.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.event.InputEvent.BUTTON1_MASK;

/**
 * chenxi
 */
@Component
public class RobotUtils {

    @Autowired
    private Monitor monitor;

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    public static void main(String[] args) throws Exception {

        //定位四个点作为图像的基准
        //判断坐标元件的存在

        Thread.sleep(1000);

        Robot robot = new Robot();

        RobotUtils robotUtils = new RobotUtils();
        for (int x : getPoint()) {
            System.out.println(x);
        }


//        int[] Point = getPoint();
//        FileOutputStream File = new FileOutputStream("pointMessage.txt", true);
//        String pointMessage = "{" + Point[0] + "," + Point[1] + "," + Point[2] + "," + Point[3]        + "," + Point[4] + "}\n";
//        byte b[] = pointMessage.getBytes();
//        File.write(b);
//        File.close();

    }

    public void printWord(int word) throws AWTException {

        Robot robot = new Robot();


        List<Integer> key = KeyBoardUtils.getKey(word);

        key.forEach(x -> {
            int pressTime = (int) (Math.random() * 100 + 200);
            robot.keyPress(x);
            robot.delay(pressTime);
            robot.keyRelease(x);
            robot.delay((int) (Math.random() * 100 + 200));
        });
    }

    public void tapWithNoCer(com.t9d.tech.org.beans.Point point) throws AWTException {
        // 获取判断点的信息
        int decisionX = point.getX();
        int decisionY = point.getY();

        // 获取真实点的颜色
        Robot robot = new Robot();

        // 计算鼠标位置并且移动到该位置
        int mouseMoveX = (int) (Math.random() * 35 - 15) + decisionX;
        int mouseMoveY = (int) (Math.random() * 35 - 15) + decisionY;
        // 修复JDK8的移动不正确的BUG
        for (int i = 0; i < 6; i++) {
            robot.mouseMove(mouseMoveX, mouseMoveY);
        }

        // 模拟计算鼠标按下的间隔并且按下鼠标
        int moveTime = (int) (Math.random() * 100 + 200);
        int mousePressTime = (int) (Math.random() * 100 + 50);
        robot.delay(moveTime);
        robot.mousePress(BUTTON1_MASK);
        robot.delay(mousePressTime);
        robot.mouseRelease(BUTTON1_MASK);


    }

    public void tap(com.t9d.tech.org.beans.Point point) throws AWTException {
        // 获取判断点的信息
        int decisionX = point.getX();
        int decisionY = point.getY();

        // 获取真实点的颜色
        Robot robot = new Robot();

        // 修复JDK8的移动不正确的BUG
        for (int i = 0; i < 6; i++) {
            robot.mouseMove(decisionX, decisionY);
        }

        // 模拟计算鼠标按下的间隔并且按下鼠标
        int moveTime = (int) (Math.random() * 100 + 200);
        int mousePressTime = (int) (Math.random() * 100 + 200);
        robot.delay(moveTime);
        robot.mousePress(BUTTON1_MASK);
        robot.delay(mousePressTime);
        robot.mouseRelease(BUTTON1_MASK);

    }

    public boolean tapWithCer(com.t9d.tech.org.beans.Point point) throws AWTException {
        // 获取判断点的信息
        int decisionX = point.getX();
        int decisionY = point.getY();
        int decisionR = point.getR();
        int decisionG = point.getG();
        int decisionB = point.getB();
        // 获取真实点的颜色
        Robot robot = new Robot();
        Color decisionRGB = robot.getPixelColor(decisionX, decisionY);
        int mouseR = decisionRGB.getRed();
        int mouseG = decisionRGB.getGreen();
        int mouseB = decisionRGB.getBlue();
        // 如果真实点与判断点颜色一致,则执行以下操作
        if (Math.abs(mouseR - decisionR) < 15 && Math.abs(mouseG - decisionG) < 15 && Math.abs(mouseB - decisionB) < 15) {

            // 计算鼠标位置并且移动到该位置
            int mouseMoveX = (int) (Math.random() * 35 - 15) + decisionX;
            int mouseMoveY = (int) (Math.random() * 35 - 15) + decisionY;
            // 修复JDK8的移动不正确的BUG
            for (int i = 0; i < 6; i++) {
                robot.mouseMove(mouseMoveX, mouseMoveY);
            }

            // 模拟计算鼠标按下的间隔并且按下鼠标
            int moveTime = (int) (Math.random() * 100 + 200);
            int mousePressTime = (int) (Math.random() * 100 + 200);
            robot.delay(moveTime);
            robot.mousePress(BUTTON1_MASK);
            robot.delay(mousePressTime);
            robot.mouseRelease(BUTTON1_MASK);

            return true;
        }

        return false;
    }

    public void go2Zero() throws AWTException {
        Robot robot = new Robot();

        int moveTime = (int) (Math.random() * 500 + 200);

        int x = monitor.getLeftUpPoint().getX();
        int y = monitor.getLeftUpPoint().getY();
        robot.delay(moveTime);

        logger.info("去往起点： x->" + x + " y->" + y);
        robot.mouseMove(x, y);
    }

    public void go2Mid() throws AWTException {
        Robot robot = new Robot();

        int moveTime = (int) (Math.random() * 500 + 200);

        int x = (int) (monitor.getLeftUpPoint().getX() + monitor.getWidth() / 2);
        int y = (int) (monitor.getLeftUpPoint().getY() + monitor.getHight() / 2);
        robot.delay(moveTime);

        logger.info("去往中点： x->" + x + " y->" + y);
        robot.mouseMove(x, y);
    }

    public static void moveTo(int x, int y) throws AWTException {

        Robot robot = new Robot();

        int moveTime = (int) (Math.random() * 500 + 200);

        robot.delay(moveTime);
        robot.mouseMove(x, y);
    }

    /**
     * 本方法会根据设定的判断点与真实点进行对比,如果颜色一致则移动鼠标到该点进行单击操作
     *
     * @param Point - 判断点的相关信息
     * @throws Exception - 如果平台配置不允许使用Robot类则抛出异常
     */
    public static void MouseResponse(int[] Point) throws Exception {

        // 获取判断点的信息
        int decisionX = Point[0];
        int decisionY = Point[1];
        int decisionR = Point[2];
        int decisionG = Point[3];
        int decisionB = Point[4];
        // 获取真实点的颜色
        Robot robot = new Robot();
        Color decisionRGB = robot.getPixelColor(decisionX, decisionY);
        int mouseR = decisionRGB.getRed();
        int mouseG = decisionRGB.getGreen();
        int mouseB = decisionRGB.getBlue();
        // 如果真实点与判断点颜色一致,则执行以下操作
        if (Math.abs(mouseR - decisionR) < 5 && Math.abs(mouseG - decisionG) < 5 && Math.abs(mouseB - decisionB) < 5) {

            // 计算鼠标位置并且移动到该位置
            int mouseMoveX = (int) (Math.random() * 35 - 15) + decisionX;
            int mouseMoveY = (int) (Math.random() * 35 - 15) + decisionY;
            // 修复JDK8的移动不正确的BUG
            for (int i = 0; i < 6; i++) {
                robot.mouseMove(mouseMoveX, mouseMoveY);
            }

            // 模拟计算鼠标按下的间隔并且按下鼠标
            int moveTime = (int) (Math.random() * 500 + 200);
            int mousePressTime = (int) (Math.random() * 500 + 200);
            robot.delay(moveTime);
            robot.mousePress(BUTTON1_MASK);
            robot.delay(mousePressTime);
            robot.mouseRelease(BUTTON1_MASK);

        }

    }


    /**
     * 本方法会在三秒后获取当前鼠标的坐标相关信息并且设置为判断点.
     *
     * @throws Exception - 如果平台配置不允许使用Robot类则抛出异常
     */
    public static int[] getPoint() throws Exception {
        Robot robot = new Robot();
        robot.delay(3000);
        // 获取鼠标坐标
        PointerInfo pinfo = MouseInfo.getPointerInfo();
        Point p = pinfo.getLocation();
        int mouseX = (int) p.getX();
        int mouseY = (int) p.getY();
        // 获取鼠标坐标颜色
        Color mouseRGB = robot.getPixelColor(mouseX, mouseY);
        int R = mouseRGB.getRed();
        int G = mouseRGB.getGreen();
        int B = mouseRGB.getBlue();
        // 返回鼠标的坐标值
        int[] array = new int[]{mouseX, mouseY, R, G, B};
        return array;
    }

    /**
     * 本方法会将文件信息提取并且返回此文件信息列表
     *
     * @param FileURL - 指定的文件URL
     * @return - 返回的文件信息列表
     * @throws Exception - 如果发生 I/O 错误则抛出异常
     */
    public static ArrayList<int[]> FileToArrayList(String FileURL) throws Exception {

        String string = null;
        int[] Point = null;
        ArrayList<int[]> PointList = new ArrayList<int[]>();
        //本人采用正则表达式提取数据,
        Pattern p = Pattern.compile("\\{([^,]+),([^,]+),([^,]+),([^,]+),([^\\}]+)\\}");
        BufferedReader File = new BufferedReader(new InputStreamReader(new FileInputStream(FileURL)));

        while ((string = File.readLine()) != null) {
            //虽然有其他存数据办法，比如数据库，但是不可能让用户专门下载个数据库，这是一个正常的逻辑
            Matcher rule = p.matcher(string);

            while (rule.find()) {
                // 将每行的数据提取并且赋值,最后添加进容器中
                int X = Integer.parseInt(rule.group(1));
                int Y = Integer.parseInt(rule.group(2));
                int R = Integer.parseInt(rule.group(3));
                int G = Integer.parseInt(rule.group(4));
                int B = Integer.parseInt(rule.group(5));
                Point = new int[]{X, Y, R, G, B};
                PointList.add(Point);
            }
        }
        File.close();
        return PointList;
    }

    static void start(ArrayList<int[]> PointList) throws Exception {

        for (int i = 0; i < PointList.size(); i++) {
            // 如果判断点与真实点颜色一致就移动到该点并且点击该点
            MouseResponse(PointList.get(i));
        }
    }

    public void run() {
        try {
            // 读取判断点信息
            ArrayList<int[]> PointList = FileToArrayList("pointMessage.txt");
            // 开始刷本
            while (true) {
                // 遍历每个设置判断点
                for (int i = 0; i < PointList.size(); i++) {
                    // 如果判断点与真实点颜色一致就移动到该点并且点击该点
                    MouseResponse(PointList.get(i));

                }
                // 每次遍历完让程序休息一下，别太累了
                Thread.sleep(500);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
