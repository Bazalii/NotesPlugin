package com.itmo.notesPlugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class NotesAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = MainPanel(e.project)

        dialog.title = "Notes"

        dialog.show()
    }
}