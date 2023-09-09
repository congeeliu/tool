<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.mapper.${className}Mapper">
    <insert id="add">
        insert into ${className?uncap_first} (<#if properties??><#list properties as property><#if property_index != 0>${property.name}<#sep>, </#if></#list></#if>) values (<#if properties??><#list properties as property><#if property_index != 0><#noparse>#{</#noparse>${property.name}<#noparse>}</#noparse><#sep>, </#if></#list></#if>)
    </insert>

    <delete id="remove">
        delete from ${className?uncap_first} where ${firstPropertyName}=<#noparse>#{</#noparse>${firstPropertyName}<#noparse>}</#noparse>
    </delete>

    <update id="update">
        update ${className?uncap_first}
        set <#if properties??><#list properties as property><#if property_index != 0>${property.name} = <#noparse>#{</#noparse>${property.name}<#noparse>}</#noparse><#sep>, </#if></#list></#if>
        where ${firstPropertyName} = <#noparse>#{</#noparse>${firstPropertyName}<#noparse>}</#noparse>
    </update>

    <select id="getList" resultType="${packageName}.pojo.${className}">
        select * from ${className?uncap_first} limit <#noparse>#{</#noparse>currIndex<#noparse>}</#noparse>, <#noparse>#{</#noparse>pageSize<#noparse>}</#noparse>
    </select>

    <select id="getCount" resultType="Integer">
        select count(*) from ${className?uncap_first}
    </select>

    <select id="find" resultType="${packageName}.pojo.${className}">
        select * from ${className?uncap_first}
        <where>
            <if test="${firstPropertyName} != ''">
                and ${firstPropertyName} like concat('%', <#noparse>#{</#noparse>${firstPropertyName}<#noparse>}</#noparse>, '%')
            </if>
        </where>
        limit <#noparse>#{</#noparse>currIndex<#noparse>}</#noparse>, <#noparse>#{</#noparse>pageSize<#noparse>}</#noparse>
    </select>

    <select id="getCountOfFind" resultType="Integer">
        select count(*) from ${className?uncap_first}
        <where>
            <if test="${firstPropertyName} != ''">
                and ${firstPropertyName} like concat('%', <#noparse>#{</#noparse>${firstPropertyName}<#noparse>}</#noparse>, '%')
            </if>
        </where>
    </select>
</mapper>
