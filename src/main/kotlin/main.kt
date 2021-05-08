import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold

fun main() {
    val bot = bot {

        token = Apikey.miApyKey

        dispatch {

            text {
                print("Chat ID: ")
                println(message.chat.id)
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = text)
            }
            command("para1") {

                val result = bot.sendMessage(chatId = ChatId.fromId(update.message!!.chat.id), text = "Para world!")

                result.fold(
                    {
                        // do something here with the response
                    },
                    {
                        // do something with the error
                    }
                )
            }
            bot.startPolling()
        }
    }
}