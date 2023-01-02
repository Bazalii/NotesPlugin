package com.itmo.notesPlugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.io.File
import javax.swing.*
import javax.swing.table.DefaultTableModel

class MainPanel(private var project: Project?) : DialogWrapper(project) {
    private val notesRepository: NotesRepository =
        NotesRepository(File("${project!!.basePath}", "Notes"))

    private var mainPanel: JPanel? = null
    private var mainTable: JTable? = null
    private var addItemButton: JButton? = null
    private var updateItemButton: JButton? = null
    private var removeItemButton: JButton? = null

    init {
        drawTable()

        addItemButton!!.addActionListener {
            val dialog = AddItemPanel(project)
            dialog.show()

            drawTable()
        }

        updateItemButton!!.addActionListener {
            val dialog = UpdateItemPanel(project)
            dialog.show()

            drawTable()
        }

        removeItemButton!!.addActionListener {
            val dialog = RemoveItemPanel(project)
            dialog.show()

            drawTable()
        }

        init()
    }

    override fun createCenterPanel(): JComponent? {
        return mainPanel
    }

    override fun createSouthPanel(): JComponent? {
        return null
    }

    private fun drawTable() {
        val columns = arrayOf("Name", "Text")
        val notes = notesRepository.getAllForTable()

        mainTable!!.model = DefaultTableModel(
            notes,
            columns
        )
    }
}