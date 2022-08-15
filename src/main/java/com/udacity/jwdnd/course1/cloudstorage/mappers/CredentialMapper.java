package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;

@Mapper
public interface CredentialMapper {

	@Insert("INSERT INTO credentials (url, username, key1, password, userid) VALUES (#{url}, #{username}, #{key1}, #{password}, #{userId})")
	int insert(Credential credential);
	
	@Select("SELECT * FROM credentials WHERE userId = #{userId}")
	List<Credential> getCredentialsByUserId(Integer userId);
	
	@Delete("DELETE FROM credentials WHERE credentialid = #{credentialId}")
	int delete(Integer credentialId);
	
	@Update("UPDATE credentials SET url = #{url}, username = #{username}, password = #{password}, key1 = #{key1} WHERE credentialid = #{credentialId}")
	int update(Credential credential);
	
}
