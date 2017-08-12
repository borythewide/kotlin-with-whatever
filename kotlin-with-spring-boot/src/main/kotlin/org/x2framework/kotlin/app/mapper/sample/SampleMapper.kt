package org.x2framework.kotlin.app.mapper.sample

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface SampleMapper {
	@Select("SELECT 1 as \"One\" FROM DUAL")
	fun getOne():Int
}