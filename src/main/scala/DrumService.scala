import javax.sound.sampled.AudioSystem

import akka.actor.{Props, ActorSystem}
import spray.http.MediaTypes._
import spray.routing.SimpleRoutingApp

import scala.util.Properties


/**
  * Created by Kuba on 2016-03-06.
  */
object DrumService extends App with SimpleRoutingApp {

  implicit val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val playActor = system.actorOf(Props[PlayActor], name = "helloactor")

  val snore = getClass.getResource("drums/real-life-SN2.wav")
  val kick = getClass.getResource("drums/real-life-KICK1.wav")
  val hihat = getClass.getResource("drums/HHCLOSE1.aif")
  val crash1 = getClass.getResource("drums/real-life-CRASH1.wav")
  val crash2 = getClass.getResource("drums/real-life-CRASH2.wav")
  val tomF = getClass.getResource("drums/real-life-TOM2FAMBI.wav")
  val tomL = getClass.getResource("drums/real-life-TOM2LAMBI.wav")
  val tomM = getClass.getResource("drums/real-life-TOM2MAMBI.wav")
  val ride = getClass.getResource("drums/real-life-RIDE.wav")

  def snoreAudioIn = AudioSystem.getAudioInputStream(snore)

  def kickAudioIn = AudioSystem.getAudioInputStream(kick)

  def hihatAudioIn = AudioSystem.getAudioInputStream(hihat)

  def crash1AudioIn = AudioSystem.getAudioInputStream(crash1)

  def crash2AudioIn = AudioSystem.getAudioInputStream(crash2)

  def tomFAudioIn = AudioSystem.getAudioInputStream(tomF)

  def tomLAudioIn = AudioSystem.getAudioInputStream(tomL)

  def tomMAudioIn = AudioSystem.getAudioInputStream(tomM)

  def rideAudioIn = AudioSystem.getAudioInputStream(ride)

  val port = Properties.envOrElse("PORT", "8080").toInt
  startServer(interface = "0.0.0.0", port = port) {
    get {
      path("play" / "sound" / Segment) { note =>
        note match {
          case "Space" =>
            playActor ! Play(kickAudioIn, 0)
          case "Enter" =>
            playActor ! Play(snoreAudioIn, 0)
          case "Shift" =>
            playActor ! Play(hihatAudioIn, 0)
          case "W" =>
            playActor ! Play(crash1AudioIn, 0)
          case "E" =>
            playActor ! Play(crash2AudioIn, 0)
          case "I" =>
            playActor ! Play(tomFAudioIn, 0)
          case "O" =>
            playActor ! Play(tomLAudioIn, 0)
          case "P" =>
            playActor ! Play(tomMAudioIn, 0)
          case "Q" =>
            playActor ! Play(rideAudioIn, 0)
        }
        complete {
          "OK"
        }
      }
    } ~
      path("") {
        get {
          respondWithMediaType(`text/html`) {
            complete {
              <html>
                <body>
                  <h1>This is the Spray.io based back-end server</h1>
                </body>
              </html>
            }
          }
        }
      }
  }
}
