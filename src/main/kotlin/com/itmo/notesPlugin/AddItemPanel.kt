package com.itmo.notesPlugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.io.File
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class AddItemPanel(project: Project?) : DialogWrapper(project) {
    private val notesRepository: NotesRepository =
        NotesRepository(File("${project!!.basePath}", "Notes"))

    private var addItemPanel: JPanel? = null
    private var noteName: JTextField? = null
    private var noteText: JTextField? = null

    init {
        init()
    }

    override fun createCenterPanel(): JComponent? {
        return addItemPanel
    }

    override fun doOKAction() {
        val note = Note(noteName!!.text, noteText!!.text)

        notesRepository.add(note)

        super.doOKAction()
    }
}