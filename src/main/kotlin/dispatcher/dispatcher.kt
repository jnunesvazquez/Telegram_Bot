package dispatcher

import Apikey.miApyKey
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.network.fold

fun main() {

    val bot = bot {

        token = miApyKey

        dispatch {

            message(Filter.Sticker) {
                bot.sendMessage(ChatId.fromId(message.chat.id), text = "You have received an awesome sticker \\o/")
            }
            message(Filter.Reply or Filter.Forward) {
                bot.sendMessage(ChatId.fromId(message.chat.id), text = "someone is replying or forwarding messages ...")
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

            command("inlinebuttons") {
                val inlineKeyboardMarkup = InlineKeyboardMarkup.create(
                    listOf(InlineKeyboardButton.CallbackData(text = "Test Inline Button", callbackData = "testButton")),
                    listOf(InlineKeyboardButton.CallbackData(text = "Show alert", callbackData = "showAlert"))
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

