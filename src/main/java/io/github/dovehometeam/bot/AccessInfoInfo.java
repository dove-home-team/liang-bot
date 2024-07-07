package io.github.dovehometeam.bot;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.zhenxin.qqbot.entity.AccessInfo;

@Getter
@Setter
public class AccessInfoInfo {
    @SerializedName("bot-app-id")
    public Integer botAppId;
    @SerializedName("bot-token")
    public String botToken;
    @SerializedName("bot-sercret")
    public String botSecret;
    @SerializedName("use send box mode")
    public Boolean useSandBoxMode = false;

    public AccessInfo convert() {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setBotAppId(botAppId);
        accessInfo.setBotToken(botToken);
        accessInfo.setBotSecret(botSecret);
        if (useSandBoxMode)
            accessInfo.useSandBoxMode();
        return accessInfo;
    }
}
