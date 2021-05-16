import Apikey.miApyKey
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold

fun main() {

    //Instanciamos un objeto bot que a su vez instanciaremos con un builder
    val bot = bot {

        //Introducimos el token de nuestro bot
        token = miApyKey

        //El dispatch nos permitira enviar información a la api de Telegram que manejara nuestro bot
        dispatch {

            //Creamos un comando que devolvera un mensaje que nosotros indiquemos
            command("start") {

                val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Bot iniciado")

                result.fold({

                }, {
                    // do something with the error
                })
            }
        }
    }
    bot.startPolling()
}