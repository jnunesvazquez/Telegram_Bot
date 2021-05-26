package ourbot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.entities.inputmedia.InputMediaPhoto
import com.github.kotlintelegrambot.entities.inputmedia.MediaGroup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.extensions.filters.Filter

/**
 * Clase con nuetro bot principal
 */
class mainBot{

    /**
     * Funcion para configurar un boton propio
     */
    fun mainBot() {

        val bot = bot {

            token = ApiKey.miApiKey

            dispatch {

                command("start") {
                    val result =
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Alguien quiere fiesta UwU")
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
                    bot.sendMessage(
                        ChatId.fromId(message.chat.id),
                        text = "¿En serio no tienes un esticker mejor para enviame?"
                    )
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
                command("inlinebuttons") {
                    val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                        //text es el texto que aparece en el boton
                        //callbackData Retoena el callbackQuery asociado (linea de codigo )
                        listOf(InlineKeyboardButton.CallbackData(text = "Presioname", callbackData = "Madre mia")),
                        //text es el texto que aparece en el boton
                        //callbackData Retoena el callbackQuery asociado (linea de codigo )
                        listOf(InlineKeyboardButton.CallbackData(text = "Hola", callbackData = "Quiero mi 10"))
                    )
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Mis botones, pulsalos a placer",
                        replyMarkup = inlineKeyboardMarkup
                    )
                }
                callbackQuery("Madre mia") {
                    val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                    bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
                }
                callbackQuery(
                    callbackData = "Quiero mi 10",
                    callbackAnswerText = "Por el duro esfuerzo en realizar esta tarea quiero mi 10, no espero menos que eso",
                    callbackAnswerShowAlert = true
                ) {
                    val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                    bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
                }
            }
        }
        bot.startPolling()
    }
}
