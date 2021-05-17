package ourbot

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
                val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Bot iniciado")
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
                    isAnonymous = false
                )
            }
            command("series") {
                bot.sendPoll(
                    chatId = ChatId.fromId(message.chat.id),
                    question = "Las mejores series de la historia",
                    options = listOf(
                        "The Wire",
                        "Breaking Bad",
                        "Juego de Tronos",
                        "True Detective",
                        "Monster"
                    ),
                    openPeriod = 120,
                    correctOptionId = 0,
                    isAnonymous = false
                )
            }
        }
    }
    bot.startPolling()
}