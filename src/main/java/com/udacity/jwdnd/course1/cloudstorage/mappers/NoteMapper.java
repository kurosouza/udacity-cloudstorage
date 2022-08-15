package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;

@Mapper
public interface NoteMapper {

	/*
	@Results(id = "noteResultMap", value = {
			@Result(property = "title", column = "notetitle"),
			@Result(property = "description", column = "notedescription")
	})
	*/
	
	@Insert("INSERT INTO notes (notetitle, notedescription, userId) VALUES (#{note.noteTitle}, #{note.noteDescription}, #{note.userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId", keyColumn = "noteid")
	int createNote(@Param("note") Note note);
	
	@Select("SELECT * FROM notes WHERE id = #{id}")
	Note findNote(int id);
	
	@Update("UPDATE notes SET notetitle=#{title}, notedescription=#{description} WHERE noteid = #{noteId}")
	int updateNote(Note note);
	
	@Delete("DELETE FROM notes WHERE noteid = #{noteId}")
	boolean deleteNote(int id);
	
	// @ResultMap("noteResultMap")
	@Select("SELECT * FROM notes WHERE userId = #{userId}")
	List<Note> getUserNotes(Integer userId);
	
}