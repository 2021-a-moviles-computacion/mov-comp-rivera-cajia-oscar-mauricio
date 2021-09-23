package com.example.deber02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerViewFeed : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view_feed)

        val listaFeed = arrayListOf<BFeedHome>()
        val listaMensajes = arrayListOf<BFeedHome>()
        val listaNotifications = arrayListOf<BNotifications>()
        val listaTrends = arrayListOf<BTrends>()




        val messagesIcon = findViewById<ImageView>(R.id.icon_messages)
        val homeIcon = findViewById<ImageView>(R.id.icon_home)
        val exploreIcon = findViewById<ImageView>(R.id.icon_explore)
        val notiIcon = findViewById<ImageView>(R.id.icon_noti)
        val imageTop = findViewById<ImageView>(R.id.top_image)

        val imagePost = findViewById<ImageView>(R.id.img_post)



        listaFeed.add(
            BFeedHome(
                "@aleja98",
                "Alejandra G.",
                R.drawable.user1,
                "Acabo de volver de Miami y ha sido un viaje inolvidable.",
                "Estas despedido.",
                "12 ago.",
                345,
                57,
                67
            )
        )

        listaFeed.add(
            BFeedHome(
                "@ghostman",
                "Fantasma del averno",
                R.drawable.user2,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "Estas despedido.",
                "15 jul.",
                666,
                798,
                3
            )
        )

        listaFeed.add(
            BFeedHome(
                "@pedro_m",
                "Divulgadora falaz",
                R.drawable.user3,
                "Hoy ha sido un día muy malo para mi.",
                "Estas despedido.",
                "21 abr.",
                35,
                6,
                9
            )
        )

        listaFeed.add(
            BFeedHome(
                "@cazadora4502",
                "Julio Maldonado",
                R.drawable.user4,
                "La semana que viene fiesta en mi casa.",
                null,
                "12 mar.",
                56,
                245,
                87
            )
        )

        listaFeed.add(
            BFeedHome(
                "@jaquerMan",
                "Incógnito",
                R.drawable.user5,
                "No me arrepiento de haber derribado la facultad.",
                null,
                "19 jul.",
                23,
                5,
                6
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@laura90",
                "Laura Goya",
                R.drawable.user8,
                "Acabo de volver de Miami y ha sido un viaje inolvidable.",
                "Estás despedido.",
                "1 h.",
                345,
                57,
                67
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@ghostman",
                "Fantasma del averno",
                R.drawable.user5,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "Te hemos sacado del grupo.",
                "11 ago.",
                666,
                798,
                3
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@villegasQ",
                "El buscón",
                R.drawable.user10,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "Tu perro ha desaparecido!",
                "1 ago.",
                666,
                798,
                3
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@user230928",
                "Julia G.",
                R.drawable.user3,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "Hablamos por whatsapp mejor",
                "25 jul.",
                666,
                798,
                3
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@BeaDestroyer93",
                "Beatriz M.",
                R.drawable.user1,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "La policia va hacia tu casa!",
                "23 jul.",
                666,
                798,
                3
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@calculadoraLoca",
                "Vanessa Vargas",
                R.drawable.user7,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "No tengo llaves",
                "14 jul.",
                666,
                798,
                3
            )
        )

        listaMensajes.add(
            BFeedHome(
                "@loboWallStreet",
                "Visonario",
                R.drawable.user9,
                "Las nuevas actualizaciones de Win10. no me dejan de sorprender.",
                "Sabemos donde vives",
                "25 jun.",
                666,
                798,
                3
            )
        )


        listaNotifications.add(
                BNotifications(
                    R.drawable.user9,
                    R.drawable.user1,
                    R.drawable.user8,
                    R.drawable.user3,
                    R.drawable.user4,
                    R.drawable.user2,
                    null,

                    R.drawable.ret,
                    null,

                    "meDicenLeo y 5 más indicaron que les gusta tu respuesta",
                    "Me encuentro yo a chicas mas atractivas que la Expósito cada día"

                )
                )

        listaNotifications.add(
            BNotifications(
                R.drawable.user10,
                R.drawable.user6,
                R.drawable.user7,
                null,
                null,
                null,
                null,

                R.drawable.fav,
                null,

                "Alonso y 2 más retweettearon tu respuesta",
                "Elijo navegar la euforia con los pies en el suelo."

            )
        )

        listaTrends.add(
            BTrends(
                "Fútbol",
                "PSG",
                "4.895"
            )
        )

        listaTrends.add(
            BTrends(
                "Política",
                "Los talibanes",
                "19.8 mil"
            )
        )

        listaTrends.add(
            BTrends(
                "Política",
                "La UE",
                "29,4 mil"
            )
        )

        listaTrends.add(
            BTrends(
                "Pop",
                "lorde",
                "60,4 mil"
            )
        )

        listaTrends.add(
            BTrends(
                "Deportes",
                "Cristiano Ronaldo",
                "10,2 mil"
            )
        )



        val recyclerViewFeedHome = findViewById<RecyclerView>(R.id.rv_feed)

        iniciarRecyclerView(listaFeed,
            this,
            recyclerViewFeedHome)

        messagesIcon.setOnClickListener{
            imageTop.setImageResource(R.drawable.mensajes)
            imagePost.setImageResource(R.drawable.icon_newm)
            messagesIcon.setImageResource(R.drawable.icon_message_select)
            homeIcon.setImageResource(R.drawable.icon_homez)
            exploreIcon.setImageResource(R.drawable.icon_explorez)
            notiIcon.setImageResource(R.drawable.icon_notiz)

            iniciarRecyclerViewM(listaMensajes,
                this,
                recyclerViewFeedHome)
        }

        homeIcon.setOnClickListener{
            imageTop.setImageResource(R.drawable.inicio)
            imagePost.setImageResource(R.drawable.icon_post)
            homeIcon.setImageResource(R.drawable.icon_home_select)
            exploreIcon.setImageResource(R.drawable.icon_explorez)
            notiIcon.setImageResource(R.drawable.icon_notiz)
            messagesIcon.setImageResource(R.drawable.icon_messagez)

            iniciarRecyclerView(listaFeed,
                this,
                recyclerViewFeedHome)
        }

        exploreIcon.setOnClickListener{
            imageTop.setImageResource(R.drawable.explorar)
            imagePost.setImageResource(R.drawable.icon_post)
            exploreIcon.setImageResource(R.drawable.icon_explore_select)
            notiIcon.setImageResource(R.drawable.icon_notiz)
            messagesIcon.setImageResource(R.drawable.icon_messagez)
            homeIcon.setImageResource(R.drawable.icon_homez)
            iniciarRecyclerViewT(listaTrends,
                this,
                recyclerViewFeedHome)
        }

        notiIcon.setOnClickListener{
            imageTop.setImageResource(R.drawable.notificaciones)
            imagePost.setImageResource(R.drawable.icon_post)
            notiIcon.setImageResource(R.drawable.icon_noti_select)
            messagesIcon.setImageResource(R.drawable.icon_messagez)
            homeIcon.setImageResource(R.drawable.icon_homez)
            exploreIcon.setImageResource(R.drawable.icon_explorez)
            iniciarRecyclerViewN(listaNotifications,
                this,
                recyclerViewFeedHome)
        }



    }

    fun iniciarRecyclerView(
        lista: List<BFeedHome>,
        actividad: GRecyclerViewFeed,
        recyclerView: RecyclerView) {

        val adaptador = FRecyclerViewAdaptadorFeed( actividad,
            lista,
            recyclerView)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewM(
        lista: List<BFeedHome>,
        actividad: GRecyclerViewFeed,
        recyclerView: RecyclerView) {

        val adaptador = FRecyclerViewAdaptadorMessages( actividad,
            lista,
            recyclerView)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewN(
        lista: List<BNotifications>,
        actividad: GRecyclerViewFeed,
        recyclerView: RecyclerView) {

        val adaptador = FRecyclerViewAdaptadorNotifications( actividad,
            lista,
            recyclerView)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewT(
        lista: List<BTrends>,
        actividad: GRecyclerViewFeed,
        recyclerView: RecyclerView) {

        val adaptador = FRecyclerViewAdaptadorTrends( actividad,
            lista,
            recyclerView)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }







}