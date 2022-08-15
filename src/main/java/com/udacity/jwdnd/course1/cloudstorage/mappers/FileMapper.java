package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;

@Mapper
public interface FileMapper {

	@Select("SELECT * FROM files WHERE fileId = #{id}")
	FileModel getFile(int id);
	
	@Insert("INSERT INTO files(filename, contenttype, filesize, userId, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	int insert(FileModel file);
	
	@Select("SELECT * FROM files WHERE userId = #{userId}")
	List<FileModel> getUserFiles(Integer userId);
	
	@Delete("DELETE FROM files WHERE fileId = #{id}")
	int delete(int id);
	
}
