package com.itmo.notesPlugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.io.File
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class RemoveItemPanel(project: Project?) : DialogWrapper(project) {
    private val notesRepository: NotesRepository =
        NotesRepository(File("${project!!.basePath}", "Notes"))

    private var removeItemPanel: JPanel? = null
    private var noteName: JTextField? = null

    init {
        init()
    }

    override fun createCenterPanel(): JComponent? {
        return removeItemPanel
    }

    override fun doOKAction() {
        notesRepository.removeByName(noteName!!.text)

        super.doOKAction()
    }
}