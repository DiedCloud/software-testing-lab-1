import org.example.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArturJourneyTests {
    @Test
    fun jawDropping() {
        val jaw = Jaw()
        jaw.dropJaw()
        assertEquals(jaw.state, JawState.DROPPED)
    }

    @Test
    fun personCreation() {
        val person = Person("Тест", listOf(Head(1, Emotion.RELAXED, Action.PICKING_TEETH)))
        assertEquals(person.name, "Тест")
        assertEquals(person.heads.first().emotion, Emotion.RELAXED)
        assertEquals(person.heads.first().currentAction, Action.PICKING_TEETH)
    }

    @Test
    fun `Control panel's functional button has to work`() {
        val controlPanel = ControlPanel(listOf(Button("Пуск", true)))
        assertEquals(controlPanel.pressButton("Пуск"), "Нажата кнопка 'Пуск'. Пульт активен!")
        assertEquals(controlPanel.isActive, true)
    }

    @Test
    fun `Control panel's non functional button can't work`() {
        val controlPanel = ControlPanel(listOf(Button("Стоп", false)))
        assertEquals(controlPanel.pressButton("Стоп"), "Кнопка 'Стоп' не работает или отсутствует.")
        assertEquals(controlPanel.isActive, false)
    }

    @Test
    fun `Control panel's unknown button can't work`() {
        val controlPanel = ControlPanel(listOf(Button("Пуск", true)))
        assertEquals(controlPanel.pressButton("Неизвестная"), "Кнопка 'Неизвестная' не работает или отсутствует.")
    }

    @Test
    fun `Scene description generates correct`() {
        val arthur = Person("Артур", listOf(Head(1, Emotion.NERVOUS)))
        arthur.jaw.dropJaw() // Артур в шоке

        val mysteriousMan = Person(
            "Неизвестный",
            listOf(Head(1, Emotion.RELAXED, Action.PICKING_TEETH), Head(2, Emotion.SMILING)),
            handPosition = HandPosition.IN_MOUTH
        )

        val chair = Chair().apply { isOccupied = true }
        val controlPanel = ControlPanel(listOf(Button("Пуск", true), Button("Стоп", false))).apply {
            hasFeetOnIt = true
        }

        val scene = Scene(arthur, mysteriousMan, chair, controlPanel)

        assertEquals(scene.describe(), """
            |Артур nervous и с отвисшей челюстью.
            |Неизвестный relaxed и picking_teeth, smiling и null.
            |Кресло занято.
            |Пульт управления используется как подставка для ног и неактивен.
        """.trimMargin())
    }
}
