package com.github.ryoii

import io.ktor.client.request.get
import net.mamoe.mirai.console.plugins.PluginBase
import net.mamoe.mirai.console.plugins.withDefault
import net.mamoe.mirai.event.subscribeFriendMessages
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.Http

class AbusePluginBase : PluginBase() {

    companion object {
        const val api = "https://nmsl.shadiao.app/api.php?lang=zh_cn&level="
    }

    val setting by lazy{
        this.loadConfig("setting.yml")
    }

    val level by setting.withDefault { "min" }

    override fun onEnable() {
        super.onEnable()

        subscribeFriendMessages {
            contains("骂") {
                reply(PlainText(Http.get(api + level)))
            }
        }

        subscribeGroupMessages {
            contains("骂") {
                if (message.any { it is At }) {
                    reply(message[At] + PlainText(Http.get(api + level)))
                } else {
                    reply(sender.at() + PlainText(Http.get(api + level)))
                }
            }
        }
    }
}