package com.lxinet.jeesns.core.utils;

import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

public class ErrorUtil {
    private static Map<Integer, String> errors = new HashMap();

    static {
        errors.put(Integer.valueOf(-999), "您的系统未授权，无法使用此功能，请联系系统开发商进行授权，网址：http://www.jeesns.cn/，QQ：582866070");
        errors.put(Integer.valueOf(-1000), "系统异常");
        errors.put(Integer.valueOf(-1001), "没有权限");
        errors.put(Integer.valueOf(-1002), "群组不存在");
        errors.put(Integer.valueOf(-1003), "先关注群组才能发帖");
        errors.put(Integer.valueOf(-1004), "帖子不存在");
        errors.put(Integer.valueOf(-1005), "会员不存在");
        errors.put(Integer.valueOf(-1006), "群组已关闭发帖功能");
        errors.put(Integer.valueOf(-1007), "微博不存在");
        errors.put(Integer.valueOf(-1008), "请先登录");
        errors.put(Integer.valueOf(-1009), "文章不存在");
        errors.put(Integer.valueOf(-1010), "相册不存在");
        errors.put(Integer.valueOf(-1011), "图片不存在");
        errors.put(Integer.valueOf(-1012), "无权查看此相册");
        errors.put(Integer.valueOf(-1013), "帖子分类不存在");
    }

    public static String error(Model model, Integer errorcode, String ftlPath) {
        model.addAttribute("msg", errors.get(errorcode));
        return ftlPath;
    }

    public static String frontError(Model model, Integer errorcode) {
        model.addAttribute("msg", errors.get(errorcode));
        return "/common/error";
    }

    public static String manageError(Model model, Integer errorcode) {
        model.addAttribute("msg", errors.get(errorcode));
        return "/manage/common/error";
    }

    public static String indexNoAuth(Model model) {
        model.addAttribute("msg", errors.get(Integer.valueOf(-999)));
        return "/common/error";
    }

    public static String manageNoAuth(Model model) {
        model.addAttribute("msg", errors.get(Integer.valueOf(-999)));
        return "/manage/common/error";
    }
}
