
import javax.sound.sampled._

import akka.actor.ActorSystem
import akka.actor.Props

import scala.swing._
import scala.swing.event._

object Player extends SimpleSwingApplication {
  val system = ActorSystem("HelloSystem")
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

  def top = new MainFrame {
    val label = new Label {
      text = "No click yet"
    }
    contents = new BoxPanel(Orientation.Vertical) {
      contents += label
      border = Swing.EmptyBorder(30, 30, 10, 10)
      listenTo(keys)
      reactions += {
        case KeyPressed(_, Key.Space, _, _) =>
          playActor ! Play(kickAudioIn, 0)
        case KeyPressed(_, Key.Enter, _, _) =>
          playActor ! Play(snoreAudioIn, 0)
        case KeyPressed(_, Key.W, _, _) =>
          playActor ! Play(hihatAudioIn, 0)
        case KeyPressed(_, Key.Control, _, _) =>
          playActor ! Play(crash1AudioIn, 0)
        case KeyPressed(_, Key.M, _, _) =>
          playActor ! Play(crash2AudioIn, 0)
        case KeyPressed(_, Key.U, _, _) =>
          playActor ! Play(tomFAudioIn, 0)
        case KeyPressed(_, Key.I, _, _) =>
          playActor ! Play(tomLAudioIn, 0)
        case KeyPressed(_, Key.O, _, _) =>
          playActor ! Play(tomMAudioIn, 0)
        case KeyPressed(_, Key.Shift, _, _) =>
          playActor ! Play(rideAudioIn, 0)

      }
      focusable = true
      requestFocus
    }
  }
}
