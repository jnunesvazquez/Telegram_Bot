##Bot de Telegram

 ###Indice

> 1. Echo
> 2. Dispatcher
> 3. Polls
> 4. Intento de Webhook
> 5. Nuestro Bot

###Echo
Si el echo esta activado, es capaz de reproducir cualquier mensaje que le indiquemos despues de introducir un comando

~~~
command("start") {
         val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Hi there!")
}
~~~
###Dispatcher

###Polls
Las polls se tratan de encuestas en las que podemos votar en encuestas de opinion o contestar a preguntas de informacion general. Existen de dos tipos, las QUIZ (Preguntas con una respuesta correcta) y las REGULAR (Pueden o no tener una respuesta correcta)

~~~
command("poll") {
        bot.sendPoll(
            chatId = ChatId.fromId(message.chat.id),
            type = QUIZ,
            question = "El mejor profesor de programacion?",
            options = listOf(
                "Nina",
                "Ricardo",
                "Elisa",
                "Manuel(???)",
                "El petardo y mas increible vago DAMIAAAAAAAAN!!!!"
            ),
            correctOptionId = 4,
            openPeriod = 60,
            isAnonymous = false
        )
    }
~~~

![Imagen Polls](.\Images\Polls.png)
###WebHook
(Hemos intentado hacerlo pero nos hemos quedado a mitad del camino por falta de conocimiento y muchos intentos)

Se trata de otra forma de acceder a la API de Telegram en vez de utilizar el getUpdates y con la ventaja de hacerlo con una URL de un dominio propio
Para poder hacerlo se necesita un certificado del servidor de terceros, una URL propia, una clave privada, otra publica y una de almacenamiento.
Despues de crear el enlace con nuestra URL, podremos recibir notificaciones de Telegram desde nuestro dominio
> Proceso

![Proceso_1](.\Images\Proceso_1.png)
![Proceso_2_3](.\Images\Proceso_2_3.png)
![Proceso_4](.\Images\Proceso_4.png)

Generaremos estos cuatro archivos que se supone que tenemos que utilizar en el bot

![Archivos_1](.\Images\Archivos_1.png)
![Archivos_2](.\Images\Archivos_2.png)

###Nuestro Bot

###Autores

Joel Jorge Nunes Vázquez 

Brais Martínez Paredes
