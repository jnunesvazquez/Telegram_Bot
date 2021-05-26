package dispatcher

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

class Dispatcher {

    fun dispatcher() {

        val bot = bot {

            token = ApiKey.miApiKey //el token indica cual es nuentro bot y a que le estamos mandando nuetros comandos

            dispatch {
                //Este mensaje salta cuando contestas al bot con un stiker
                message(Filter.Sticker) {
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = "¿En serio no tienes un esticker mejor para enviame?")
                }
                //Este mensaje salta cuando reenvias el mensaje del bot o por que lo respondes en el chat
                message(Filter.Reply or Filter.Forward) {
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = "Damian, tienes que aprobar a Joel y Brais")
                }
                /**
                 * @param start Nombre del comando de nuestro bot
                 * Este comando retorna un texto en el que nos indica que el bor a sido iniciado
                 */
                /**
                 * @param start Nombre del comando de nuestro bot
                 * Este comando retorna un texto en el que nos indica que el bor a sido iniciado
                 */
                command("start") {

                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Bot iniciado")

                    result.fold({

                    }, {
                        // do something with the error
                    })
                }
                /**
                 * @param hola Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                /**
                 * @param hola Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                command("hola") {

                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Buenas dulce personita")

                    result.fold({

                    }, {
                        // do something with the error
                    })
                }
                /**
                 * @param adios Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                /**
                 * @param adios Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                command("adios") {

                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Adiós dulce personita")

                    result.fold({

                    }, {
                        // do something with the error
                    })
                }
                /**
                 * @param comandoconargs Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                /**
                 * @param comandoconargs Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                command("comandoconargs") {
                    val joinedArgs = args.joinToString() /*Crea una cadena de todos los elementos separados usando un
                separador y usando el prefijo y sufijo dados si se suministran. Si la colección puede ser enorme, puede
                especificar un valor de límite no negativo, en cuyo caso solo se agregarán los primeros elementos de
                límite, seguidos de la cadena truncada (que por defecto es "...").*/
                    val response =
                        if (joinedArgs.isNotBlank()) joinedArgs else "No hay texto a parte del comando"
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = response)
                }
                /**
                 * @param markdown Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                /**
                 * @param markdown Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                //Altera el texto sin necesidad de un editor de texto. Solo sirve para texto incluido en el comando
                command("markdown") {
                    val markdownText = "_Mi gran mensaje_: *Markdown* es `util a su manera`"
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = markdownText,
                        parseMode = ParseMode.MARKDOWN
                    )
                }
                /**
                 * @param markdown2 Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                /**
                 * @param markdown2 Nombre del comando de nuestro bot
                 * Este comando retorna un texto
                 */
                //Version mejorada del marckdown. Lo de la mencion del usuario aun no funciona ni en la libreria de kotlinTelegram
                command("markdown2") {
                    val markdownV2Text = """
                    *negrita*
                    _italic_
                    __subrallado__
                    ~tachado~
                    *negrita _italic negrita ~italic negrita tachada~ __subrallado italic negrita___ negrita*
                    [Nuestro bot](https://github.com/jnunesvazquez/Telegram_Bot)
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
                /**
                 * @param inlinebuttons Nombre del comando de nuestro bot
                 * Este comando retorna botones que se le puede asociar texto o un aventana emerjente con texto
                 */
                /**
                 * @param inlinebuttons Nombre del comando de nuestro bot
                 * Este comando retorna botones que se le puede asociar texto o un aventana emerjente con texto
                 */
                //sirve para crear dos botones
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
                /**
                 * @param f Nombre del comando de nuestro bot
                 * Este comando retorna un  texto y dos botones situados en donde el usuario puede escribir
                 */
                /**
                 * @param f Nombre del comando de nuestro bot
                 * Este comando retorna un  texto y dos botones situados en donde el usuario puede escribir
                 */
                /*
                Con esto le envias tu direccion al bot y tu numero de telefono. Solo funciona por privado con el bot, en
                un grupo no funciona este comando
                */
                command("f") {
                    val keyboardMarkup = KeyboardReplyMarkup(keyboard = generateUsersButton(), resizeKeyboard = true)
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Aqui los botones de usuarios",
                        replyMarkup = keyboardMarkup
                    )
                }
                /**
                 * @param mediagroup Nombre del comando de nuestro bot
                 * Este comando retorna un texto y fotos
                 */
                /**
                 * @param mediagroup Nombre del comando de nuestro bot
                 * Este comando retorna un texto y fotos
                 */
                //Este comando sirve para poder enviar fotos con el bot
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
                /**
                 * @param Madremia Es el texto que aparece en el collbackData del boton asociado
                 * Retorna el callbackQuery
                 */
                /**
                 * @param Madremia Es el texto que aparece en el collbackData del boton asociado
                 * Retorna el callbackQuery
                 */
                //este texto de entrada tiene que coincidir con el texto que se le ponga al boton EN callbackData
                callbackQuery("Madre mia") {
                    val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                    bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
                }
                /**
                 * @param Quieromi10 Es el texto que aparece en el collbackData del boton asociado
                 * Retorna el callbackQuery
                 */
                /**
                 * @param Quieromi10 Es el texto que aparece en el collbackData del boton asociado
                 * Retorna el callbackQuery
                 */
                //este texto de entrada tiene que coincidir con el texto que se le ponga al boton EN callbackData
                callbackQuery(
                    callbackData = "Quiero mi 10",
                    callbackAnswerText = "Por el duro esfuerzo en realizar esta tarea quiero mi 10, no espero menos que eso",
                    callbackAnswerShowAlert = true
                ) {
                    val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
                    bot.sendMessage(ChatId.fromId(chatId), callbackQuery.data)
                }
                // si pones /quiero te devielve un texto
                text("quiero") {
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Mi 10")
                }
                //te devuelve la latituid y longitud de te localizacion
                location {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Tu localizacion es (latitud=${location.latitude}, longitud=${location.longitude})",
                        replyMarkup = ReplyKeyboardRemove()
                    )
                }
                //devuelve un mensaje de texto con nuetro nombre
                contact {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Buenas, ${contact.firstName} ${contact.lastName}",
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
                //Cuando envias una foto te devuelve un texto. En un grupo tendrias que responderle a un comentario del bot
                photos {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Muy buena foto"
                    )
                }
                /**
                 * @param dado nombre del comando
                 * Retorna un dado/diana en formato emoji que saca una puntuacion
                 */
                /**
                 * @param dado nombre del comando
                 * Retorna un dado/diana en formato emoji que saca una puntuacion
                 */
                command("dado") {
                    bot.sendDice(ChatId.fromId(message.chat.id), DiceEmoji.Dartboard)
                }
                //cuando reenvias la diana a el bot por privado te devuelve la puntiacion
                dice {
                    bot.sendMessage(ChatId.fromId(message.chat.id), "Un dado ${dice.emoji.emojiValue} con valor ${dice.value} ha sido recivido")
                }
                //sale cuando suceda un error
                telegramError {
                    println(error.getErrorMessage())
                }
            }

        }
        bot.startPolling()

    }

    /**
     * @return Retorna unos botones generdos
     */
    fun generateUsersButton(): List<List<KeyboardButton>> {
        return listOf(
            listOf(KeyboardButton("Te digo tu localizacion (no lo soporta el ordenador)", requestLocation = true)),
            listOf(KeyboardButton("Envio tu numero de contacto ", requestContact = true))
        )
    }
}