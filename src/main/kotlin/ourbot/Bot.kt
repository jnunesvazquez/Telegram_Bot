package ourbot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.dispatcher.photos
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.entities.inputmedia.InputMediaPhoto
import com.github.kotlintelegrambot.entities.inputmedia.MediaGroup
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold


/**
 * Clase con nuetro bot principal
 */
class Bot{

    /**
     * Funcion para configurar un boton propio
     */
    fun mainBot() {

        val bot = bot {

            token = ApiKey.miApiKey

            dispatch {

                command("start") {
                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Alguien quiere fiesta UwU")
                }

                command("peliculas") {
                    bot.sendPoll(
                        chatId = ChatId.fromId(message.chat.id),
                        question = "Las mejores peliculas de la historia",
                        options = listOf(
                            "Doce hombres sin piedad",
                            "El padrino",
                            "Los siete samurais",
                            "La lista de Schindler",
                            "Los Vengadores: Endgame"
                        ),
                        openPeriod = 120,
                        allowsMultipleAnswers = true,
                        correctOptionId = 0,
                        isAnonymous = false
                    )
                }
                command("clint") {
                    bot.sendPoll(
                        chatId = ChatId.fromId(message.chat.id),
                        question = "Las mejores peliculas de Clint Eastwood",
                        options = listOf(
                            "El bueno, el feo y el malo",
                            "Gran Torino",
                            "Million Dollar Baby",
                            "Sin perdon",
                            "El sargento de hierro"
                        ),
                        openPeriod = 120,
                        correctOptionId = 0,
                        isAnonymous = false
                    )
                }
                message(Filter.Sticker) {
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = "¿En serio no tienes un esticker mejor para enviame?")
                }

                message(Filter.Reply or Filter.Forward) {
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = "Damian, tienes que aprobar a Joel y Brais")
                }

                photos {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Muy buena foto"
                    )
                }
                command("mediagroup") {
                    bot.sendMediaGroup(
                        chatId = ChatId.fromId(message.chat.id),
                        mediaGroup = MediaGroup.from(
                            InputMediaPhoto(
                                media = TelegramFile.ByUrl("https://i.blogs.es/d50a8e/deadpool-1200x675/1366_2000.jpeg"),
                                caption = "Ryan Reynolds muy sexy"
                            ),
                            InputMediaPhoto(
                                media = TelegramFile.ByUrl("https://images5.alphacoders.com/105/thumb-1920-1055769.jpg"),
                                caption = "Final Space best serie"
                            )
                        ),
                        replyToMessageId = message.messageId
                    )
                }
                command("memes") {
                    val markdownV2Text = """
                    Cuando te enteras que *NBA* significa __National__ _Basketball_ ~Association~ y *NO* negros bien altos
                    [Perturbador de mentes](https://www.youtube.com/watch?v=U4lz8MN6MQA)
                """.trimIndent()
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = markdownV2Text,
                        parseMode = ParseMode.MARKDOWN_V2
                    )
                }
                text("quiero") {
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Mi 10")
                }
            }
        }
        bot.startPolling()
    }
}
