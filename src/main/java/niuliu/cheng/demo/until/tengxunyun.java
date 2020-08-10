package niuliu.cheng.demo.until;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

public class tengxunyun {


    // 短信应用 SDK AppID
// 1400开头
    private int appid = 1400281944;

    // 短信应用 SDK AppKey
    private String appkey = "xxxx";

    // 短信模板ID,需要在短信应用中申请
// NOTE: 这里的模板ID'7839'只是一个示例,真实的模板ID需要在短信控制台中申请
// templateId7839对应的内容是:"您的验证码是:{1}"
    private int templateId = 471533;
    // NOTE: 签名参数使用的是:签名内容,而不是签名ID。这里的签名"腾讯云"只是一个示例,真实的签名需要在短信控制台申请。
    String smsSign = "toaround";

    public void sd(String phoneNumbers, String yzsw, String zt) {
        //您正进行{1}操作，验证码为{2}，有效时间为3分钟，如非本人操作，请忽略本短信。
        if (zt.equals("登录"))
            this.templateId = 471533;
        else if (zt.equals("注册"))
            this.templateId = 471534;
        else if (zt.equals("修改密码"))
            this.templateId = 471533;

        try {
            // 数组具体的元素个数和模板中变量个数必须一致,例如示例中templateId:5678对应一个变量,参数数组元素个数也必须是一个
            String[] params = {yzsw};
            SmsSingleSender ssender = new SmsSingleSender(this.appid, this.appkey);
            // 签名参数未提供或者为空时,会使用默认签名发送短信
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers,
                    this.templateId, params, this.smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
