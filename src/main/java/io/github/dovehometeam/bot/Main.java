package io.github.dovehometeam.bot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import me.zhenxin.qqbot.core.BotCore;
import me.zhenxin.qqbot.entity.AccessInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<AccessInfoInfo> supplier = () -> {
            AccessInfoInfo info = new AccessInfoInfo();
            info.setBotAppId(0);
            info.setBotToken("");
            return info;
        };
        val gson = new GsonBuilder().setPrettyPrinting().create();
        val path = new File(System.getProperty("user.dir"), "config.json").toPath();
        AccessInfo info;
        if (!Files.exists(path)) {
            try(val bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                AccessInfoInfo infoInfo = supplier.get();
                info = infoInfo.convert();
                gson.toJson(infoInfo, bw);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            try(val br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

                info = gson.fromJson(br, AccessInfoInfo.class).convert();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        val bot = new BotCore(info);
        val api = bot.getApiManager();
        bot.registerAtMessageEvent();
    }
}