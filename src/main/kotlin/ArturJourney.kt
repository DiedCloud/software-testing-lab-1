package org.example

// Эмоции персонажа
enum class Emotion {
    NERVOUS, SHOCKED, RELAXED, SMILING
}

// Позиция рук
enum class HandPosition {
    IN_MOUTH, ON_CONTROL_PANEL, RELAXED
}

// Действия, которые может выполнять персонаж
enum class Action {
    PICKING_TEETH, LEANING_BACK, USING_CONTROL_PANEL
}

// Состояние челюсти
enum class JawState {
    NORMAL, DROPPED
}

// Челюсть персонажа
class Jaw {
    var state: JawState = JawState.NORMAL
    private set;

    fun dropJaw() {
        state = JawState.DROPPED
    }

    override fun toString(): String {
        return if (state == JawState.DROPPED) "с отвисшей челюстью" else "с нормальной челюстью"
    }
}

// Голова, которая может иметь эмоции и выполнять действия
data class Head(
    val id: Int,
    var emotion: Emotion,
    var currentAction: Action? = null
)

// Человек с двумя головами и челюстью
data class Person(
    val name: String,
    val heads: List<Head>,
    var jaw: Jaw = Jaw(),
    var handPosition: HandPosition = HandPosition.RELAXED
)

// Кресло, в котором можно развалиться
class Chair {
    var isOccupied: Boolean = false
}

// Кнопка на пульте управления
data class Button(val label: String, val isFunctional: Boolean)

// Пульт управления, на который можно положить ноги или использовать по назначению
class ControlPanel(val buttons: List<Button>) {
    var hasFeetOnIt: Boolean = false
    var isActive: Boolean = false

    fun pressButton(label: String): String {
        val button = buttons.find { it.label == label }
        return if (button != null && button.isFunctional) {
            isActive = true
            "Нажата кнопка '$label'. Пульт активен!"
        } else {
            "Кнопка '$label' не работает или отсутствует."
        }
    }
}

// Сцена, описывающая происходящее
data class Scene(
    val arthur: Person,
    val mysteriousMan: Person,
    val chair: Chair,
    val controlPanel: ControlPanel
) {
    fun describe(): String {
        return """
            |Артур ${arthur.heads.joinToString { it.emotion.name.lowercase() }} и ${arthur.jaw}.
            |Неизвестный ${mysteriousMan.heads.joinToString { "${it.emotion.name.lowercase()} и ${it.currentAction?.name?.lowercase()}" }}.
            |Кресло ${if (chair.isOccupied) "занято" else "свободно"}.
            |Пульт управления ${if (controlPanel.hasFeetOnIt) "используется как подставка для ног" else "свободен"} и ${if (controlPanel.isActive) "активен" else "неактивен"}.
        """.trimMargin()
    }
}
