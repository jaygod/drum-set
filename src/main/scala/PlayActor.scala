import javax.sound.sampled.{AudioSystem, AudioInputStream}

import akka.actor.Actor

/**
  * Created by Kuba on 2016-03-05.
  */
class PlayActor extends Actor {
  override def receive: Receive = {
    case Play(inputStream,timeout) => playSound(inputStream,timeout)
  }

  def playSound(inputStream: AudioInputStream, timeout: Int): Unit = {
    val clip = AudioSystem.getClip
    clip.open(inputStream)

    timeout match {
      case 0 => clip.loop(0)
      case _ => {
        new Thread(new Runnable {
          def run() {
            while (true) {
              val clip = AudioSystem.getClip
              def hihatAudioIn = AudioSystem.getAudioInputStream(getClass.getResource("drums/HIHAT01.aif"))
              clip.open(hihatAudioIn)
              clip.start
              Thread.sleep(timeout)
            }
          }
        }).start
      }
    }
  }
}

case class Play(inputStream: AudioInputStream, timeout: Int)