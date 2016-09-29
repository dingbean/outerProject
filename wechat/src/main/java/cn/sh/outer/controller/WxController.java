package cn.sh.outer.controller;

import cn.sh.outer.util.IdGenerate;
import cn.sh.outer.util.SignUtil;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Created by Administrator on 2016/9/20.
 */
@Controller
@RequestMapping("/wx")
public class WxController {

    private static Logger log = LoggerFactory.getLogger(WxController.class);

    @RequestMapping(value = "/controller")
    @ResponseBody
    public String testController(HttpServletRequest request){
        System.out.println("idNum=" + IdGenerate.getIdNum());
        return "200";
    }

    @RequestMapping(value = "/wxComein", method = RequestMethod.GET)
    @ResponseBody
    public void wxComeinGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String echostr = request.getParameter("echostr");
            response.setContentType("text/xml");
            response.getWriter().print(echostr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/wxComein", method = RequestMethod.POST)
    @ResponseBody
    public String wxComeinPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        String token = request.getParameter("token");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(token,signature, timestamp, nonce)) {
            // 验证消息的合法性
            BufferedReader sis = request.getReader();

            String requestString = streamToString(sis);
            log.info("用户请求===:" + requestString);
        }
        return null;
    }

    public static String streamToString(Reader reader) throws IOException {
        char[] buf = new char[1024];
        int len = 0;
        StringBuffer buffer = new StringBuffer();
        while ((len = reader.read(buf)) != -1) {
            buffer.append(buf, 0, len);
        }
        return buffer.toString();
    }
}
