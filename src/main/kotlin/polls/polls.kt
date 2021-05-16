import Apikey.miApyKey
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.pollAnswer
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.polls.PollType.QUIZ
import kotlin.concurrent.thread

fun main() {

    val bot = bot {

        token = miApyKey

        dispatch {

            //Devuelve un mensaje por consola con el usuario que ha seleccionado una opcion en la encuesta
            pollAnswer {
                println("${pollAnswer.user.username} has selected the option ${pollAnswer.optionIds.lastOrNull()} in the poll ${pollAnswer.pollId}")
            }

            //Comando que mostrara una encuesta con todos los profesores
            command("bestpollever") {
                bot.sendPoll(
                    //Recoge la id del usuario que haya votado en la encuesta
                    chatId = ChatId.fromId(message.chat.id),
                    //Define el tipo de encuesta
                    type = QUIZ,
                    //La pregunta
                    question = "El mejor profesor de programacion?",
                    //Las opciones a escoger
                    options = listOf(
                        "Nina",
                        "Ricardo",
                        "Elisa",
                        "Manuel(???)",
                        "El petardo y mas increible vago DAMIAAAAAAAAN!!!!"
                    ),
                    //Decimos que opcion del array es la correcta
                    correctOptionId = 4,
                    //Definimos la duracion de la encuesta
                    openPeriod = 60,
                    //Declaramos si la votacion es anonima o no lo es
                    isAnonymous = false
                )
            }
        }
    //Comenzamos la encuesta
    }.startPolling()
}
