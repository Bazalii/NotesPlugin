package com.itmo.notesPlugin

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class NotesRepository(private var file: File) {
    private val jsonFormat = Json {
        encodeDefaults = true
        prettyPrint = true
    }

    @Serializable
    private companion object {
        var notes: MutableMap<String, String> = mutableMapOf()
    }

    init {
        load()
    }

    fun add(note: Note) {
        if (!notes.containsKey(note.name)){
            notes[note.name] = note.text
            save()
        }
    }

    fun getAllForTable(): Array<Array<String>> {
        val notes = Array(NotesRepository.notes.count()) { Array(2) { "0" } }
        var pointer = 0

        NotesRepository.notes.forEach { note ->
            notes[pointer] = arrayOf(note.key, note.value)
            pointer += 1
        }

        return notes
    }

    fun update(note: Note) {
        if (notes.containsKey(note.name)){
            notes[note.name] = note.text
            save()
        }
    }

    fun removeByName(name: String) {
        notes.remove(name)
        save()
    }

    private fun load() {
        if (!file.exists()) {
            save()
        } else {
            notes = jsonFormat.decodeFromString(file.readText())
        }
    }

    private fun save() {
        val data = jsonFormat.encodeToString(notes)

        file.writeText(data)
    }
}