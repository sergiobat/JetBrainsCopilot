package dev.koding.copilot.config

import com.intellij.openapi.options.Configurable
import java.text.NumberFormat
import javax.swing.JFormattedTextField
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.text.DefaultFormatterFactory
import javax.swing.text.NumberFormatter

class ApplicationConfigurable : Configurable {

    private lateinit var panel: JPanel
    private lateinit var copilotToken: JTextField
    private lateinit var contextLines: JFormattedTextField

    override fun apply() {
        settings.token = copilotToken.text
        settings.contentLines = contextLines.text.toIntOrNull() ?: 10
    }

    override fun createComponent(): JPanel {
        contextLines.formatterFactory = DefaultFormatterFactory(NumberFormatter(NumberFormat.getIntegerInstance()))
        return panel
    }

    override fun reset() {
        copilotToken.text = settings.token ?: ""
        contextLines.text = settings.contentLines.toString()
    }

    override fun getDisplayName() = "GitHub Copilot"
    override fun isModified() = settings.token != copilotToken.text ||
            settings.contentLines != (contextLines.text.toIntOrNull() ?: 10)
}