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

    val setting by lazy {
        this.loadConfig("setting.yml")
    }

    val owner by setting.withDefault { 0L }
    val level by setting.withDefault { "min" }
    private val whiteList by setting.withDefault { mutableListOf<Long>() }
    val whiteSet = whiteList.toHashSet()
    val keyword by setting.withDefault { listOf("骂") }

    override fun onEnable() {
        super.onEnable()

        subscribeFriendMessages {
            content({ keyword.any { key -> it.contains(key, ignoreCase = true) } }) {
                reply(PlainText(Http.get(api + level)))
            }
        }

        subscribeGroupMessages {
            content({ (group.id in whiteSet) and keyword.any { key -> it.contains(key, ignoreCase = true) } }) {
                if (message.any { it is At }) {
                    reply(message[At] + PlainText(Http.get(api + level)))
                } else {
                    reply(sender.at() + PlainText(Http.get(api + level)))
                }
            }
        }


        if (owner != 0L) {
            subscribeGroupMessages {
                (sentBy(owner) and startsWith("口吐芬芳")) {
                    when {
                        it.endsWith("开") -> {
                            whiteSet.add(group.id)
                            reply("本群开")
                        }
                        it.endsWith("关") -> {
                            whiteSet.remove(group.id)
                            reply("本群关")
                        }
                    }
                }
            }
        }
    }
}