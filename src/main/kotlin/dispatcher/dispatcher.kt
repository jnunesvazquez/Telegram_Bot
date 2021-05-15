package dispatcher

import Apikey.miApyKey
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.*
import com.github.kotlintelegrambot.entities.dice.DiceEmoji
import com.github.kotlintelegrambot.entities.inlinequeryresults.InlineQueryResult
import com.github.kotlintelegrambot.entities.inlinequeryresults.InputMessageContent
import com.github.kotlintelegrambot.entities.inputmedia.InputMediaPhoto
import com.github.kotlintelegrambot.entities.inputmedia.MediaGroup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.network.fold

fun main() {

    val bot = bot {

        token = miApyKey

        dispatch {
            //Este mensaje salta cuando contestas al bot con un stiker
            message(Filter.Sticker) {
                bot.sendMessage(ChatId.fromId(message.chat.id), text = "You have received an awesome sticker \\o/")
            }
            //Este mensaje salta cuando reenvias el mensaje del bot o por que lo respondes en el chat
            message(Filter.Reply or Filter.Forward) {
                bot.sendMessage(ChatId.fromId(message.chat.id), text = "Damian, tienes que aprobar a Joel y Brais")
            }

            command("start") {

                val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Bot iniciado")

                result.fold({

                }, {
                    // do something with the error
                })
            }
            command("hola") {

                val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Buenas dulce personita")

                result.fold({

                }, {
                    // do something with the error
                })
            }
            command("adios") {

                val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Adiós dulce personita")

                result.fold({

                }, {
                    // do something with the error
                })
            }

            command("comandoconargs") {
                val joinedArgs = args.joinToString() /*Crea una cadena de todos los elementos separados usando un
                separador y usando el prefijo y sufijo dados si se suministran. Si la colección puede ser enorme, puede
                especificar un valor de límite no negativo, en cuyo caso solo se agregarán los primeros elementos de
                límite, seguidos de la cadena truncada (que por defecto es "...").*/
                val response =
                    if (joinedArgs.isNotBlank()) joinedArgs else "No hay texto a parte del comando"
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = response)
            }

            //Altera el texto sin necesidad de un editor de texto. Solo sirve para texto incluido en el comando
            command("markdown") {
                val markdownText = "_Cool message_: *Markdown* is `beatiful` :P"
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = markdownText,
                    parseMode = ParseMode.MARKDOWN
                )
            }
            //
            command("markdown2") {
                val markdownV2Text = """
                    *bold \*text*
                    _italic \*text_
                    __underline__
                    ~strikethrough~
                    *bold _italic bold ~italic bold strikethrough~ __underline italic bold___ bold*
                    [inline URL](http://www.example.com/)
                    [inline mention of a user](tg://user?id=123456789)
                    `inline fixed-width code`
                    ```kotlin
                    fun main() {
                        println("Hello Kotlin!")
                    }
                    ```
                """.trimIndent()
                /*trimIndent() ->
                Detecta una sangría mínima común de todas las líneas de entrada, la elimina de cada línea y también
                elimina la primera y la última línea si están en blanco (observe la diferencia en blanco vs vacío).
                Tenga en cuenta que las líneas en blanco no afectan el nivel de sangría detectado. En caso de que haya
                líneas que no estén en blanco sin espacios en blanco iniciales (sin sangría), la sangría común es 0 y,
                por lo tanto, esta función no cambia la sangría. No conserva los finales de línea originales.
                 */

                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = markdownV2Text,
                    parseMode = ParseMode.MARKDOWN_V2
                )
            }
            /*
            sirve para crear dos botones
             */
            command("inlinebuttons") {
                val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                    listOf(InlineKeyboardButton.CallbackData(text = "Presioname", callbackData = "Madre mia")),
                    listOf(InlineKeyboardButton.CallbackData(text = "hola", callbackData = "showAlert"))
                )
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Hello, inline buttons!",
                    replyMarkup = inlineKeyboardMarkup
                )
            }
            /*
            Con esto le envias tu direccion al bot y tu numero de telefono. Solo funciona por privado con el bot, en
            un grupo no funciona este comando
            */
            command("f") {
                val keyboardMarkup = KeyboardReplyMarkup(keyboard = generateUsersButton(), resizeKeyboard = true)
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Hello, users buttons!",
                    replyMarkup = keyboardMarkup
                )
            }
            /*
            Este comando sirve para poder enviar fotos con el bot
             */
            command("mediagroup") {
                bot.sendMediaGroup(
                    chatId = ChatId.fromId(message.chat.id),
                    mediaGroup = MediaGroup.from(
                        InputMediaPhoto(
                            media = TelegramFile.ByUrl("https://www.wallpapertip.com/wmimgs/67-674353_epic-anime-wallpapers-21118-hd-pictures-data-src.jpg"),
                            caption = "Asombrate de mis fotos Damian"
                        ),
                        InputMediaPhoto(
                            media = TelegramFile.ByUrl("https://images5.alphacoders.com/105/thumb-1920-1055769.jpg"),
                            caption = "Final Space best serie"
                        )
                    ),
                    replyToMessageId = message.messageId
                )
            }
            //este texto de entrada tiene que coincidir con el texto que se le ponga al boton EN callbackData
            callbackQuery("Madre mia") {
                val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
            }
            //este texto de entrada tiene que coincidir con el texto que se le ponga al boton EN callbackData
            callbackQuery(
                callbackData = "showAlert",
                callbackAnswerText = "HelloText",
                callbackAnswerShowAlert = true
            ) {
                val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
            }

            text("ping") {
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Pong")
            }

            location {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Your location is (${location.latitude}, ${location.longitude})",
                    replyMarkup = ReplyKeyboardRemove()
                )
            }

            contact {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Hello, ${contact.firstName} ${contact.lastName}",
                    replyMarkup = ReplyKeyboardRemove()
                )
            }

            channel {
                // Handle channel update
            }

            inlineQuery {
                val queryText = inlineQuery.query

                if (queryText.isBlank() or queryText.isEmpty()) return@inlineQuery

                val inlineResults = (0 until 5).map {
                    InlineQueryResult.Article(
                        id = it.toString(),
                        title = "$it. $queryText",
                        inputMessageContent = InputMessageContent.Text("$it. $queryText"),
                        description = "Add $it. before you word"
                    )
                }
                bot.answerInlineQuery(inlineQuery.id, inlineResults)
            }

            photos {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Wowww, awesome photos!!! :P"
                )
            }

            command("diceAsDartboard") {
                bot.sendDice(ChatId.fromId(message.chat.id), DiceEmoji.Dartboard)
            }

            dice {
                bot.sendMessage(ChatId.fromId(message.chat.id), "A dice ${dice.emoji.emojiValue} with value ${dice.value} has been received!")
            }

            telegramError {
                println(error.getErrorMessage())
            }
        }

    }
    bot.startPolling()

}
fun generateUsersButton(): List<List<KeyboardButton>> {
    return listOf(
        listOf(KeyboardButton("Request location (not supported on desktop)", requestLocation = true)),
        listOf(KeyboardButton("Request contact", requestContact = true))
    )
}