import javax.sound.sampled.{AudioSystem, AudioInputStream}

import akka.actor.Actor

/**
  * Created by Kuba on 2016-03-05.
  */
class PlayActor extends Actor {
  override def receive: Receive = {
    case Play(inputStream, timeout) => playSound(inputStream, timeout)
  }

  def playSound(inputStream: AudioInputStream, timeout: Int): Unit = {
    val clip = AudioSystem.getClip
    clip.open(inputStream)
    clip.loop(0)
  }
}

case class Play(inputStream: AudioInputStream, timeout: Int)