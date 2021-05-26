/*package webhook

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.webhook
import java.io.File

object MyBotConfig {
    //const val API_TOKEN = ApiKey.miApiKey
    const val SERVER_HOSTNAME = "https://webhook.site/a9936e8c-e2d5-4c66-8e59-a88df655fefb"
}*/

class Webhook {/*
    /**
     * Funcion que nos permitira conectarnos de otra forma a la base de datos de Telegram
     */
    fun webhook() {
        val bot = bot {

            //token = MyBotConfig.API_TOKEN

            webhook {
                //url = "${MyBotConfig.SERVER_HOSTNAME}/${MyBotConfig.API_TOKEN}"
                /* This certificate argument is only needed when you want Telegram to trust your
                * self-signed certificates. If you have a CA trusted certificate you can omit it.
                * More info -> https://core.telegram.org/bots/webhooks */
                certificate = TelegramFile.ByFile(File(Utiles.certPath))
                maxConnections = 50
                allowedUpdates = listOf("message")
            }

            dispatch {
                command("hello") {
                    bot.sendMessage(ChatId.fromId(message.chat.id), "Hey bruh!")
                }
            }
        }
        bot.startWebhook()

        /*val env = applicationEngineEnvironment {
            module {
                routing {
                    post("/${MyBotConfig.API_TOKEN}") {
                        val response = call.receiveText()
                        bot.processUpdate(response)
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }

            sslConnector(
                keyStore = Utiles.keyStore,
                keyAlias = Utiles.keyAlias,
                keyStorePassword = { Utiles.keyStorePassword },
                privateKeyPassword = { Utiles.privateKeyPassword }
            ) {
                port = 443
                keyStorePath = Utiles.keyStoreFile.absoluteFile
                host = "0.0.0.0"
            }*/
        //embeddedServer(Netty, env).start(wait = true)
    }*/
}