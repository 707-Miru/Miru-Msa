<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.back.miru.model.dao.MemberDao">

	<insert id="signUp" parameterType="Member">
		insert into member (id, password, name, email, address, phone)
		values (#{id}, #{password}, #{name}, #{email}, #{address}, #{phone})
	</insert>

	<select id="login" parameterType="map" resultType="Member">
		select *
		from member
		where id = #{id} and password = #{password}
	</select>
	
	<select id="getMember" parameterType="String" resultType="Member">
		select * from member where id = #{id}
	</select>
	
	<update id="update" parameterType="Member">
		update member
		<!-- id,와 name은 수정 안함 -->
		<trim prefix="set" suffixOverrides=",">
			<if test="password != null">password = #{password},</if>
			<if test="email != null">email = #{email},</if>
			<if test="address != null">address = #{address},</if>
			<if test="phone != null">phone = #{phone},</if>
		</trim>
		where id = #{id}
	</update>
	
	<delete id="delete" parameterType="String">
		delete from member where id = #{id}
	</delete>
	
	<select id="interestList" resultType="Interest">
		select * from interest where id = #{id}
	</select>
	
	<insert id="addInterest" parameterType="map">
		insert into interest(id, dongCode, dongName)
		values(#{id}, #{dongcode}, #{dongname})
	</insert>
	
	<delete id="removeInterest" parameterType="map">
		delete from interest where id=#{id} and dongCode = #{dongcode}
	</delete>
	
</mapper>