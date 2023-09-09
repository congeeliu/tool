package ${packageName}.pojo;

import java.util.Date;

public class ${className} {
<#if properties??>
    <#list properties as property>
    private ${property.type} ${property.name};
    </#list>
</#if>

    public ${className}() {
    }

    public ${className}(<#if properties??><#list properties as property>${property.type} ${property.name}<#sep>, </#list></#if>) {
<#if properties??>
    <#list properties as property>
        this.${property.name} = ${property.name};
    </#list>
</#if>
    }

<#if properties??>
    <#list properties as property>
    public ${property.type} get${property.name?cap_first}() {
        return ${property.name};
    }

    public void set${property.name?cap_first}(${property.type} ${property.name}) {
        this.${property.name} = ${property.name};
    }

    </#list>
</#if>
}
