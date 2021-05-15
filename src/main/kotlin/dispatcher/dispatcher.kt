package dispatcher

import Apikey.miApyKey
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold

fun main() {

    val bot = bot {

        token = miApyKey

        dispatch {

            command("start") {

                val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Hi there!")

                result.fold({

                }, {
                    // do something with the error
                })
            }
        }
    }
    bot.startPolling()

}

