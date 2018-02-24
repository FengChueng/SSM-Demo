package com.zyl.ssm.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import okhttp3.Response;

public class SMSUtils {
	public static final String SEND_SMS_BASE_URL = "http://120.77.252.107:9292/";//
    private static Logger log = Logger.getLogger(SMSUtils.class);

    /**
     * 放假通知
     * 
     * @param Vacation
     *            假期名称
     * @param timeStart
     *            开始时间
     * @param timeEnd
     *            结束时间
     * @param recNums
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean sendHolidayNoticeSMS(String Vacation, String timeStart, String timeEnd, String[] recNums)
            throws UnsupportedEncodingException {
        // http://120.77.252.107:9292/holiday_notice.json?Vacation=%E5%9B%BD%E5%BA%86&timeStart=10%E6%9C%881%E5%8F%B7&timeEnd=10%E6%9C%888%E5%8F%B7&recNums=17396202169
        if (StringUtils.isEmpty(Vacation)) {
            log.error("放假通知-----Vacation参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(timeStart)) {
            log.error("放假通知-----timeStart参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(timeEnd)) {
            log.error("放假通知-----timeEnd参数不能为空");
            return false;
        }
        String Prefixurl = SEND_SMS_BASE_URL + "holiday_notice.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "Vacation=" + URLEncoder.encode(Vacation, "utf-8") + "&timeStart="
                + URLEncoder.encode(timeStart, "utf-8") + "&timeEnd=" + URLEncoder.encode(timeEnd, "utf-8")
                + recNumsUrl;
        System.out.println(url);
        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            log.error("放假通知", e);
        }
        return isSuccess;

    }

    /**
     * 发送修改密码的验证码
     * 
     * @param verify
     *            验证码
     * @param recNums
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCodeSMS(String verify, String[] recNums) throws UnsupportedEncodingException {
        // http://39.108.105.166:9292/modify_pwd.json?verify=1234&recNums=17396202110
        if (StringUtils.isEmpty(verify)) {
            log.error("修改密码验证码-----sign(门店编号)参数不能为空");
            return false;

        }
        if (recNums == null || recNums.length == 0) {
            log.error("修改密码验证码-----recNums(电话号码)参数不能为空");
            return false;
        }
        String Prefixurl = SEND_SMS_BASE_URL + "modify_pwd.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "verify=" + URLEncoder.encode(verify, "utf-8") + recNumsUrl;
        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            log.error("修改密码验证码", e);
        }
        return isSuccess;
    }

    /**
     * 修改密码时发送验证码
     * 
     * @param param
     * @return
     */
    public static boolean sendVerifyCodeSMS(Map<String, String> param) {
        String account = "";
        String verifyCode = "";
        Set<String> keySet = param.keySet();
        for (String key : keySet) {// 只存在一条数据
            account = key;
            verifyCode = param.get(key);
            break;
        }
        String url = SEND_SMS_BASE_URL + "modify_pwd.json?verify=" + verifyCode + "&recNums=" + account;
        Response response = null;
        try {
            response = OkhttpUtils.execute(url);
            if (response != null && response.isSuccessful()) {
            	return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return false;
    }

    /**
     * 发送黑盒通知客户
     * 
     * @param sign
     *            编号，店铺
     * @param time
     *            时间（只能填时分，eg:14:00）
     * @param modular
     *            故障类型
     * @param recNums
     *            目的地手机号码列表
     * @return 成功或失败
     * @throws IOException
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendBoxSMS(String sign, String time, String modular, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/box_notification.json?sign=%E5%A4%A7%E5%8D%8EL%E5%BA%97&time=14%3A00&modular=%E7%81%AF%E7%AE%B1&recNums=18200366709

        if (StringUtils.isEmpty(sign)) {
            log.error("发送黑盒通知客户-----sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("发送黑盒通知客户-----时间不能为空");
            // throw new ValidationException("时间不能为空");
            return false;
        }
        if (StringUtils.isEmpty(modular)) {
            log.error("发送黑盒通知客户-----故障类型不能为空");
            // throw new ValidationException("故障类型不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("发送黑盒通知客户-----接受者手机号不能为null");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }
        String Prefixurl = SEND_SMS_BASE_URL + "box_notification.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + "&time="
                + URLEncoder.encode(time, "utf-8") + "&modular=" + URLEncoder.encode(modular, "utf-8") + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("发送黑盒通知客户" + e);
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 发送客户下单短信
     * 
     * @param sign
     *            编号，店铺
     * @param time
     *            时间（只能填时分，eg:14:00）
     * @param type
     *            订单类型(只有保修和维修)
     * @param recNums
     *            目的地手机号码列表
     * @return 完成或失败
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendOrderSMS(String sign, String time, String type, String[] recNums)
            throws UnsupportedEncodingException {
        // http://39.108.105.166:9292/client_order.json?sign=%E4%B8%AD%E5%8D%8E%E5%BA%97&time=14%3A00&type=%E7%BB%B4%E4%BF%AE&recNums=18200366709

        if (StringUtils.isEmpty(sign)) {
            log.error("发送客户下单短信时-----sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("发送客户下单短信时-----时间不能为空");
            // throw new ValidationException("时间不能为空");
            return false;
        }
        if (StringUtils.isEmpty(type)) {
            log.error("发送客户下单短信时-----订单类型不能为空");
            // throw new ValidationException("订单类型不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("发送客户下单短信时-----接受者手机号不能为null");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "client_order.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + "&time="
                + URLEncoder.encode(time, "utf-8") + "&type=" + URLEncoder.encode(type, "utf-8") + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("发送客户下单短信" + e);
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 开始服务模板
     * 
     * @param sign
     *            编号，店铺
     * @param recNums
     *            目的地手机号码列表
     * @return 完成或失败
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendStartServiceSMS(String sign, String[] recNums) throws UnsupportedEncodingException {
        // http://39.108.105.166:9292/start_service.json?sign=%E6%B5%8B%E8%AF%95%E9%97%A8%E5%BA%97&recNums=18200366709&recNums=18780209511
        if (StringUtils.isEmpty(sign)) {
            log.error("维修完成模板---------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("维修完成模板---------sign(门店编号)参数不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "start_service.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + recNumsUrl;
        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("开始服务模板" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * 维修完成模板
     * 
     * @param sign
     *            编号，店铺
     * @param recNums
     *            目的地手机号码列表
     * @return 完成或失败
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCompleteSMS(String sign, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/complete_repair.json?sign=%E6%B5%8B%E8%AF%95%E9%97%A8%E5%BA%97&recNums=18200366709&recNums=18780209511
        if (StringUtils.isEmpty(sign)) {
            log.error("维修完成模板---------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("维修完成模板---------sign(门店编号)参数不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "complete_repair.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + recNumsUrl;
        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("维修完成模板" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * 清洗完成模板
     * 
     * @param sign
     *            编号，店铺
     * @param recNums
     *            目的地手机号码列表
     * @return 完成或失败
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCompleteCleanSMS(String sign, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/complete_clean.json?sign=%E6%B5%8B%E8%AF%95%E9%97%A8%E5%BA%97&recNums=18200366709&recNums=18780209511
        if (StringUtils.isEmpty(sign)) {
            log.error("维修完成模板---------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("维修完成模板---------sign(门店编号)参数不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "complete_clean.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + recNumsUrl;
        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("清洗完成模板" + e);
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 下单通知服务商
     */
    public static boolean sendOrderNotifiProviderSMS(String sign, String time, String type, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/order_notification_provider.json?sign=%E5%A4%A7%E5%8D%8E&time=14%3A00&type=%E7%BB%B4%E4%BF%AE&recNums=18200366709
        if (StringUtils.isEmpty(sign)) {
            log.error("下单通知服务商------------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("下单通知服务商------------time(时间)参数不能为空");
            // throw new ValidationException("time(时间)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(type)) {
            log.error("下单通知服务商------------type(订单类型)参数不能为空");
            // throw new ValidationException("type(订单类型)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("下单通知服务商------------接受者手机号不能为null");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "order_notification_provider.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + "&time=" + time + "&type=" + type
                + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("下单通知服务商" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * 黑盒通知服务商
     */
    public static boolean sendBoxNotifiProviderSMS(String sign, String time, String modular, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/box_notification_provider.json?sign=%E5%A4%A7%E5%8D%8E&time=14%3A00&modular=%E7%81%AF%E7%AE%B1&recNums=18200366709
        if (StringUtils.isEmpty(sign)) {
            log.error("黑盒通知服务商------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("黑盒通知服务商------time参数不能为空");
            // throw new ValidationException("time(时间)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(modular)) {
            log.error("黑盒通知服务商------modular参数不能为空");
            // throw new ValidationException("modular(故障类型)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("黑盒通知服务商------接受者手机号不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "box_notification_provider.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + "&time=" + time + "&modular=" + modular
                + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("发送黑盒通知客户" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * 企业客户周期清洁订单提前通知
     * 
     * @param sign
     * @param time
     * @param modular
     * @param recNums
     * @return
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean cycleOrderNotifiCustomer(String sign, String time, String frequency, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/cycle_order_notification_customer.json?sign=%E5%A4%A7%E5%8D%8E&time=12%3A00&frequency=%E7%AC%AC%E4%B8%80%E6%AC%A1&recNums=18200366709
        if (StringUtils.isEmpty(sign)) {
            log.error("企业客户周期清洁订单提前通知------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("企业客户周期清洁订单提前通知------time参数不能为空");
            // throw new ValidationException("time(时间)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(frequency)) {
            log.error("企业客户周期清洁订单提前通知------frequency参数不能为空");
            // throw new ValidationException("modular(故障类型)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("黑盒通知服务商------接受者手机号不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "cycle_order_notification_customer.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + "&time=" + time + "&frequency="
                + frequency + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("发送黑盒通知客户" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * 服务商派单通知
     * 
     * @param sign
     * @param time
     * @param frequency
     * @param recNums
     * @return
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean providerSingleNotifiStaff(String provider, String time, String type, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/single_order_notification_staff.json?provider=%E5%A4%A7%E5%8D%8E&time=14%3A00&type=%E7%BB%B4%E4%BF%AE&recNums=18200366709
        if (StringUtils.isEmpty(provider)) {
            log.error("服务商派单通知------provider参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("服务商派单通知------time参数不能为空");
            // throw new ValidationException("time(时间)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(type)) {
            log.error("服务商派单通知------type参数不能为空");
            // throw new ValidationException("modular(故障类型)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("服务商派单通知------接受者手机号不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "single_order_notification_staff.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "provider=" + URLEncoder.encode(provider, "utf-8") + "&time=" + time + "&type=" + type
                + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("发送黑盒通知客户" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * 服务商周期订单提前通知
     * 
     * @param sign
     * @param time
     * @param frequency
     * @param recNums
     * @return
     * @throws ValidationException
     * @throws UnsupportedEncodingException
     */
    public static boolean cycleOrderNotifiprovider(String sign, String time, String frequency, String[] recNums)
            throws ValidationException, UnsupportedEncodingException {
        // http://39.108.105.166:9292/cycle_order_notification_provider.json?sign=%E7%89%9B&time=14%3A00&frequency=2&recNums=18200366709
        if (StringUtils.isEmpty(sign)) {
            log.error("企业客户周期清洁订单提前通知------sign(门店编号)参数不能为空");
            // throw new ValidationException("sign(门店编号)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(time)) {
            log.error("企业客户周期清洁订单提前通知------time参数不能为空");
            // throw new ValidationException("time(时间)参数不能为空");
            return false;
        }
        if (StringUtils.isEmpty(frequency)) {
            log.error("企业客户周期清洁订单提前通知------frequency参数不能为空");
            // throw new ValidationException("modular(故障类型)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("黑盒通知服务商------接受者手机号不能为空");
            // throw new ValidationException("接受者手机号不能为null");
            return false;
        }

        String Prefixurl = SEND_SMS_BASE_URL + "cycle_order_notification_provider.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "sign=" + URLEncoder.encode(sign, "utf-8") + "&time=" + time + "&frequency="
                + frequency + recNumsUrl;

        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            isSuccess = false;
            log.error("发送黑盒通知客户" + e);
            e.printStackTrace();
        }
        return isSuccess;

    }

    /**
     * @throws UnsupportedEncodingException
     * 
     * @Title: universalVerificationCode @Description: TODO
     *         通用验证码短信模版 @param @param verify 验证码 @param @param recNums
     *         手机号码 @param @return @return boolean @throws
     */
    public static boolean universalVerificationCode(String verify, String[] recNums)
            throws UnsupportedEncodingException {
        // http://120.77.252.107:9292/universal_verification_code.json?verify=666666&recNums=17396202169
        if (StringUtils.isEmpty(verify)) {
            log.error("通用验证码短信模版-----verify(验证码)参数不能为空");
            return false;
        }
        if (recNums == null || recNums.length == 0) {
            log.error("通用验证码短信模版-----recNums(电话号码)参数不能为空");
            return false;
        }
        String Prefixurl = SEND_SMS_BASE_URL + "universal_verification_code.json?";
        StringBuffer phones = new StringBuffer();
        for (String phone : recNums) {
            phones.append("&recNums=" + phone);
        }
        String recNumsUrl = phones.toString().trim();
        String url = Prefixurl + "verify=" + URLEncoder.encode(verify, "utf-8") + recNumsUrl;
        boolean isSuccess = false;
        try {
            Response response = OkhttpUtils.execute(url);
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            log.error("修改密码验证码", e);
        }
        return isSuccess;
    }

    

}
