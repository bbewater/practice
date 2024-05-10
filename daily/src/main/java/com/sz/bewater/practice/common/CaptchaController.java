package com.sz.bewater.practice.common;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import cn.hutool.captcha.CaptchaUtil;

@RestController
public class CaptchaController {
    @Resource
    RedisTemplate<String, String> redisTemplate;

    /**
     * 方法一 ShearCaptcha
     * 图片格式
     * session存储
     * 接口需添加白名单放行
     *
     * @param request HttpServletRequest
     */
    @GetMapping("/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        shearCaptcha.write(response.getOutputStream());
        //获取验证码中的文字内容
        request.getSession().setAttribute("verifyCode", shearCaptcha.getCode());
    }

    /**
     * 方法二 LineCaptcha
     * 图片格式
     * session存储
     * 接口需添加白名单放行
     *
     * @param request HttpServletRequest
     */
    @GetMapping("/verifyTwo")
    public void verifyTwo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 40, 5, 4);
        lineCaptcha.setBackground(Color.PINK);
        //图形验证码写出，可以写出到文件，也可以写出到流
        ImageIO.write(lineCaptcha.getImage(), "JPEG", response.getOutputStream());
        //获取验证码中的文字内容
        request.getSession().setAttribute("verifyCode", lineCaptcha.getCode());
    }

    /**
     * 方法三 ShearCaptcha
     * 图片的base64编码字符串
     * session存储
     * 接口需添加白名单放行
     *
     * @param request HttpServletRequest
     * @return String
     */
    @GetMapping("/getVerify")
    public String getVerify(HttpServletRequest request) {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
        //获取验证码中的文字内容
        request.getSession().setAttribute("verifyCode", shearCaptcha.getCode());
        String base64String = "";
        try {
            base64String = "data:image/png;base64," + shearCaptcha.getImageBase64();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64String;
    }

    /**
     * 方法四 LineCaptcha
     * 图片的base64编码字符串
     * session存储
     * 接口需添加白名单放行
     *
     * @param request HttpServletRequest
     * @return String
     */
    @GetMapping("/getVerifyTwo")
    public String getVerifyTwo(HttpServletRequest request) {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 40, 5, 4);
        //获取验证码中的文字内容
        request.getSession().setAttribute("verifyCode", lineCaptcha.getCode());
        String base64String = "";
        try {
            //返回 base64
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(lineCaptcha.getImage(), "JPEG", bos);
            byte[] bytes = bos.toByteArray();
            Base64.Encoder encoder = Base64.getEncoder();
            base64String = "data:image/png;base64," + encoder.encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64String;
    }

    /**
     * 方法五 ShearCaptcha
     * 图片的base64编码字符串
     * redis存储
     * 接口需添加白名单放行
     *
     * @return String
     */
    @GetMapping("/getVerifyThree")
    public VerifyCodeVO getVerifyThree() {
        String captchaKey = UUID.randomUUID().toString();
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 0);
        // 存入redis并设置过期时间为30分钟
        redisTemplate.opsForValue().set("captcha:" + captchaKey, shearCaptcha.getCode(), 30L, TimeUnit.MINUTES);
        //captcha:d6e561a6-7929-4469-8154-008710932f61
        String base64String = "";
        try {
            base64String = "data:image/png;base64," + shearCaptcha.getImageBase64();
        } catch (Exception e) {
            e.printStackTrace();
        }
        VerifyCodeVO verifyCodeVO = new VerifyCodeVO();
        verifyCodeVO.setCaptchaKey(captchaKey);
        verifyCodeVO.setCaptchaImg(base64String);
        return verifyCodeVO;
    }

    /**
     * 方法六 LineCaptcha
     * 图片的base64编码字符串
     * redis存储
     * 接口需添加白名单放行
     *
     * @return String
     */
    @GetMapping("/getVerifyFour")
    public VerifyCodeVO getVerifyFour() {
        String captchaKey = UUID.randomUUID().toString();
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 40, 5, 4);
        // 存入redis并设置过期时间为30分钟
        redisTemplate.opsForValue().set("captcha:" + captchaKey, lineCaptcha.getCode(), 30L, TimeUnit.MINUTES);
        //captcha:d6e561a6-7929-4469-8154-008710932f61
        String base64String = "";
        try {
            //返回 base64
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(lineCaptcha.getImage(), "JPEG", bos);
            byte[] bytes = bos.toByteArray();
            Base64.Encoder encoder = Base64.getEncoder();
            base64String = "data:image/png;base64," + encoder.encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        VerifyCodeVO verifyCodeVO = new VerifyCodeVO();
        verifyCodeVO.setCaptchaKey(captchaKey);
        verifyCodeVO.setCaptchaImg(base64String);
        return verifyCodeVO;
    }

    /**
     * session存储
     *
     * @param captchaCode 验证码
     * @param request     HttpServletRequest
     * @return boolean
     */
    @GetMapping("/checkCaptcha")
    public boolean getCheckCaptcha(@RequestParam("captchaCode") String captchaCode, HttpServletRequest request) {
        try {
            // toLowerCase() 不区分大小写进行验证码校验
            String sessionCode = String.valueOf(request.getSession().getAttribute("verifyCode")).toLowerCase();
            System.out.println("session里的验证码：" + sessionCode);
            String receivedCode = captchaCode.toLowerCase();
            System.out.println("用户的验证码：" + receivedCode);
            return !"".equals(sessionCode) && !"".equals(receivedCode) && sessionCode.equals(receivedCode);
        } catch (Exception e) {
            return false;
        }
    }


    @Data
    static
    class VerifyCodeVO {
        private String captchaKey;
        private String captchaImg;
    }


}
